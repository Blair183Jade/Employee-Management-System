package com.itheima.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Log annotation is used to mark methods that should have their execution logged.
 * This is typically applied as part of an Aspect-Oriented Programming (AOP) approach,
 * where logging aspects intercept the execution of annotated methods to perform
 * logging automatically.
 *
 * This annotation must be retained at runtime, allowing the AOP framework to detect
 * and act upon the annotated methods during the application's execution.
 *
 * It is targeted at methods, meaning it should only be used to annotate method declarations.
 */
@Retention(RetentionPolicy.RUNTIME)  // Specifies that this annotation should be available at runtime.
@Target(ElementType.METHOD)          // Specifies that this annotation can only be applied to methods.
public @interface Log {
}
