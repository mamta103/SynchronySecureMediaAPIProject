# SynchronySecureMediaAPI

A secure Spring Boot REST API designed for media upload and user management, featuring JWT-based authentication, H2 database, Kafka messaging, and GitHub CI/CD integration.

---

## 🚀 Features

- **User Registration & Authentication**
- **JWT Security (Optional: Easily pluggable)**
- **Image Upload Endpoint**
- **Spring Security Integration**
- **Kafka Integration**
- **H2 In-Memory/File-based Database**
- **Maven Build + GitHub Actions CI/CD**

---

## Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Security
- JWT
- Kafka
- H2 Database
- Maven
- GitHub Actions

---

## API Endpoints

| Endpoint                     | Method | Description                   |
|-----------------------------|--------|-------------------------------|
| `/api/users/register`       | POST   | Register a new user          |
| `/api/authenticate`         | POST   | Authenticate and receive JWT |
| `/api/images/upload`        | POST   | Upload an image (secured)    |
| `/h2-console`               | GET    | Access H2 DB Console         |

---

## Security

- Users must authenticate to access protected endpoints.
- JWT is issued on login and used for secure communication.
- H2 console access is publicly permitted for development.

---

##Setup & Run

### Prerequisites

- Java 17+
- Maven
- Kafka (Optional, for full feature use)

### Run Locally

```bash
git clone https://github.com/mamta103/SynchronySecureMediaAPIProject.git
cd SynchronySecureMediaAPIProject
mvn clean install
mvn spring-boot:run
