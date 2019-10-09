package com.hjl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author hjl
 * @Description 启动类
 *
 */
@SpringBootApplication
@MapperScan("com.hjl.dao")
@ServletComponentScan("com.hjl.filter")
@EnableScheduling
public class SpringBootStudyApplication {

    private static final Logger LOG = LogManager.getLogger("Embedded");
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }

}
