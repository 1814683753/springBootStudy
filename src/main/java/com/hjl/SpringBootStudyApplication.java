package com.hjl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Author hjl
 * @Description 启动类
 *
 */
@SpringBootApplication
@MapperScan("com.hjl.dao")
@ServletComponentScan("com.hjl.filter")
public class SpringBootStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }

}
