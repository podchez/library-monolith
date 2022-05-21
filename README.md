# Ebook library management system
Spring Boot Monolithic Application with secured RESTful endpoints.

![books picture](https://images.vexels.com/media/users/3/205462/isolated/lists/87b34912ed9f8d2900754c38220faac6-pile-of-books-illustration.png)

## About :bulb:
Backend part of the ebook library management system.

Features:
- CRUD operations with accounts, roles, books, authors, genres
- REST API with secured endpoints for these CRUD operations
- 3 roles for authorization: ADMIN, STAFF, USER

## Deployment :rocket:
- Clone this repository `git clone https://github.com/podchez/library-monolith.git`
- From the root folder of the project type `./mvnw clean install` `./mvnw spring-boot:run`
- The application should start locally on `http://localhost:8080/`

## Documentation 📄
- http://localhost:8080/api/v1/books  (GET, POST)
- http://localhost:8080/api/v1/books/{id}  (GET, PUT, DELETE)
---
- http://localhost:8080/api/v1/authors  (GET, POST)
- http://localhost:8080/api/v1/authors/{id}  (GET, PUT, DELETE)
---
- http://localhost:8080/api/v1/genres  (GET, POST)
- http://localhost:8080/api/v1/genres/{id}  (GET, PUT, DELETE)
---
- http://localhost:8080/api/v1/accounts  (GET, POST)
- http://localhost:8080/api/v1/accounts/{id}  (GET, PUT, DELETE)
---
- http://localhost:8080/api/v1/roles  (GET, POST)
- http://localhost:8080/api/v1/roles/{id}  (GET, PUT, DELETE)
