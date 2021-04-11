
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-22 17:49
 * <p>
 * Copyright 2020 All rights reserved.
 **/
public class Test1 {

    /**
     * 不可用字符
     */
    private final static String regEx = "['*@、\\\\:,{}\"!><|+?;/~\\- ]";

    /**
     * 错误类型
     */
    private final static String EMPTY_ERROR = "empty_error";

    private final static String LENGTH_ERROR = "length_error";

    private final static String FORMAT_ERROR = "format_error";

    private final static String CHARACTER_ERROR = "character_error";

    /**
     * 提示文本
     */
    private final static String DIM = "维度";

    private final static String CUBE = "多维数据集";

    private final static String MEMBER = "维度成员";

    private final static String ATTR = "维度属性";

    private final static String NAME = "名称";

    private final static String CODE = "编码";

    private final static String VALUE = "值";

    /**
     * 模块code
     */
    private final static String MODEL_DIM = "dim";

    private final static String MODEL_CUBE = "cube";

    private final static String MODEL_MEMBER = "member";

    private final static String MODEL_ATTR = "attr";

    /**
     * 字段code
     */
    private final static String VALUE_TYPE_CODE = "code";

    private final static String VALUE_TYPE_NAME = "name";

    private final static String VALUE_TYPE = "value";

    /**
     * 不可使用的字符串
     */
    private final static String STR_CUBE = "cube";

    private final static String STR_DIM = "dimension";

    private final static String STR_SUBSET = "subset";

    private final static String STR_VIEW = "view";

    private final static String STR_PROCESS = "process";

    private final static String STR_CHORES = "chores";

    /**
     * 数字常量
     */
    private final static int VALUE_LENGTH = 30;

    private final static int MAP_SIZE = 4;

    /**
     * 多维库验证名称
     * 1、长度小于30
     * 2、不区分大小写
     * 3、不可用特殊字符(撇号、星号、at符号、斜杠反斜杠、冒号、逗号、花括号、双引号、感叹号、基本运算符号、管道符号、问号、分号、波浪号、空格)
     * 4、作用对象：多维模型、模型编码；维度类型、维度编码；维度成员、成员编码；变量。
     * 5、不可用保留字段（不区分大小写）：cube、dimension、subset、view、process、chores
     * @param value 成员编码
     */
    public static String validateName(String value){
        //非空判断
        if (value == null || value.isEmpty()){
            return EMPTY_ERROR;
        }
        //长度判断
        if (value.length() > VALUE_LENGTH){
            return LENGTH_ERROR;
        }
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(value);
        if (m.find()){
            return FORMAT_ERROR;
        }
        //不区分大小写，判断是否使用保留字段
        value = value.toLowerCase();
        if (STR_CUBE.equals(value)
                || STR_DIM.equals(value)
                || STR_SUBSET.equals(value)
                || STR_VIEW.equals(value)
                || STR_PROCESS.equals(value)
                || STR_CHORES.equals(value)){
            return CHARACTER_ERROR;
        }
        value ="134";
        return null;
    }

    private static String errorText(String model, String type){

        Map<String, String> map = new HashMap<>(MAP_SIZE);
        map.put(VALUE_TYPE_NAME, NAME);
        map.put(VALUE_TYPE_CODE, CODE);
        map.put(VALUE_TYPE, VALUE);

        switch (model){
            case MODEL_DIM:{
                return  DIM + map.get(type);
            }
            case MODEL_CUBE:{
                return  CUBE + map.get(type);
            }
            case MODEL_MEMBER:{
                return  MEMBER + map.get(type);
            }
            case MODEL_ATTR:{
                return  ATTR + map.get(type);
            }
            default:
                return null;
        }
    }

    /**
     * 多维库字段校验
     * @return  错误提示文本
     */
    @Test
    public  void tabaseValidate() {

        String a= null;
        StringUtils.isEmpty(null);
        a.isEmpty();
        String model=MODEL_CUBE;
        String type=VALUE_TYPE_CODE;
        String value ="cubE";

        String a1  = "";
        //编码不可为空或"" 且长度不能超过30
        String validate = validateName(value);
        if (validate != null) {
            switch (validate) {
                case EMPTY_ERROR: {
                    a = errorText(model, type) + "不能为空";
                }
                case LENGTH_ERROR: {
                    a = errorText(model, type) + "字段长度不允许超过30位。";
                }
                case FORMAT_ERROR: {
                    a = errorText(model, type) + "不能输入特殊字符";
                }
                case CHARACTER_ERROR: {
                    a = errorText(model, type) + "不能输入特殊字符串。";
                }
                default:
                    break;
            }
        }
        System.out.println(a);
    }

    /**
     * 获取模块编码
     * @return
     */
    public static String getCubeModeCode(){
        return MODEL_CUBE;
    }

    public static String getDimModeCode(){
        return MODEL_DIM;
    }

    public static String getMemberModeCode(){
        return MODEL_MEMBER;
    }

    public static String getAttrModeCode(){
        return MODEL_ATTR;
    }

    /**
     * 获取校验类型
     * @return
     */
    public static String codeType(){
        return VALUE_TYPE_CODE;
    }

    public static String nameType(){
        return VALUE_TYPE_NAME;
    }

    public static String attrType(){
        return VALUE_TYPE;
    }

}
