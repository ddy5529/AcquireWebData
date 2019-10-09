package com.ddy.spide.acquire_web_data.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor:拦截器
 * */
@Component
public class ProductServiceInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     *  用于在将请求发送到控制器之前执行操作。此方法应返回true，以将响应返回给客户端。
     * */
    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //logger.info("preHandle：用于在将请求发送到控制器之前执行操作");
        return true;
    }

    /**
     * 用于在将响应发送到客户端之前执行操作。
     * */
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        //logger.info("postHandle:用于在将响应发送到客户端之前执行操作");
    }

    /**
     *用于在完成请求和响应后执行操作
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {
        //logger.info("afterCompletion:用于在完成请求和响应后执行操作");
    }
}
