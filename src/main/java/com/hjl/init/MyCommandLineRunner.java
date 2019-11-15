package com.hjl.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author ：hjl
 * @date ：2019/11/15 17:36
 * @description：
 * @modified By：
 */
@Component
//指定其执行顺序,值越小优先级越高
@Order(value = 10)
public class MyCommandLineRunner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(MyCommandLineRunner.class);
    /**
     * 工程启动结束后会立即执行的方法
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        LOG.info("MyCommandLineRunner execute ..... args:{}",args);
    }
}
