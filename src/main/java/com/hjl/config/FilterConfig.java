package com.hjl.config;

import com.hjl.filter.TestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author hjl
 * @Description 让过滤器生效的配置类
 * @Date 2019/8/4 17:06
 */
@Configuration
public class FilterConfig {

    /**
     * 可以创建多个过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean registerFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new TestFilter());
        // 拦截路径，可以添加多个
        registration.addUrlPatterns("/*");
        registration.setName("TestFilter");
        // spring boot 会按照order值的大小，从小到大的顺序来依次过滤。
        registration.setOrder(1);
        return registration;
    }
}
