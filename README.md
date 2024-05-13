# Tlias Web Management Application

This repository contains the source code for the Tlias Web Management Application, a Spring Boot-based project designed to demonstrate various functionalities including database interactions, file uploading to Alibaba Cloud OSS, and basic web management tasks. This project is implemented based on the Java Web development tutorial available [here](https://www.youtube.com/playlist?list=PLFbd8KZNbe-8rZ-TtxbiSln4ZngVHiBo0).

## Features

- **Web Services**: Utilizes Spring Boot's web capabilities to expose RESTful services, adhering to the MVC (Model-View-Controller) architecture pattern which separates the application into three main logical components, each with distinct responsibilities.
- **Data Persistence**: Integrates MyBatis for ORM functionalities with MySQL, facilitating the management and querying of databases in an object-oriented fashion.
- **File Upload**: Supports file uploading to Alibaba Cloud OSS, providing a robust solution for handling large file transfers and storage in the cloud.
- **Security**: Implements JWT (JSON Web Tokens) for secure API access, ensuring that only authenticated users can access certain endpoints.
- **AOP (Aspect-Oriented Programming)**: Includes aspect-oriented programming to handle cross-cutting concerns such as logging, transaction management, and security features, enhancing modularity and reducing the redundancy in the code.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- Java 11 or higher
- Maven 3.6 or higher
- MySQL Server 5.7 or higher

## Configuration

1. **Database Setup**: Ensure your MySQL database is up and running.
    - Create a database named `employee`.
    - Update the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` in `application.yml`.

2. **Aliyun OSS Configuration**:
    - Replace `aliyun.oss.endpoint`, `aliyun.oss.accessKeyId`, `aliyun.oss.accessKeySecret`, and `aliyun.oss.bucketName` in `application.yml` with your actual OSS credentials.

## Running the Application

To run the application, execute the following commands in the terminal:

```bash
mvn clean install
java -jar target/tlias-web-management-0.0.1-SNAPSHOT.jar


Alternatively, you can run the application directly using Maven:

```bash
mvn spring-boot:run
```

## Usage

Once the application is running, you can access the following endpoints:

- `POST /upload`: Endpoint for file uploads to Alibaba Cloud OSS.
- Other RESTful endpoints as defined in your controller classes.

## Contributing

Contributions to the project are welcome. Please adhere to this simple guideline:
- Fork the repository and create your branch from `master`.
- If you've added code that should be tested, add tests.
- Ensure your code lints.
- Issue that pull request!

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE.md) file for details.

## Acknowledgments

- Thanks to the Spring Boot team for the comprehensive framework.
- Thanks to Alibaba Cloud for the OSS functionalities.
- Thanks to all contributors who spend time to help improve this project.


This README file is structured to guide new users and contributors through setting up, running, and participating in the development of the project. Make sure to replace placeholders and add any specific instructions related to your project for better clarity and accuracy.
