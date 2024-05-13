package com.itheima.exception;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler class for handling all exceptions across the application.
 * This class provides a centralized exception handling mechanism using @RestControllerAdvice,
 * ensuring that all uncaught exceptions are processed here to return a standard response.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles general exceptions thrown by any method in the application.
     * This method catches all exceptions and logs them, then returns a standardized error response.
     *
     * @param ex The exception that was caught.
     * @return A Result object containing an error message indicating that the operation failed.
     */
    @ExceptionHandler(Exception.class) // This annotation makes this method responsible for handling all exceptions.
    public Result handleException(Exception ex){
        // Print stack trace for debugging purposes
        ex.printStackTrace();

        // Return a custom error message to hide specific error details from the user
        return Result.error("Sorry, an operation failed. Please contact the administrator.");
    }

}
