# 🚀 Product and Category Management API 📦

This project is a **REST API** developed using **Spring Boot**, designed for managing products and categories. It supports authentication and authorization, implements role-based access control, and offers an environment-based configuration for seamless development and deployment.

## 📋 Table of Contents

1. [Project Context](#-project-context)
2. [Features](#-features)
3. [Technologies Used](#-technologies-used)
4. [Architecture Overview](#-architecture-overview)
5. [Environment Configuration](#-environment-configuration)
6. [Docker Setup](#-docker-setup)
7. [How to Run](#-how-to-run)
8. [Testing and Validation](#-testing-and-validation)

## 🎯 Project Context

As a Full Stack Developer, the objective is to build a secure and containerized application with:

- 🔐 Stateful authentication using `JdbcAuthentication`
- 👥 Role-based access (`ADMIN`, `USER`) for accessing endpoints
- 🌐 Environment-based configurations (`dev` and `prod`) with `.env` for environment variables
- 🚢 Full support for CI/CD readiness 

## ✨ Features

### 📦 Product Management
- **USER/ADMIN**:
    - 📋 List products with pagination and sorting
    - 🔍 Search products by designation, category
    - 🌈 Filter products by category with pagination and sorting
- **ADMIN Only**:
    - ➕ Add, update, and delete products

### 📁 Category Management
- **USER/ADMIN**:
    - 📋 List categories with pagination and sorting
    - 🔍 Search categories by name
    - 🌈 List products in a category with pagination and sorting
- **ADMIN Only**:
    - ➕ Add, update, and delete categories

### 👤 User Management
- 🔑 Authentication via `/api/auth/login`
- 📝 Account creation via `POST /api/auth/register`
- **ADMIN Only**:
    - 👥 List users and manage roles

### 🛡️ Security
- **Stateful Authentication**: Based on sessions, with credentials stored in MariaDB
- **Password Encryption**: Using `BCryptPasswordEncoder`
- **Profiles**:
    - `dev`: Security bypass for easier development
    - `prod`: Full security enabled

## 🛠️ Technologies Used

- **Spring Boot**: For API development
- **Spring Security**: For stateful authentication and role-based access
- **Spring Data JPA**: For database operations
- **MariaDB**: As the database engine
- **Docker**: For containerization
- **Swagger**: For API documentation
- **JUnit & Mockito**: For testing
- **Lombok**: To reduce boilerplate code
- **.env**: For managing environment variables

## 🏗️ Architecture Overview

- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Implements business logic
- **Repository Layer**: Provides database access using JPA
- **DTO & Mapper**: Used for transforming entities to data transfer objects
- **Validation**: Ensures data integrity using Bean Validation

## 🔧 Environment Configuration

This project uses a `.env` file to manage environment variables. Below are the required configurations:

```env
SPRING_PROFILES_ACTIVE=dev
DATABASE_NAME=your_db_name
DATABASE_USER=your_db_user
DATABASE_PASSWORD=your_db_password
DB_PWD=root_password
```

### 🌐 Profiles:
- `dev`: Bypasses security for development
- `prod`: Enables security with database authentication

## 🐳 Docker Setup

### Prerequisites
- 🖥️ Docker installed on your system
- 📄 `.env` file configured as mentioned above

### Steps
1. 🔽 Clone the repository:
```bash
git clone https://github.com/Radiaidel/ProdCat-Manager.git
cd ProdCat-Manager
```

2. 🚀 Build and run the containers:
```bash
docker-compose up --build
```

3. 🌐 Access the application at http://localhost:8080

## 🧪 Testing and Validation

- 🔍 Unit & Integration Tests: Run with `mvn test`
- 🌐 Postman: Use provided API endpoints for manual testing
- 📖 Swagger: Access API documentation at http://localhost:8080/swagger-ui.html
