package com.hjl;

import com.hjl.service.AopTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ：hjl
 * @date ：2019/10/31 17:36
 * @description：
 * @modified By：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AopTest {
    @Autowired
    private AopTestService aopTestService;
    @Test
    public void testAop(){
        aopTestService.test();
    }
}
