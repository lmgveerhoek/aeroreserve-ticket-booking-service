# README

This repository contains the source code for the ticket booking service, which is a Java Spring Boot application.

It is updated to Java 17 and uses the latest version of Spring Boot. Furthermore, the necessary dependencies have been updated to the latest versions.

Dependencies which have been added are the following:
- AWS Spring Boot Starter
- AWS Spring Boot Starter Secrets Manager

The application is deployed to AWS Elastic Beanstalk.

# Deploy Manually

To deploy the application manually, first build the application using: 
```
mvn clean package spring-boot:repackage
```

Then deploy the application using the following command:
```
eb deploy
```