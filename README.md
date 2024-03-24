# AeroReserve Ticket Booking Service

This repository contains the source code for the ticket booking service, which is a Java Spring Boot application.

It is updated to Java 17 and uses the latest version of Spring Boot. Furthermore, the necessary dependencies have been updated to the latest versions.

Dependencies which have been added are the following:
- AWS Spring Boot Starter
- AWS Spring Boot Starter Secrets Manager

The application uses the AWS SDK to retrieve the secret from AWS Secrets Manager at run-time. The secret is used to connect to the AMQP broker and Camunda. 

The following secret is expected to be present in AWS Secrets Manager:
- `ticket-booking-service-credentials`

Which should contain the following key-value pairs:
- zeebe.client.cloud.region
- zeebe.client.cloud.clusterId
- zeebe.client.cloud.clientId
- zeebe.client.cloud.clientSecret
- spring.rabbitmq.host
- spring.rabbitmq.port
- spring.rabbitmq.username
- spring.rabbitmq.password
- spring.rabbitmq.ssl.enabled = true
- spring.rabbitmq.addresses
- ticket-generation-service-endpoint

# Requirements

To run the application, you need to have the following installed:
- Java 17
- Maven
- AWS CLI
- Elastic Beanstalk CLI

The application is only able to process requests if the following services are running:
- Amazon MQ (RabbitMQ broker)
- Camunda (Workflow engine)
- Seat Reservation Service (Zeebe worker)
- Payment Service (Lambda function)
- Ticket Generation Service (Lambda function)

# Running the application

To run the application, you can use the following command:
```
mvn spring-boot:run
```

# Deployment

To deploy the application, first build the application using: 
```
mvn clean package spring-boot:repackage
```

We then have to create a new Elastic Beanstalk environment using the following command:
```
eb create
```

And finally, we’ll include two environmental variables in Elastic Beanstalk. The first one will specify the active Spring profiles, and the second one will ensure the use of the default port 5000 expected by Beanstalk:

```
eb setenv SPRING_PROFILES_ACTIVE=beanstalk SERVER_PORT=5000
```

Then deploy the application using the following command:

```
eb deploy
```

This will deploy the application to AWS Elastic Beanstalk based on the configuration in the `.elasticbeanstalk` folder.

We can then check the status of the deployment using the following command:

```
eb status
```

# Test 

To test the application, you can use the following curl commands:
```
curl -i -X PUT http://localhost:8080/ticket
```
Simulate failures by:
```
curl -i -X PUT http://localhost:8080/ticket?simulateBookingFailure=seats
curl -i -X PUT http://localhost:8080/ticket?simulateBookingFailure=ticket
```
These executions also become visible in Camunda. 