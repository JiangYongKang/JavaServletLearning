package com.servlet.learning;

import javax.servlet.*;
import java.io.IOException;

/**
 * Author: vincent
 * Date: 2020-11-04 15:03
 * Comment: 自定义的一个前置过滤器
 */

public class BeforeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("BeforeFilter 初始化...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("BeforeFilter 执行逻辑...");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("BeforeFilter 被销毁...");
    }

}
