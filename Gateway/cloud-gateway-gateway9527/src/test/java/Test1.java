import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author : PengYanDong
 * @description : ${description}
 * @create : 2020-07-21 16:18
 * <p>
 * Copyright 2020 All rights reserved.
 **/
@Slf4j
public class Test1 {
    public static void main(String[] args) {
        log.info("****** come in MyLogGateWayFilter: " + new Date());
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }
}
