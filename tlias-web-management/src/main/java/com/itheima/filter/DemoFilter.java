package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * A Servlet Filter for demonstrating basic filter operations in a Java web application.
 * This filter intercepts all requests and responses to perform pre-processing and post-processing.
 * <p>
 * Uncomment the @WebFilter annotation to activate this filter. The urlPatterns attribute specifies
 * which requests should be intercepted by this filter, with "/*" indicating all paths.
 */
//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {

    /**
     * Initializes the filter. This method is called by the web container to indicate that the filter is
     * being placed into service. This method is called once for each instance of the filter.
     *
     * @param filterConfig Provides filter-specific configuration information.
     * @throws ServletException If an error occurs during the initialization process.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init: Initialization method executed");
    }

    /**
     * The core method of the Filter. This method is called each time a request/response pair is
     * passed through the chain due to a client request for a resource at the end of the chain.
     * The FilterChain passed in to this method allows the Filter to pass on the request and response
     * to the next entity in the chain.
     *
     * @param request  The servlet request we are processing.
     * @param response The servlet response we are creating.
     * @param chain    The filter chain we are processing.
     * @throws IOException      if an I/O related error has occurred during the processing.
     * @throws ServletException if the request could not be handled.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("Demo Filter: Request intercepted... Pre-processing logic");

        // Proceed with the rest of the filter chain
        chain.doFilter(request, response);

        System.out.println("Demo Filter: Request intercepted... Post-processing logic");
    }

    /**
     * Called by the web container to indicate to a filter that it is being taken out of service.
     * This method is only called once all threads within the filter's doFilter method have exited
     * or after a timeout period has passed.
     * After the web container calls this method, it will not call the doFilter method again
     * on this instance of the filter.
     */
    @Override
    public void destroy() {
        System.out.println("destroy: Destruction method executed");
    }
}
