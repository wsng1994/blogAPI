package com.wsng.blog.filter;

import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author Sean
 * @Date: 2021/4/27 11:26
 * @Version 0.01
 */

@WebFilter(filterName="myFilter",urlPatterns="/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {
//        System.out.println("MyFilter init............");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
//        MDC.put("SEQ","999999");
//        System.out.println("MyFilter doFilter.........before");
        chain.doFilter(request, response);
//        System.out.println("MyFilter doFilter.........after");
    }
    @Override
    public void destroy() {

//        System.out.println("MyFilter destroy..........");
    }


}
