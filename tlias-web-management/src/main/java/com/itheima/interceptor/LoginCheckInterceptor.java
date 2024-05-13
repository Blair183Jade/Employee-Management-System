package com.itheima.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Interceptor to check login status for secured resources.
 * This interceptor verifies the presence and validity of a JWT in the request headers before allowing access to secured endpoints.
 */
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    /**
     * Pre-handle method that runs before the target handler method.
     * It checks if the request contains a valid JWT token in the header and decides whether to proceed with the request.
     *
     * @param req the HTTP request
     * @param resp the HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return true to continue the request, false to abort the request
     * @throws Exception in case of errors
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String url = req.getRequestURL().toString();
        log.info("Request URL: {}", url);

        if (url.contains("login")) {
            log.info("Login process, proceed...");
            return true;
        }

        String jwt = req.getHeader("token");
        if (!StringUtils.hasLength(jwt)) {
            log.info("Token not present in request header, returning 'not logged in' message");
            sendNotLoggedInResponse(resp, "NOT_LOGIN");
            return false;
        }

        try {
            JwtUtils.parseJWT(jwt);
            log.info("Token valid, proceed");
            return true;
        } catch (Exception e) {
            log.error("Token parsing failed, returning 'not logged in' message", e);
            sendNotLoggedInResponse(resp, "NOT_LOGIN");
            return false;
        }
    }

    /**
     * Post-handle method that runs after the handler method.
     * It can be used for additional processing after the main logic has been completed.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param handler the handler (or handler method) that has been executed
     * @param modelAndView the ModelAndView that the handler returned (can be null)
     * @throws Exception in case of errors
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("postHandle executed");
    }

    /**
     * Callback method that runs after the complete request has been finished and the view was rendered.
     * It can be used for cleanup activities.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param handler the handler (or handler method) that was executed
     * @param ex any exception thrown on handler execution, if any
     * @throws Exception in case of errors
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("afterCompletion executed");
    }

    /**
     * Helper method to send a JSON response indicating the user is not logged in.
     *
     * @param resp the HttpServletResponse to write the error message to
     * @param errorCode the error code to include in the response
     * @throws IOException if an error occurs during writing to the response
     */
    private void sendNotLoggedInResponse(HttpServletResponse resp, String errorCode) throws IOException {
        Result errorResult = Result.error(errorCode);
        String errorJson = JSONObject.toJSONString(errorResult);
        resp.getWriter().write(errorJson);
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
