# Ebook library management system
Spring Boot Monolithic Application with secured RESTful endpoints.

## Technologies :bulb:
- **Java 11**
- **Maven**
- **Spring Boot**
- **Spring Web**
- **Spring Data**
- **Spring Security** + **JWT**
- **PostgreSQL**
- **Flyway**
- **JUnit** + **AssertJ** + **Mockito**
- **Slf4j**
- **Lombok**
- **Swagger**

## Deployment :rocket:
Open terminal for running bash scripts (for example Git Bash) and type `bash start.sh`

(The application should start locally on port 8070)


## Security :closed_lock_with_key:
*Public access URIs* - Swagger documentation, registration, login.

*All other URIs* - **access only with a valid JWT token** (which you can get after registration/login).

**ROLE_ADMIN** - CRUD operations (POST, GET, PUT, DELETE)

**ROLE_STAFF** - CRU operations (POST, GET, PUT)

**ROLE_USER**  - R operations (GET)


## API Documentation 📄
![books](docs/docs-books.png)

![authors](docs/docs-authors.png)

![accounts](docs/docs-accounts.png)

![roles](docs/docs-roles.png)
