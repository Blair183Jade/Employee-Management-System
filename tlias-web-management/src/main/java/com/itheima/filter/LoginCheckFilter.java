package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A servlet filter that checks for user authentication before allowing access to protected resources.
 * It checks for a valid JWT token in the request headers; if the token is invalid or missing, the request is denied.
 */
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    /**
     * Processes incoming requests to determine if they should be allowed or rejected based on the presence and validity of a JWT token.
     *
     * @param request ServletRequest object that contains the client's request
     * @param response ServletResponse object that contains the filter's response
     * @param chain FilterChain that allows the request to proceed to the next entity in the chain
     * @throws IOException if an I/O error occurs during this filter's processing of the request
     * @throws ServletException if the request could not be handled
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // Log the request URL.
        String url = req.getRequestURL().toString();
        log.info("Request URL: {}", url);

        // Allow all login-related requests to pass through.
        if(url.contains("login")){
            log.info("Login request, allowing...");
            chain.doFilter(request, response);
            return;
        }

        // Check for the presence of a JWT in the request header.
        String jwt = req.getHeader("token");
        if(!StringUtils.hasLength(jwt)){
            log.info("No token found in request header, returning 'not logged in' message");
            respondWithNotLoggedIn(resp);
            return;
        }

        // Validate the JWT.
        try {
            JwtUtils.parseJWT(jwt);
            log.info("Token is valid, allowing request");
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error("Token parsing failed, returning 'not logged in' message", e);
            respondWithNotLoggedIn(resp);
        }
    }

    /**
     * Helper method to respond with a JSON indicating that the user is not logged in.
     *
     * @param resp HttpServletResponse object to write the error message to.
     * @throws IOException if an error occurs during writing to the response.
     */
    private void respondWithNotLoggedIn(HttpServletResponse resp) throws IOException {
        Result errorResult = Result.error("NOT_LOGIN");
        String errorJson = JSONObject.toJSONString(errorResult);
        resp.getWriter().write(errorJson);
    }
}
