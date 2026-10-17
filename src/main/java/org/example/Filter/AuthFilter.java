package org.example.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Order(1)
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        //System.out.println(httpRequest.getParameter("admnNo"));

        //httpRequest.setAttribute("admnNo",1002);

        filterChain.doFilter(servletRequest,servletResponse);

        //System.out.println("Auth filter "+httpResponse.getStatus());


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
