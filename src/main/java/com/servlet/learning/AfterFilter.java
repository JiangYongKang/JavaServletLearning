package com.servlet.learning;

import javax.servlet.*;
import java.io.IOException;

/**
 * Author: vincent
 * Date: 2020-11-04 15:05
 * Comment: 自定义的一个后置过滤器
 */

public class AfterFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("AfterFilter 初始化...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("AfterFilter 执行逻辑...");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("AfterFilter 被销毁...");
    }

}
