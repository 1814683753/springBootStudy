package com.hjl.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author hjl
 * @Description 过滤器测试类
 * @Date 2019/8/4 15:44
 */
public class TestFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(TestFilter.class);

    /**
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("init...............");
    }

    /**
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.info("start...............");
        filterChain.doFilter(servletRequest,servletResponse);
        LOG.info("end...............");
    }

    /**
     *
     */
    @Override
    public void destroy() {
        LOG.info("destroy...............");
    }
}
