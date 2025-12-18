# Fitness Tracker API

A Spring Boot-based RESTful API designed for managing fitness tracker data. This platform allows developers to register their applications, obtain API keys, and securely submit or retrieve fitness-related activities.

## Features

- **Developer Management**: Register developer accounts and manage profiles.
- **Application Registration**: Developers can register multiple fitness applications, each with its own unique API key and category (e.g., basic, premium).
- **Fitness Data Tracking**: 
    - Store fitness activities including username, activity type, duration, and calories burned.
    - Retrieve a history of fitness data associated with a specific application.
- **Security**:
    - **Basic Authentication**: Protects developer and application registration endpoints.
    - **API Key Authentication**: Tracker endpoints require a valid `X-API-Key` header.
- **Rate Limiting**: Integrated rate limiting mechanism to ensure fair API usage (1 request per second for data retrieval).
- **System Monitoring**: Includes Spring Boot Actuator for health checks and management.

## Technologies Used

- **Java 17+**
- **Spring Boot 3.x**
    - **Spring Web**: For building RESTful controllers.
    - **Spring Security**: For Basic and API Key-based authentication.
    - **Spring Data JPA**: For object-relational mapping and database interactions.
    - **Spring Boot Actuator**: For application monitoring.
    - **Spring Validation**: For request body validation.
- **H2 Database**: An in-memory/file-based database used for data persistence.
- **Gradle**: Build automation and dependency management.

## API Endpoints

### Developer & Application Management (Requires Basic Auth)
- `POST /api/developers/signup`: Create a new developer account.
- `GET /api/developers/{id}`: Retrieve developer details and registered applications.
- `POST /api/applications/register`: Register a new fitness application.

### Tracker API (Requires `X-API-Key` Header)
- `POST /api/tracker`: Submit new fitness data.
- `GET /api/tracker`: Retrieve all fitness data for the application (Rate-limited).

### Management
- `POST /actuator/shutdown`: Gracefully shut down the application.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 17 or higher.
- Gradle (optional, as the project includes a wrapper).
- The API will be available at `http://localhost:8080`.

### Database Access
The application uses an H2 database. You can access the H2 console at `http://localhost:8080/h2-console` with the following credentials (defined in `application.properties`):
- **JDBC URL**: `jdbc:h2:file:../fitness_db`
- **User**: `sa`
- **Password**: `sa`

## Project Structure
- `src/fitnesstracker/controller`: REST controllers handling incoming requests.
- `src/fitnesstracker/entity`: JPA entities for Users, Apps, and Fitness Data.
- `src/fitnesstracker/repository`: Data access interfaces.
- `src/fitnesstracker/service`: Business logic layer.
- `src/fitnesstracker/configuration`: Security and global exception handling configurations.
