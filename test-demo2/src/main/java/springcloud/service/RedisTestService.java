package springcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author pengyd
 */
@Service
public class RedisTestService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public void test() {
        stringRedisTemplate.opsForValue().set("testKey:ffa","value",30, TimeUnit.MINUTES);
    }
}
