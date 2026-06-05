# Product Demo API

A RESTful API for product management built with Spring Boot, secured with JWT authentication and backed by PostgreSQL.

## Tech Stack

- **Java 17**
- **Spring Boot 4.0.6**
- **Spring Security** + **JWT** (jjwt 0.12.6)
- **Spring Data JPA** + **Hibernate**
- **PostgreSQL**
- **Lombok**
- **Maven**

## Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL running on `localhost:5432`

## Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/trinhkhanhltc/product-demo.git
   cd product-demo
   ```

2. **Create the database**
   ```sql
   CREATE DATABASE product_db;
   ```

3. **Configure credentials** in `src/main/resources/application.yaml`
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/product_db
       username: postgres
       password: your_password

   jwt:
     secret: "mySecretKey12345mySecretKey12345mySecretKey12345"  # min 32 chars
     expiration: 86400000  # 24 hours in ms
   ```

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```
   The server starts on `http://localhost:8080`.

## API Endpoints

All endpoints are prefixed with `/api/products` and require a valid JWT token in the `Authorization: Bearer <token>` header.

| Method | Endpoint              | Description         |
|--------|-----------------------|---------------------|
| GET    | `/api/products`       | Get all products    |
| GET    | `/api/products/{id}`  | Get product by ID   |
| POST   | `/api/products`       | Create a product    |
| PUT    | `/api/products/{id}`  | Update a product    |
| DELETE | `/api/products/{id}`  | Delete a product    |

### Product Schema

```json
{
  "name": "Product Name",
  "description": "Product description",
  "price": 99.99,
  "quantity": 10
}
```

### Example Requests

**Create a product**
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{"name":"Laptop","description":"Gaming laptop","price":1500.00,"quantity":5}'
```

**Get all products**
```bash
curl http://localhost:8080/api/products \
  -H "Authorization: Bearer <token>"
```

**Update a product**
```bash
curl -X PUT http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{"name":"Laptop Pro","description":"Updated","price":1800.00,"quantity":3}'
```

**Delete a product**
```bash
curl -X DELETE http://localhost:8080/api/products/1 \
  -H "Authorization: Bearer <token>"
```

## Project Structure

```
src/
└── main/
    └── java/com/example/product/
        ├── config/          # Security configuration
        ├── controller/      # REST controllers
        ├── dto/             # Request/Response DTOs
        ├── entity/          # JPA entities
        ├── exception/       # Global exception handling
        ├── repository/      # Spring Data JPA repositories
        ├── security/        # JWT service
        └── service/         # Business logic
```

## Build

```bash
./mvnw clean package
java -jar target/product-0.0.1-SNAPSHOT.jar
```
