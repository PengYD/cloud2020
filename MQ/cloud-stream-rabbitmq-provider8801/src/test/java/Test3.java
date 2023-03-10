
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test3 {

    @Test
    public void test1() throws Exception{

        String isTenantMode = "";

        boolean b = Boolean.parseBoolean(isTenantMode);
        Boolean isLastLevel = null;
        boolean equals = Boolean.TRUE.equals(isLastLevel);
        System.out.println(equals);
    }

    @Test
    public void test123(){

        String attrValue = "{\"relatedType\":\"idList\",\"value\":\"[\"11ec42b838fd90dc89a6c952ef9d8fdf\",\"11ec42b838fe2d1d89a6bb8c03f2626e\",\"11ec42b838fec95e89a6295f90607ad8\",\"11ec42b838ff3e8f89a63359af13a856\"]\"}";
        AttrValueBO attrValueBo = JSONObject.parseObject(attrValue, AttrValueBO.class);
        for (String id : attrValueBo.getValue()) {
            List<String> list = JSONObject.parseObject(id, List.class);
            List<String> list1 = JSONObject.parseArray(id, String.class);
        }
    }

    public class AttrValueBO {

        public String getRelatedType() {
            return relatedType;
        }

        public void setRelatedType(String relatedType) {
            this.relatedType = relatedType;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }

        private String relatedType;

        private List<String> value;
    }

    @Test
    public void test1234(){

        List<List<String>> stringLists = new ArrayList<>();

        List<String> ab = new ArrayList<>();
        ab.add("A");

        List<String> abc = new ArrayList<>();
        abc.add(null);

        stringLists.add(ab);
        stringLists.add(abc);

        Optional<List<String>> result = stringLists.parallelStream()
                .filter(CollectionUtils::isNotEmpty)
                .reduce((a, b) -> {
                    System.out.println(a);
                    System.out.println(b);
                    a.retainAll(b);
                    return a;
                });

        System.out.println(result.get());

    }

    @Test
    public void test12345(){


        String msg = "12334<tr>dasd";
        List<String> list=new ArrayList<String>();
        Pattern p = Pattern.compile("(\\[<^\\>]*\\])");
        Matcher m = p.matcher(msg);
        while(m.find()){
            list.add(m.group().substring(1, m.group().length()-1));
        }

        Double aDouble = new Double("1.0001");
        String format1 = String.format("0.%02d", 0);
        String format =  String.format("0.%02d", 0 );

        String str = "一二三四五六七八九十";
        String str1 = "qqqqqwwwwweeee  errrrrtt  tttyyyyy";

        String replace = str1.replace(" ", "a");

        String a = null;
        a.replaceAll(" ", " 1");

        System.out.println(str.length());
    }


}
