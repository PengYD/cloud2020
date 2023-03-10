
import io.micrometer.core.instrument.util.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

public class Test2 {

    private static String SCOPE = "scope(";

    private static String END_SCOPE = "endscope";

    private static String EMPTY = "";


    @Test
    public void test1() throws Exception{


        Map<String, String> map = new HashMap<>();
        String s = map.get("123");

        BigDecimal bigDecimalA = new BigDecimal("1.9");
        BigDecimal bigDecimalB = new BigDecimal("1.90");

        System.out.println(bigDecimalA == bigDecimalB);
        System.out.println(bigDecimalA.equals(bigDecimalB));

        int i = bigDecimalA.compareTo(bigDecimalB);
//        String a = "123459999999999999999";
//        String b = "123459999999999999999";
//        System.out.println(b == a);



//        String s = this.scopeUtils("Scope(kehu:{'国际客户05','国际客户04','国际客户03','国际客户02','国际客户01','不区分客户'},kemu:{'货币资金','交易性金融资产','应收票据','应收账款','预付账款','应收股利','应收利息','其它应收款'},chanpin:{'丝绸','服装','纺织','针织','家纺','棉花','其他','不区分产品'},bizhong:{'人民币CNY','美元USD','欧元EUP','英镑GBP','迪拉姆AED','港币HKD','日元JPY','澳元MOP','越南盾VND','印度卢比INR'},banben00:{'集团目标版','年度经营目标版','集团下发差异','编制第一版','编制第二版','编制第三版'},qita:{'不区分其他','筹资费用'},qingjing:{'去年同期','预算完成率','预算数','实际数'});\n" +
//                "['全年数','不区分贸易方式','总裁办公室']=if(DB('555444',!kehu,!kemu,!chanpin,!bizhong,!banben00,'总裁办公室','不区分贸易方式',!qita,!qingjing,'全年数')>12,-1,1);\n" +
//                "Endscope;");
//        String abc = "8943,8944,8945,8946,8947,8948,8949,8950,8951,8952,8953,8954,8955,8956,8957,8958,8959,8960,8961,8962,8963,8964,8965,8966,8967,8968,8969,8970,8971,8972,8973,8974,8975,8976,8977,8978,8979,8980,8981,8982,8983,8984,8985,8986,8987,8988,8989,8990,8991,8992,8993,8994,8995,8996,8997,8998,8999,9000,9001,9002,9003,9004,9005,9006,9007,9008,9009,9010,9011,9012,9013,9014,9015,9016,9017,9018,9019,9020,9021,9022,9023,9024,9025,9026,9027,9028,9029,9030,9031,9032,9033,9034,9035,9036,9037,9038,9039,9040,9041,9042,9043,9044,9045,9046,9047,9048,9049,9050,9051,9052,9053,9054,9055,9056,9057,9058,9059,9060,9061,9062,9063,9064,9065,9066,9067,9068,9069,9070,9071,9072,9073,9074,9075,9076,9077,9078,9079,9080,9081,9082,9083,9084,9085,9086";
//        System.out.println(abc.split(",").length);
//        System.out.println("\r\n");
    }

    @Test
    public void test2(){

        char[] chars = "ZAAAA".toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ('Z' != chars[i]) {
                chars[i] = (char) (chars[i] + 1);
                break;
            }
        }

        System.out.println(chars);
    }

    public  String scopeUtils(String str){
        // 参数判断
        if (StringUtils.isEmpty(str)){
            return str;
        }
        if (!str.toLowerCase().contains(SCOPE) || !str.toLowerCase().contains(END_SCOPE)){
            return str;
        }

        // 解析后的规则表达式
        StringBuffer rules = new StringBuffer();
        // scope表达式解析结果 key:dim  value:member List
        Map<String, List<String>> scopeMap = new HashMap<>();
        // 判断是否是scope的作用范围
        boolean isScope = false;

        // 将规则按照分号拆解为一条一条的(去掉换行符 空格)
        List<String> ruleList = Arrays.asList(str.replace("\n", EMPTY).replace("\r",EMPTY).replace(" ", EMPTY).split(";"));
        for (String rule : ruleList){
            if (rule.toLowerCase().contains(SCOPE) && !rule.toLowerCase().equals(END_SCOPE)){
                isScope = true;
                // 解析scope函数的范围
                try {
                    scopeMap = scopeHead(rule);
                }catch (Exception e){

                }
                continue;
            }
            if (rule.toLowerCase().contains(END_SCOPE)){
                isScope = false;
                // 清除scope解析结果，等待下一个scope函数。
                scopeMap.clear();
                continue;
            }
            if (isScope){
                ruleJoin(rule, scopeMap, rules);
            }else {
                rules.append(rule).append(";\n");
            }
        }
        return rules.toString();
    }

    /**
     * 将scope函数解析为 Map<String,List<String>> key为维度 value为对应的成员数组
     * @param scope 示例 ：Scope(D1:{'d1','d2'},E2:{'e1','e2'})
     * @return
     */
    private static Map<String,List<String>> scopeHead(String scope){
        Map<String,List<String>> scopeMap = new HashMap<>();
        // 示例 ：Scope(D1:{'d1','d2'},E2:{'e1','e2'})
        // 去掉示例中外层的Scope()
        String dimAndMembers = scope.substring(SCOPE.length()).substring(0, scope.length() - SCOPE.length() -1);

        String[] split = dimAndMembers.split("},");
        // 示例 ：D1:{'d1','d2'}
        // 处理示例中维度成员表达式
        for (String dim : split) {
            // 根据 :{ 分解获得维度和成员两部分
            String[] dimMember = dim.split(":\\{");
            // 去掉 } 然后根据 ， 分解或得成员数组
            String[] members = dimMember[1].replaceAll("}", EMPTY).split(",");
            scopeMap.put( dimMember[0], Arrays.asList(members));
        }
        return scopeMap;
    }

    /**
     * 将规则中的scope对应的维度 替换为对应的成员，算出笛卡尔积 拼接。
     * @param rule
     * @param scopeMap
     * @param rules
     */
    private static void ruleJoin( String rule, Map<String,List<String>> scopeMap, StringBuffer rules){
        // Scope(D1:{'d1','d2'},E2:{'e1','e2'});
        // [!D1,!E2,'c']=if(DB('jy001',!D1,!E2,'c2')>999,-1,1)

        List<String> ruleList = new ArrayList<>();
        // 数据中转
        List<String> list = new ArrayList<>();
        ruleList.add(rule);
        scopeMap.forEach((key, value)->{
            String dim = "!" + key;
            if (rule.contains(dim)){
                for (String member : value){
                    for (String str : ruleList){
                        list.add(str.replace(dim, member));
                    }
                }
                ruleList.clear();
                ruleList.addAll(list);
                list.clear();
            }
        });

        System.out.println(ruleList.size());
//        for (String var : ruleList){
//
//            System.out.println(var);
//        }
//        ruleList.forEach(e->{
//            rules.append(e).append(";\n");
//        });
    }
}
