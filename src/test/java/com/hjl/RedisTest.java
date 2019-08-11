package com.hjl;

import com.hjl.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * @Author hjl
 * @Description redis测试类
 * @Date 2019/8/11 11:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void testRedisUtil() {
        Set<String> keys = redisUtil.getAllKey("*");
        keys.forEach(key -> System.out.println(key));
    }
}
