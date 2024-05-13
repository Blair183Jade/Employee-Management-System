package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * A simple servlet filter named XbcFilter that intercepts all web requests to perform specific logic
 * before and after the servlet processing.
 * <p>
 * To activate this filter, uncomment the @WebFilter annotation and specify the URL patterns this filter should apply to.
 * For example, "/*" applies to all incoming requests.
 */
//@WebFilter(urlPatterns = "/*")
public class XbcFilter implements Filter {

    /**
     * Method to handle the request and response just before and just after the rest of the filter chain.
     * It logs messages to the console before passing the request down the chain and after the request has been processed.
     *
     * @param request  The servlet request we are processing.
     * @param response The servlet response we are creating.
     * @param chain    The filter chain we are processing, which allows the request to proceed to the next entity in the filter chain.
     * @throws IOException      if an I/O error occurs during this filter's processing of the request.
     * @throws ServletException if the request for the filter could not be handled.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Xbc Filter: Intercepted request... Pre-processing logic");

        // Continue processing the request
        chain.doFilter(request, response);

        System.out.println("Xbc Filter: Intercepted request... Post-processing logic");
    }
}
