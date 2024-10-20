import requests
import time
import json

# 登录的token
token = '26f076b51e4211ebbcd3354d03c8c6ae'
# 管会平台的APPId
mdd_app_id = 'c29aca44e35911eaa56e4393828805d4'
# 管会平台的url地址
mdd_pre_url = 'http://192.168.48.238:8282/mdd/'
# 主数据的AppId
master_data_app_id = '7609987ea0e011eaa98b4588e077cbea'
# 主数据的url地址
master_data_pre_url = 'http://192.168.48.238/ecs/masterdata/'

mdd_headers = {
    "Accept": "application/json",
    "Content-Type": "application/json",
    "appId": mdd_app_id,
    "LoginToken": token
}

master_data_headers = {
    "Accept": "application/json",
    "Content-Type": "application/json",
    "appId": master_data_app_id,
    "LoginToken": token
}


def check_request_status(r):
    """
    判断请求是否正常
    :param r: 请求返回对象
    :return:
    """
    if r.status_code != requests.codes.ok and r.status_code != 204:
        raise Exception(f'请求返回状态码: {r.status_code}; '
                        f'请求url地址: {r.request.url}; '
                        f'请求的方式: {r.request.method}; '
                        f'请求的内容: {r.request.body}')

    if r.status_code == requests.codes.ok and False == r.json().get('success'):
        raise Exception(r.json())


def get_mdd_all_dim():
    """
    获取管会所有维度
    :return: 所有维度
    """
    dim_r = requests.get(url=mdd_pre_url + f'metadata/dims?limit=1000', headers=mdd_headers)
    check_request_status(dim_r)

    return dim_r.json().get('items')


def get_master_data_dim_member(dim_id):
    """
    根据维度名称，查询主数据维度下的成员
    :param dim_id:
    :return: 主数据成员列表
    """

    payload = {"typeId": dim_id}
    snapshot_r = requests.post(url=master_data_pre_url + 'masterdataMemberSync/client/startSyncMd',
                               data=json.dumps(payload),
                               headers=master_data_headers)
    check_request_status(snapshot_r)

    print(f"主数据生成的快照版本 {snapshot_r.json().get('data')}")
    snapshot_no = snapshot_r.json().get('data')
    time.sleep(30)
    print('已经休眠30S等待快照生成.')

    return get_master_data_snapshot_member(dim_id, snapshot_no)


def get_master_data_snapshot_member(dim_id, snapshot_no):
    """
    根据维度Id和批次号查询主数据的成员
    :param dim_id:
    :param snapshot_no:
    :return: 维度下的成员
    """

    print(f'查询主数据维度({dim_id})的快照({snapshot_no})成员开始')

    limit = 100
    offset = 0
    payload = {'limit': limit,
               'offset': offset,
               'typeId': dim_id,
               'batchNo': snapshot_no,
               'status': 'ENABLE,DISABLE'}
    member_r = requests.post(url=master_data_pre_url + 'masterdataMember/client/all',
                             data=json.dumps(payload),
                             headers=master_data_headers)
    check_request_status(member_r)
    # print(f'主数据返回的成员数据: {member_r.json()}')
    members = member_r.json().get('list')

    while member_r.json()['hasNextPage']:
        offset = offset + limit
        payload = {'limit': limit,
                   'offset': offset,
                   'typeId': dim_id,
                   'batchNo': snapshot_no,
                   'status': 'ENABLE,DISABLE'}
        member_r = requests.post(url=master_data_pre_url + 'masterdataMember/client/all',
                                 data=json.dumps(payload),
                                 headers=master_data_headers)
        check_request_status(member_r)

        members.extend(member_r.json().get('list'))

    print(f'查询主数据维度({dim_id})的快照({snapshot_no})成员结束')

    return members[1:]


def get_mdd_dim_member(dim_id):
    """
    获取维度下所有成员
    :param dim_id: 维度id
    :return:
    """
    limit = 1000
    offset = 0
    member_r = requests.get(url=mdd_pre_url + f'metadata/dimMembers?'
                                              f'limit={limit}&offset={offset}&totalResults=true'
                                              f'&query=dimId=%27{dim_id}%27',
                            headers=mdd_headers)
    check_request_status(member_r)

    member = member_r.json().get('items')

    while member_r.json()['hasMore']:
        offset = offset + limit

        member_r = requests.get(url=mdd_pre_url + f'metadata/dimMembers?'
                                                  f'limit={limit}&offset={offset}&totalResults=true'
                                                  f'&query=dimId=%27{dim_id}%27',
                                headers=mdd_headers)
        check_request_status(member_r)

        member.extend(member_r.json().get('items'))

    return member
