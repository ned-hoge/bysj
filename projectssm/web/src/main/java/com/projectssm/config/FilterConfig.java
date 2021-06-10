package com.projectssm.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registFilter(){
        FilterRegistrationBean registration=new FilterRegistrationBean();
        registration.setFilter(new com.projectssm.config.LogCostFilter());
        registration.addUrlPatterns("/views/index.html");
        registration.setName("LogCostFilter");
        registration.setOrder(1);
        return registration;
    }
}
