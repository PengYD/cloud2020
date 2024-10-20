import pymysql
import cx_Oracle


def get_data_by_sql(cur, sql_1):
    cur.execute(sql_1)
    data = cur.fetchall()
    data_list = list(data)
    return data_list


def get_all_scene(cur):
    sql = f"SELECT OBJECT_ID,SCENE_POS FROM T_MR_SCENE  WHERE P_APP_ID ='{app_id}' ORDER BY SCENE_POS "
    scene_list = get_data_by_sql(cur, sql)
    return scene_list


def create_sql(cur):
    print('获取所有场景。。。。。。。')
    sql_list = []
    scene_list = get_all_scene(cur)
    count = len(scene_list)
    i = 1
    for s in scene_list:
        print('\r\n开始处理场景下元素的totalPos【{}/{}】\r\n'.format(i, count))
        create_total_pos(cur, s, sql_list)
        i = i + 1
    return sql_list


def get_element_by_scene(scene_id, cur):

    sql_1 = "SELECT ELEMENT_ID,ELEMENT_PARENT_ID,ELEMENT_POS,ELEMENT_TOTAL_POS,ELEMENT_DB_CODE,ELEMENT_TOTAL_CODE," \
            f"ELEMENT_LEVEL FROM T_MR_SCENE_ELEMENT WHERE SCENE_ID ='{scene_id}' " \
            "ORDER BY ELEMENT_LEVEL,ELEMENT_PARENT_ID"
    scene_list = get_data_by_sql(cur, sql_1)
    return scene_list


def create_total_pos(cur, s, sql_list):
    print('获取场景下所有元素。。。。。。。')
    scene_id = s[0]
    element_list = get_element_by_scene(scene_id, cur)

    level_map = {}
    total_pos_map = {}
    total_code_map = {}

    level_map[scene_id] = -1
    total_pos_map[scene_id] = str(s[1]).rjust(5, '0')
    total_code_map[scene_id] = None

    parent_id = '1'
    parent_total_code = ''
    parent_total_pos = ''
    parent_total_level = 1

    for element in element_list:

        if element[1] != parent_id:
            parent_id = element[1]
            parent_total_code = total_code_map[parent_id]
            parent_total_pos = total_pos_map[parent_id]
            parent_total_level = level_map[parent_id]

            check_element(element, parent_total_code, parent_total_pos, parent_total_level, sql_list,
                          level_map, total_pos_map, total_code_map)

        else:
            check_element(element, parent_total_code, parent_total_pos, parent_total_level, sql_list,
                          level_map, total_pos_map, total_code_map)


def check_element(element, parent_total_code, parent_total_pos, parent_total_level, sql_list,
                  level_map, total_pos_map, total_code_map):
    e_id = element[0]
    e_total_pos = parent_total_pos + ',' + str(element[2]).rjust(5, '0')
    if parent_total_code is None:
        e_total_code = element[4]
    else:
        e_total_code = parent_total_code + ',' + element[4]
    e_level = parent_total_level + 1

    if e_total_pos != element[3]:
        sql = f"UPDATE T_MR_SCENE_ELEMENT SET ELEMENT_TOTAL_POS = '{e_total_pos}' WHERE ELEMENT_ID = '{e_id}'"
        sql_list.append(sql)
        print(sql)
    if e_total_code != element[5]:
        sql = f"UPDATE T_MR_SCENE_ELEMENT SET ELEMENT_TOTAL_CODE = '{e_total_code}' WHERE ELEMENT_ID = '{e_id}'"
        sql_list.append(sql)
        print(sql)

    if e_level != element[6]:
        sql = f"UPDATE T_MR_SCENE_ELEMENT SET ELEMENT_LEVEL = '{e_level}' WHERE ELEMENT_ID = '{e_id}'"
        sql_list.append(sql)
        print(sql)

    level_map[e_id] = e_level
    total_pos_map[e_id] = e_total_pos
    total_code_map[e_id] = e_total_code


# # Oracle数据库连接信息
# data_type = 'oracle'
conn = cx_Oracle.connect('ecs2_c1_dev', 'ecs2_c1_dev', 'ta-prd-scan.kinlong.cn:1521/DEV4ECS')

# # MySQL数据库连接信息
# # data_type = 'mysql'
# conn = pymysql.connect(
#     host='119.3.253.175',
#     user='root',
#     password='Ecs2163!@#',
#     db='ecs2',
#     charset='utf8',
# )
# 管会平台的APPId
app_id = 'c76ef194385e11eb9f43c90043244155'


if __name__ == '__main__':
    cursor = conn.cursor()
    try:
        print("开始执行脚本。。。。。。")
        up_data_sql = create_sql(cursor)

        print("开始执行sql。。。。。。。。。。")
        for sql_2 in up_data_sql:
            print(sql_2)
            # cursor.execute(sql_2)
    except Exception as e:
        print("脚本执行异常，回滚数据。。。。。。。。。。。。。。。")
        print(e)
        conn.rollback()
    else:
        print("执行结束，提交事务。。。。。。。。。。")
        conn.commit()
    finally:
        cursor.close()
        conn.close()
