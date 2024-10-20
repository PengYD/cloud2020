import os
import sys

sys.path.append('../')
from master_data_tool.app_config import *


def restore_dim_member_position(dim_name, is_restore):
    dim_r = requests.get(url=mdd_pre_url + f'metadata/dims?query=dimCode=%27{dim_name}%27', headers=mdd_headers)
    check_request_status(dim_r)

    dim_id = dim_r.json().get('items')[0]['objectId']
    print(f'查询的维度({dim_name})的ID是: {dim_id}')

    do_restore_dim_member_position(dim_id, dim_name, is_restore)


def restore_all_dim_member_position(is_restore):
    dims = get_mdd_all_dim()

    for dim in dims:
        dim_name = dim['dimCode']
        dim_id = dim['objectId']
        print(f'查询的维度({dim_name})的ID是: {dim_id}')

        do_restore_dim_member_position(dim_id, dim_name, is_restore)
        print(os.linesep)
        print(os.linesep)
        time.sleep(10)


def do_restore_dim_member_position(dim_id, dim_name, is_restore):
    members = get_master_data_dim_member(dim_id)

    print(f'维度({dim_name})下成员的position恢复开始.')
    restore_sql = []
    for member in members:
        member_id = member['objectId']
        member_position = member['position']
        member_path = member['path']

        try:
            mdd_member_r = requests.get(url=mdd_pre_url + f'metadata/dimMembers/{member_id}', headers=mdd_headers)
            check_request_status(mdd_member_r)
        except Exception:
            print(f'======查询不到成员id({member_id})')
        else:
            mdd_member_position = mdd_member_r.json()['dimMemberPos']
            mdd_member_total_position = mdd_member_r.json()['dimMemberTotalPos']

            position_is_ok = True
            total_position_is_ok = True
            if member_position != mdd_member_position:
                print('成员 <{} ({})> 的position不一致. < {} - {}>'.format(
                    mdd_member_r.json()['dimMemberName'], member_id, member_position, mdd_member_position))
                position_is_ok = False
            if member_path != mdd_member_total_position:
                print('成员 <{} ({})> 的total position不一致. <{} - {}>'.format(
                    mdd_member_r.json()['dimMemberName'], member_id, member_path, mdd_member_total_position))
                total_position_is_ok = False

            if is_restore:
                # print('更新成员 <{} ({})> 的position 和total position不一致'.format(
                #     mdd_member_r.json()['dimMemberName'], member_id, member_position, member_path))
                if position_is_ok is True and total_position_is_ok is False:
                    sql = "UPDATE T_MDD_DIM_MEMBER SET DIM_MEMBER_TOTAL_POS='{}' WHERE OBJECT_ID='{}';" \
                        .format(member_path, member_id)
                    restore_sql.append(sql)
                if position_is_ok is False and total_position_is_ok is True:
                    sql = "UPDATE T_MDD_DIM_MEMBER SET DIM_MEMBER_POS='{}' WHERE OBJECT_ID='{}';" \
                        .format(member_position, member_id)
                    restore_sql.append(sql)
                if position_is_ok is False and total_position_is_ok is False:
                    sql = "UPDATE T_MDD_DIM_MEMBER SET DIM_MEMBER_POS='{}',DIM_MEMBER_TOTAL_POS='{}' WHERE OBJECT_ID='{}';" \
                        .format(member_position, member_path, member_id)
                    restore_sql.append(sql)

    print(f'维度({dim_name})下成员的position恢复结束.')
    print('\r\n\r\n\r\n============================更新的SQL脚本如下============================\r\n\r\n\r\n')
    for sql in restore_sql:
        print(sql)


if __name__ == '__main__':
    # restore_dim_member_position('Integration3', True)
    restore_all_dim_member_position(True)
