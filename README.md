# crud-product-spring-boot
Crud Project With Spring Boot

# 🛍️ CRUD Product API - Spring Boot

A RESTful API for managing products, built with **Spring Boot**, **JPA**, **H2**, **Swagger**, and tested using **JUnit 5** and **Mockito**.

---

## 🚀 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (for testing)
- Swagger/OpenAPI
- Jakarta Validation
- JUnit 5 + Mockito
- Postman (manual testing)

---

## 📦 Features

- `GET /products` → List all products
- `GET /products/{id}` → Retrieve a product by ID
- `POST /products` → Create a new product
- `PUT /products/{id}` → Update an existing product
- `DELETE /products/{id}` → Delete a product

---

## 📄 Validation Rules

- **Name**: required (`@NotBlank`)
- **Price**: minimum `1.00`, maximum `10000.00`
- **Quantity**: minimum `0`, maximum `1000`

Error messages are standardized via `ValidationMessages.properties`.

---

## 🧪 Testing

- **Unit tests**: `ProductServiceTest` using Mockito
- **Integration tests**: `ProductServiceIntegrationTest` with H2 and Spring context
- **Validation tests**: handled via `@Valid` and `GlobalExceptionHandler`

---

## 📚 API Documentation

Access the Swagger UI at:
http://localhost:8081/swagger-ui/index.html

---

## 🐳 Docker Support

You can run the application using Docker:

```bash
# Build the image
docker build -t crud-product-api .

# Run the container
docker run -p 8080:8080 crud-product-api



Código

---

## 🛠️ How to Run the Project

```bash
# Clone the repository
git clone https://github.com/your-username/crud-product-spring-boot.git

# Navigate into the project folder
cd crud-product-spring-boot

# Run with Maven
./mvnw spring-boot:run


🧰 Requirements
Java 17+

Maven 3.8+

🤝 Contributions
Contributions are welcome! Feel free to open issues or submit pull requests.

🧠 Author
Developed by André — passionate about clean code, solid testing, and well-documented APIs.




