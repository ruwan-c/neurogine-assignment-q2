package com.neurogine.assignment.demo.configuration;

import com.neurogine.assignment.demo.aspect.GeneralInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * register interceptors
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private GeneralInterceptor generalInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(generalInterceptor);
    }
}
