# 🎓 Student Management System

> A full-featured **Admin Panel** for managing students — includes CRUD operations, admin authentication (login/logout), and a clean responsive UI built with Spring Boot.

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/your-username/StudentManagementSystem)
![Java](https://img.shields.io/badge/java-21-blue)
![Spring Boot](https://img.shields.io/badge/spring--boot-3.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/postgresql-15+-blue)

---

## ✨ Features

- 🔑 **Admin Authentication** — secure login/logout with Spring Security  
- 📝 **Student Management** — Create, Read, Update, Delete students  
- ✅ **Form Validation** — input validation for clean data  
- 🔒 **Role-Based Access** — only admins can access management pages  
- ⚡ **Simple, Extensible Architecture** — clean package structure, easy to maintain  

---

## 🛠️ Tech Stack

- **Language:** Java 21+  
- **Framework:** Spring Boot (Web, Data JPA, Security)  
- **Database:** PostgreSQL 
- **Build Tool:** Maven (wrapper included: `mvnw`)  
- **View Layer:** Thymeleaf (server-side templates)  

---

## 🚀 Getting Started

### 1. Prerequisites
- Java 21+ installed → `java -version`  
- PostgreSQL installed & running  
- (Optional) Maven installed → but you can use the included wrapper (`./mvnw`)  

### 2. Clone the Repository
```bash
git clone https://github.com/mohamedtoukhy03/StudentManagementSystem.git
cd StudentManagementSystem
```

## 3. Project Structure

```plaintext
.mvn/                                   # Maven wrapper files
sql_scripts/                            # SQL scripts for creating tables / seeding data
src/
  main/
    java/com/School/StudentManagementSystem/
      handler/                           # Exception handling
      controller/                       # Controllers (handle web requests)
      repository/                              # Data Access Objects (repositories)
      entity/                           # JPA entities (Student, Admin, etc.)
      exception/                        # Custom exception classes
      security/                         # Spring Security config
      service/                          # Business logic services
      StudentManagementSystemApplication.java   # Main Spring Boot app
    resources/
      static/
        css/                            # CSS files
        javaScript/                      # JavaScript files
        index.html                      # Frontend entry page
      templates/                        # Thymeleaf templates
      application.properties            # App configuration
  test/
    java/com/School/StudentManagementSystem/    # Unit & integration tests
.gitattributes
.gitignore
mvnw                                    # Maven wrapper (Linux/macOS)
mvnw.cmd                                # Maven wrapper (Windows)
pom.xml                                 # Maven dependencies & project config
```

## 4. Setup Database (PostgreSQL)

```sql
CREATE DATABASE "School"
    WITH
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1

-- Connect to the new database
\c "School";

-- Students table
CREATE TABLE students (
    id GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(15) NOT NULL,
    second_name VARCHAR(15) NOT NULL
);

-- Admins table
CREATE TABLE admins (
    email VARCHAR(100) PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);
```

## 5. Configure Application

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/School
spring.datasource.username=db_username
spring.datasource.password=db_password
```

## 6. Run the Application

Using Maven wrapper:

```bash
# Build & run
### Linux / macOS
./mvnw spring-boot:run
### windows
mvnw.cmd spring-boot:run
```

## 7. Contributing

Contributions are welcome! Follow these steps:

### 1. Create a new branch for your feature or bugfix
```bash
git checkout -b feature-name
```
### 2. Make your changes and commit them
```bash
git add .
git commit -m "Added feature: description"
```

### 3. Push your branch to your forked repository
```bash
git push origin feature-name
```

### 4. Open a Pull Request (PR)
Go to the original repository on GitHub

Create a PR from your branch

## 8. Using the Application

- Open your browser and visit 👉 [http://localhost:8080](http://localhost:8080)  
- Login with an admin account you created in the database  
- Start managing students (CRUD features available after login)

## 📄 License

This project is licensed under the [MIT License](LICENSE).

