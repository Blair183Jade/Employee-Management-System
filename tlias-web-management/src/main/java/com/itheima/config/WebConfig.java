package com.itheima.config;

import com.itheima.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class to setup web MVC settings, including interceptors.
 * <p>
 * This class implements the WebMvcConfigurer interface to customize the way Spring handles
 * web requests. It is marked with @Configuration to indicate that it provides Spring configuration.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;  // Dependency injection of the login check interceptor.

    /**
     * Configures the registry of interceptors to apply them to HTTP requests.
     * This method adds a custom interceptor to check the login status of the user across all
     * requests, excluding the login path to avoid circular checks and allow for user authentication.
     *
     * @param registry the interceptor registry where interceptors are registered.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/**")          // Apply the interceptor to all paths.
                .excludePathPatterns("/login");  // Exclude the login path to prevent checking during authentication.
    }
}
