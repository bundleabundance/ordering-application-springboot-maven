# Ordering Application

The Ordering Application is a Spring Boot project developed to manage products, customers, carts, and orders. The application provides functionalities like adding products, creating customers, managing carts, placing orders, and more.

## Dependencies

- **Spring Boot**: A powerful and flexible framework for building Java-based enterprise applications.
- **Jakarta Persistence (JPA) / Hibernate**: Used for database persistence and entity management.
- **Maven**: A build automation tool for managing dependencies and building the project.
- **H2 Database**: An in-memory database for development and testing purposes.
- **Spring Web**: Provides HTTP request handling, making it suitable for building RESTful APIs.
- **Spring Data**: Simplifies data access and manipulation using a repository abstraction.

## Project Structure

The project is structured with separate packages for entities, repositories, services, and controllers. The main components include:

- **Entities**: Represent database tables and include Product, Customer, Cart, Order, etc.
- **Repositories**: Interface for database operations, such as ProductRepository, CustomerRepository, etc.
- **Services**: Contain business logic for managing products, customers, carts, and orders.
- **Controllers (not implemented)**: Would handle HTTP requests if API endpoints were developed.

## Getting Started

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/bundleabundance/ordering-application-springboot-maven.git
    ```

2. **Build and Run the Application**:
    ```bash
    cd ordering-application
    ./mvnw spring-boot:run
    ```

3. **Access the H2 Database Console**:
    URL: http://localhost:8080/h2-console
    JDBC URL: jdbc:h2:mem:testdb
    Username: sa
    Password: (leave it blank)

## Notes

This project does not include API endpoints or a frontend. It's primarily designed for backend logic and database interactions.
H2 Database is used for development. For production, consider configuring a persistent database.

