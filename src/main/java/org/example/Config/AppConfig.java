package org.example.Config;

import org.example.Filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<RequestFilter> createRequestFilter(){
        FilterRegistrationBean filter= new FilterRegistrationBean();
        filter.setFilter(new RequestFilter());
        filter.setOrder(2);
        return filter;
    }

    @Bean
    public RequestFilter createNewRequestFilter(){
        RequestFilter filter= new RequestFilter();
        return filter;
    }



}
