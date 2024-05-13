package com.itheima.controller;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller that demonstrates the usage of cookies and HTTP sessions.
 * Provides methods to set and retrieve cookies, and to manipulate session data.
 */
@Slf4j
@RestController
public class SessionController {

    /**
     * Sets a cookie in the user's browser.
     * This method creates a cookie named 'login_username' with a value 'itheima' and sends it to the client.
     *
     * @param response HttpServletResponse to which the cookie will be added.
     * @return Result indicating success of the operation.
     */
    @GetMapping("/c1")
    public Result cookie1(HttpServletResponse response) {
        // Create and add a cookie to the response
        response.addCookie(new Cookie("login_username", "itheima"));
        return Result.success();
    }

    /**
     * Retrieves and logs the value of a specific cookie from the request.
     * This method searches for a cookie named 'login_username' and logs its value.
     *
     * @param request HttpServletRequest from which cookies are retrieved.
     * @return Result indicating success of the operation.
     */
    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request) {
        // Get cookies from the request
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("login_username".equals(cookie.getName())) {
                    log.info("login_username: {}", cookie.getValue());
                }
            }
        }
        return Result.success();
    }

    /**
     * Demonstrates setting an attribute in the HTTP session.
     * This method stores a value 'tom' under the attribute name 'loginUser' in the session.
     *
     * @param session HttpSession in which the attribute is stored.
     * @return Result indicating success of the operation.
     */
    @GetMapping("/s1")
    public Result session1(HttpSession session) {
        // Log session info
        log.info("HttpSession-s1: {}", session.hashCode());

        // Set attribute in session
        session.setAttribute("loginUser", "tom");
        return Result.success();
    }

    /**
     * Retrieves and logs the value of an attribute from the HTTP session.
     * This method fetches the value of 'loginUser' from the session and logs it.
     *
     * @param request HttpServletRequest from which the session is retrieved.
     * @return Result containing the value of the session attribute if found.
     */
    @GetMapping("/s2")
    public Result session2(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("HttpSession-s2: {}", session.hashCode());

        // Get attribute from session
        Object loginUser = session.getAttribute("loginUser");
        log.info("loginUser: {}", loginUser);
        return Result.success(loginUser);
    }
}
