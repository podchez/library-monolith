# Ebook library management system
Spring Boot Monolithic Application with secured RESTful endpoints.

## Technologies :bulb:
- **Java 11**
- **Maven**
- **Spring (Boot, Web, Data, Security, Test)**
- **Hibernate**
- **PostgreSQL**
- **Flyway**
- **Docker**, **Docker Compose**, **Bash**
- **CI/CD** pipeline with GitHub Actions
- **JUnit**, **AssertJ**, **Mockito**, **H2 Database**
- **Slf4j**
- **Lombok**
- **Swagger**

## Deployment :rocket:
Required software:
- terminal for running bash scripts (for example, Git Bash)
- docker

Steps:
1) Clone this repository `git clone https://github.com/podchez/library-monolith.git`
2) From the root folder of the project - **type in the terminal `bash start.sh`**

(the application should start locally on port 8070)


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
