package com.hjl.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author hjl
 * @Description 拦截器测试
 * @Date 2019/8/10 17:17
 */
public class TestInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(TestInterceptor.class);

    /**
     *  controller接口调用前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("接口将被调用....调用的接口地址为: {}",request.getRequestURI());
        // true代表放行，false代表拦截
       return true;
    }

    /**
     * controller 接口调用完页面渲染前调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOG.info("接口调用完成.......");
    }

    /**
     * 页面渲染完成后调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOG.info("页面渲染完成.......");
    }
}
