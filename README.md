Calculator Project

Overview
The Calculator Project is a Java-based application built with Spring Boot. 
It provides a flexible and extensible calculator service that supports basic arithmetic operations: addition, subtraction, multiplication, and division. The project also includes error handling for invalid inputs and division by zero.

Features
Supports basic arithmetic operations: ADD, SUBTRACT, MULTIPLY, DIVIDE
Handles errors such as division by zero and invalid input
Extensible design using Factory and Strategy design patterns
Well-structured for maintainability and testing

Project Structure
com.example.calculator: The main package containing the application logic.
controller: Contains REST controllers for exposing calculator endpoints.
model: Contains enums and data models.
service: Contains business logic and services.
service.operations: Contains operation-related classes and the factory for operations.
exception: Contains custom exceptions.

Requirements
Java 18 or higher
Maven

Setup and Installation

Clone the Repository

git clone https://github.com/yourusername/calculator-project.git
cd calculator-project
Build the Project

Using Maven:
mvn clean install

Running the Application

To run the Spring Boot application, use the following command:
mvn spring-boot:run
The application will start and be available at http://localhost:8080.

API Endpoints
http://localhost:8080/api/calculator/calculate?operation=MULTIPLY&num1=5&num2=3
Method: GET
Description: Perform a calculatin based on the provided operation and num1 and num2

http://localhost:8080/api/calculator/chain-calculate?initialValue=5
Method: POST
Request Body:
{
"ADD": 5,
"SUBTRACT": 3
}
Description: Perform a chain calculation starting with initailValue and provided json body which is a m
map of operation and number 


200 OK: Returns the result of the calculation.
400 Bad Request: Returns error details for invalid inputs or operations.


Running Tests
mvn test