import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-12-29 15:57
 * <p>
 * Copyright 2020 All rights reserved.
 **/
public class Test1 {

    @Test
    public void test1(){

        String totalCode = "00001,00002,00001";

        String ptotalCode = "00001";

        int length = ptotalCode.length();

       String substring = totalCode.substring(length + 2, length + 7);

        String substring1 = totalCode.substring(length+8);

//        '00001'||','||lpad(to_char(to_number(substr(DIM_MEMBER_TOTAL_POS,length('00001')+2,5)) + 1),5,'0')||','
//                ||substr(DIM_MEMBER_TOTAL_POS,length('00001')+8)

        List<String> totalCodeList = new ArrayList<>();
        while (totalCode.contains(",")){
            int i = totalCode.lastIndexOf(",");
            totalCode = totalCode.substring(0,i);
            totalCodeList.add(totalCode);
        }
        System.out.println(totalCodeList.toString());
    }
}
