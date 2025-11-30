Spring Boot Product API

📋 Overview

This project is a RESTful API developed using Spring Boot. It provides CRUD (Create, Read, Update, Delete) operations for a Product resource. The application is built with best practices in mind, featuring input validation, global error handling, database persistence via JPA, and interactive API documentation using Swagger (OpenAPI).

🚀 Features

RESTful Endpoints: Full CRUD support for Product resources.

Database Persistence: Uses Spring Data JPA with PostgreSQL (configurable for MySQL).

Validation: JSR 380 (Hibernate Validator) implementation to ensure data integrity.

Global Exception Handling: Centralized error management using @ControllerAdvice for consistent API responses.

Documentation: Integrated Swagger UI for API visualization and testing.

🛠️ Tech Stack

Language: Java 21

Framework: Spring Boot 4.0

Build Tool: Maven

Database: PostgreSQL (Compatible with MySQL)

Documentation: Springdoc OpenAPI (Swagger UI)

⚙️ Prerequisites

Before running the application, ensure you have the following installed:

Java Development Kit (JDK) 21 or higher

Maven 3.6+

PostgreSQL (or MySQL)

🔧 Configuration

Clone the repository:

git clone [https://github.com/your-username/spring-product-api.git](https://github.com/your-username/spring-product-api.git)
cd spring-product-api


Database Setup:
Create a database named product_db in your PostgreSQL instance.

Application Properties:
Navigate to src/main/resources/application.properties and update your database credentials:

# Database Configuration (PostgreSQL)
spring.datasource.url=jdbc:postgresql://localhost:5432/product
spring.datasource.username=your_username
spring.datasource.password=your_password

# Hibernate ddl-auto
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


Note: If using MySQL, change the driver class name and URL in the properties file.

🏃‍♂️ Running the Application

You can run the application using the Maven wrapper or your IDE.

Using Command Line:

mvn spring-boot:run


The application will start on http://localhost:8080.

📖 API Documentation (Swagger)

Once the application is running, you can access the interactive Swagger UI to test the endpoints directly:

👉 Swagger UI URL: http://localhost:8080/swagger-ui/index.html

🛣️ Endpoints

Method

Endpoint

Description

GET

/api/products

Retrieve all products

GET

/api/products/{id}

Retrieve a specific product by ID

POST

/api/products

Create a new product

PUT

/api/products/{id}

Update an existing product

DELETE

/api/products/{id}

Delete a product

Sample JSON Payload (POST/PUT)

{
  "name": "Wireless Headphones",
  "description": "Noise cancelling over-ear headphones",
  "price": 1999.99
}


🛡️ Validation & Error Handling
The API implements strict validation:
Name: Cannot be empty.

Price: Must be a positive number.

If validation fails, the API returns a 400 Bad Request with a structured error message detailing which fields failed validation.
