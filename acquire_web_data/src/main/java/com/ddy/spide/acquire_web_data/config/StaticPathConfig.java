package com.ddy.spide.acquire_web_data.config;

import com.ddy.spide.acquire_web_data.filter.ProductServiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 在这个类中添加路由映射，用于静态资源的跳转
 * */
//@Configuration
public class StaticPathConfig extends WebMvcConfigurationSupport {

    @Autowired
    private ProductServiceInterceptor productServiceInterceptor;

    /**
     * 添加拦截器
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(productServiceInterceptor);
        super.addInterceptors(registry);
    }
}
