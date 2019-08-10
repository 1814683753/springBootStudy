package com.hjl.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author hjl
 * @Description 过滤器的第二个测试类
 * @Date 2019/8/10 15:41
 */
@WebFilter(urlPatterns = "/*", filterName = "TestFilter2")
public class TestFilter2 implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(TestFilter2.class);
    /**
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("testFilter2 init......");
    }

    /**
     * 执行方法之前执行
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.info("testFilter2 start......");
        if(servletRequest instanceof HttpServletRequest){
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            // 获取请求的主机和端口后的路径
            String uri = request.getRequestURI();
            // 获取请求的全路径
            String url = request.getRequestURL().toString();
            LOG.info("uri = {},\t url = {}",uri,url);
        }
        filterChain.doFilter(servletRequest,servletResponse);
        LOG.info("testFilter2 end......");
    }

    /**
     *
     */
    @Override
    public void destroy() {
        LOG.info("testFilter2 destroy......");
    }
}
