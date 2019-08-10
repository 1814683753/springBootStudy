package com.hjl.config;

import com.hjl.interceptor.TestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author hjl
 * @Description 定制spring boot的全局mvc配置
 * @Date 2019/8/10 17:20
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(MyMvcConfig.class);

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 设置所有地址都应用这个拦截器
        // /*代表后面一级 /**代表后面多级目录
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");
        LOG.info("拦截器添加成功!!!!!!");
    }
}
