package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Main application class for the Tlias Web Management Application.
 * This class serves as the launching point for the application, utilizing Spring Boot's capabilities
 * for easy startup and configuration.
 * <p>
 * Annotations:
 *
 * @SpringBootApplication - Used to enable a host of features, e.g. Java-based Spring configuration,
 * component scanning, and more. This is a convenience annotation that is equivalent to using
 * @Configuration, @EnableAutoConfiguration, and @ComponentScan with their default attributes.
 * @ServletComponentScan - Enables the scanning of Servlets, Filters, and Listeners within
 * Spring Boot applications. This allows the application to detect and register servlet components
 * annotated with @WebServlet, @WebFilter, or @WebListener.
 */
@SpringBootApplication
@ServletComponentScan // Enables scanning of Servlet components like filters and servlets
public class TliasWebManagementApplication {

    /**
     * Main method to run the Spring Boot application.
     * This method uses SpringApplication.run to bootstrap the application, starting
     * the embedded web server, auto-detecting and configuring beans, and more.
     *
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(TliasWebManagementApplication.class, args);
    }
}
