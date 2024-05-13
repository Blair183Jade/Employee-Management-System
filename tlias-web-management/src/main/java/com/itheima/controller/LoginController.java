package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller responsible for handling employee login requests.
 * This controller provides an endpoint for employee authentication and token generation.
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    /**
     * Authenticates an employee and generates a JWT token upon successful login.
     *
     * @param emp Employee login credentials including username and password.
     * @return Result object containing the JWT token if authentication is successful,
     * or an error message if authentication fails.
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("Employee login attempt: {}", emp);
        Emp e = empService.login(emp);

        // If login is successful, generate and issue a token
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());

            String jwt = JwtUtils.generateJwt(claims); // JWT contains the currently logged-in employee's information
            return Result.success(jwt);
        }

        // If login fails, return an error message
        return Result.error("Username or password incorrect");
    }

}
