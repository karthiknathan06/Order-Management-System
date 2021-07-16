# Order Processing System

An API which is used manages user/products, order processing and confirmation.
The stacks used 
- Java Spring Boot Framework
- Apache Kafka
- Junit
- MongoDB cloud Atlas
## Use cases

- Manages User Details, Product details, Order details service
- Secure authentication/authorization using Spring security/ JWT
- User can get list of products
- User can order products
- User can check the status of order


Validation Service [Link](https://github.com/karthiknathan06/validator-service) 
Notification Service [Link](https://github.com/karthiknathan06/email-notifier-service) 


![alt text](https://github.com/karthiknathan06/Order-Management-System/blob/main/architectureDiagram.jpg)


How to run!

1. `mvn install` or use IDE and update project maven dependencies 
2. Init kafka producer and consumer and create kafka topic and mark respective entries in application.properties
3. Start the spring project validation service and order processing service and mail service

Future enhancement

This solution can be expanded by using below stacks 

1. Interactive kafka streams api
2. Split into microservices for code maintainability and scalability

![alt text](https://github.com/karthiknathan06/Order-Management-System/blob/main/FutureEnhancement.jpg)

Initiated the project for microservices [Link](https://github.com/karthiknathan06/Retail-Order-Management-System/tree/master) Also refer this

<li>Implemented Unit Testing for controllers using mockito and spring unit testing packages.</li> <br>
<li>Integration Testing can also be done for mongodb and Kafka using embedded services or Test Containers</li>
