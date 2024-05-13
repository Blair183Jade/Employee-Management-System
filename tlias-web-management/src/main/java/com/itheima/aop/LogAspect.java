package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Aspect for logging method execution details.
 * <p>
 * This aspect intercepts all methods annotated with @Log to record execution details such as
 * the method name, parameters, return values, and execution time. These details are then
 * stored in the database using an OperateLogMapper.
 */
@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * Around advice that captures and logs the execution details of methods annotated with @Log.
     *
     * @param joinPoint provides the context in which the intercepted method is executed.
     * @return the result of the method execution.
     * @throws Throwable to rethrow exceptions thrown by the method being intercepted.
     */
    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // Obtain the current HTTP request from the RequestContextHolder.
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // Extract the user ID from the JWT in the request header.
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");

        // Capture the current time and method details.
        LocalDateTime operateTime = LocalDateTime.now();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        // Measure execution time.
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        // Serialize the return value and calculate the execution time.
        String returnValue = JSONObject.toJSONString(result);
        Long costTime = end - begin;

        // Create and insert the operation log into the database.
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);

        // Log the operation for debugging and verification purposes.
        log.info("AOP records operation log: {}", operateLog);

        return result;
    }
}
