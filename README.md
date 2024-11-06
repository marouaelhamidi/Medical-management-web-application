# Medical-management-web-application
Medical Management Web App A web application for managing doctor and patient accounts and scheduling medical appointments, built with Spring Boot, Hibernate, JPA, REST, Thymeleaf, and JavaFX. It features secure account management, appointment booking, and an AI-powered chatbot for quick, interactive support.

 Project Objectives
Appointment Management: Simplify the appointment booking process between patients and doctors.

User Account Management: Allow users (patients and doctors) to securely manage their personal information. Patients can update their contact details, medical history, etc.

Doctor Account Management: Provide doctors with an interface to manage their schedules, patients, and availability. Doctors can also review the appointment history and patient details.

III. Technologies Used
Spring Boot

Definition: Spring Boot is an open-source framework for developing Java applications. It simplifies the process of creating new Spring applications by providing a pre-configured setup, enabling developers to focus on business logic instead of infrastructure configuration.

Dependencies:

spring-boot-starter-data-jpa: For interaction with the database using JPA (Java Persistence API).
spring-boot-starter-thymeleaf: For rendering web pages using the Thymeleaf template engine.
spring-boot-starter-web: For creating RESTful web applications.
spring-boot-starter-security: For managing application security.
spring-boot-devtools: For development with automatic reload and other tools.
spring-boot-starter-test: For unit and integration testing.
spring-boot-starter-mail: For sending emails.
spring-boot-starter-tomcat: For integrating Tomcat as an embedded servlet container.
Database

mysql-connector-java: To connect the application to a MySQL database.
Security and Validation

org.json: For handling JSON objects.
Other Tools and Libraries

nekohtml: For processing HTML documents.
okhttp: For making HTTP requests.
javax.annotation-api: For Java annotations.
spring-context: For Spring's IoC container functionalities.
JavaFX

JavaFX is a Java library for creating rich internet applications (RIAs) with modern and interactive graphical user interfaces (GUIs). It is commonly used for desktop applications.
Python

Python is an interpreted, interactive, and object-oriented programming language. It is widely used in web development, data science, artificial intelligence, automation, and more.
Hugging Face

Hugging Face is a company specializing in developing natural language processing (NLP) models and open-source libraries. Their transformers library is particularly popular for working with transformer models like BERT, GPT, and Blenderbot.
IV. Implementation
System Architecture The diagram below illustrates the global architecture of the application, detailing the various layers and components used:

a) Presentation Layer This layer is responsible for user interactions (patients, doctors, and administrators). It includes the following interfaces:

Web Client Service:

Technologies: REST
Features: Deployment of the Hugging Face model via Python using a REST API.
Lightweight Web Client:

Technologies: Thymeleaf, HTML, CSS, JavaScript
Features: User interface for patients, doctors, and administrators. Manages accounts, appointment booking, availability viewing.
Rich JavaFX Client:

Technologies: JavaFX
Features: ChatBot interface using JavaFX.
Web Container:

Technologies: Spring MVC, Spring Boot
Features: Handles HTTP requests via Spring controllers.
RMI Server:

Technologies: RMI (Remote Method Invocation)
Features: Manages remote services for communication between application components. RMI is used to connect the Spring Boot application with the Python model.
JPA Repository:

Technologies: Spring Data JPA
Features: Manages CRUD operations for entities (Admin, User, Appointment).
Hibernate:

Technologies: Hibernate ORM
Features: Maps Java objects to database tables.
Database Management System (DBMS):

Technologies: MySQL
Features: Stores user and appointment information.
Roles and Functionalities

User (Patient):
Creates appointments.
Uses the chatbot for instant assistance and support.

