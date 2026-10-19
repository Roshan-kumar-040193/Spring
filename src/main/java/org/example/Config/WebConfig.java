package org.example.Config;

import org.example.Interceptor.StudentInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public StudentInterceptor studentInterceptor;

    public WebConfig(StudentInterceptor studentInterceptor){
        this.studentInterceptor =studentInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        interceptorRegistry.addInterceptor(studentInterceptor);
    }

}
