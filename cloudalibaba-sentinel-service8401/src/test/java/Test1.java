import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {

    @Test
    public void test1(){

        List<String> test = new ArrayList<>();
        String objectId = UUID.randomUUID().toString();


        String ad = "A'A ASDDF";
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@# ￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        String regEx1 = "'*@、\\:，,{}\"!!><-|+?？;；/~ ";

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(ad);
        boolean b = m.find();

        String code = ad.toLowerCase();

        if (code.equals("cube")
                || code.equals("dimension")
                || code.equals("subset")
                || code.equals("view")
                || code.equals("process")
                || code.equals("chores")){
            return ;
        }
    }

    @Test
    public void test2(){
        String code = "CU\\Be";
        if (code == null || code.isEmpty()){
            return ;
        }
        if (code.length() > 30){
            return ;
        }

        String regEx = "['*@、\\\\:：，,{}\"!!><|+?？;；/~\\- ]";

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(code);
        if (m.find()){
            return ;
        }

        String code1 = code.toLowerCase();

        if (code1.equals("cube")
                || code1.equals("dimension")
                || code1.equals("subset")
                || code1.equals("view")
                || code1.equals("process")
                || code1.equals("chores")){
            return ;
        }
        return ;
    }

    public static void main(String[] args) {
        String str = "世外桃源居所，,";
        boolean flag = isSpecialChar(str);
        System.out.println("str包含特殊字符：" + flag);
    }

    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
}
