# demo-springboot-app

Demo Spring Boot application (Java 8 compatible) with:
- Layered architecture (Controller → Service → Repository)
- DTOs and validation
- Global exception handler
- HTTP Basic security (in-memory user)
- Resilience4j circuit breaker demo
- H2 in-memory database

## Build & Run
mvn clean package
java -jar target/demo-springboot-app-0.0.1-SNAPSHOT.jar

## Endpoints
- GET /api/customers
- GET /api/customers/{id}
- POST /api/customers
- PUT /api/customers/{id}
- DELETE /api/customers/{id}
- GET /api/ping-external  (demonstrates circuit breaker fallback)

## Auth
Basic auth username/password from application.properties (demo/demo123)

## Notes
- Resilience4j settings are in application.properties. The external call points to a non-existing endpoint to demonstrate fallback behavior.
