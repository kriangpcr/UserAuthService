## Project Overview
This project provides a **User Management API** with features like user registration, login, token refresh, and CRUD operations for user-related data. The system also includes unit tests for key functionalities.

### Requirements

1. Java: JDK 17+
2. Maven: 3.8+
3. Database: PostgreSQL (configure in application.properties)

### Features
- **API Endpoints**:  
  - `POST /user/register`: Register a new user.  
  - `POST /user/login`: User login with token generation.  
  - `GET /user/refresh-token`: Refresh the authentication token.

- **Unit Tests**:  
  Validates user creation, update, social and address creation, and user deletion.

### Technologies
- **Backend**: Spring Boot
- **Testing**: JUnit 5

### How to Run
1. Clone the repository.
2. Build the project: `./mvnw clean install`
3. Run tests: `./mvnw test`
4. Start the server: `./mvnw spring-boot:run`
