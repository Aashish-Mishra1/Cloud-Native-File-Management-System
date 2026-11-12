# Cloud-Native File Management System

A full-stack cloud-native file management application built with Spring Boot, Angular, and AWS S3.

## Tech Stack

**Backend**
- Spring Boot, Spring Security, Spring Data JPA
- Apache Kafka (message broker)
- PostgreSQL
- JWT authentication
- Amazon S3

**Frontend**
- Angular, TypeScript, HTML, CSS

## Architecture

```
Angular (4200)  →  Spring Boot Server (8080)  →  AWS S3
                          ↓ Kafka topic: "greeting"
                    Mail Sender Service (8083)
                          ↓
                      Gmail SMTP
```

## Setup

### Prerequisites
- Java 17
- Node.js & npm
- PostgreSQL
- Docker (for Kafka)
- AWS account with S3 bucket

### 1. Clone the repository

```bash
git clone https://github.com/Aashish-Mishra1/Cloud-Native-File-Management-System
```

### 2. Start Kafka

```bash
docker-compose up -d
```

### 3. Create PostgreSQL database

```sql
CREATE DATABASE fs;
```

### 4. Configure the server

Open `server/src/main/resources/application.yml` and set:

```yaml
spring:
  datasource:
    username: your-postgres-username
    password: your-postgres-password

cloud:
  aws:
    credentials:
      access-key: your-aws-access-key
      secret-key: your-aws-secret-key
    bucket:
      name: your-s3-bucket-name

jwt:
  token:
    secret: your-jwt-secret
```

### 5. Configure the mail sender

Open `mail-sender/src/main/resources/application.yml` and set:

```yaml
spring:
  mail:
    username: your-email@gmail.com
    password: your-gmail-app-password
```

> Use a [Google App Password](https://myaccount.google.com/apppasswords), not your regular Gmail password.

### 6. Run the server

```bash
cd server
mvn spring-boot:run
```

API runs at: http://localhost:8080  
Swagger UI: http://localhost:8080/swagger-ui/index.html

### 7. Run the mail sender

```bash
cd mail-sender
mvn spring-boot:run
```

### 8. Run the Angular client

```bash
cd client
npm install
ng serve
```

App runs at: http://localhost:4200

## API Endpoints

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | `/api/v1/user` | Public | Register — sends verification email |
| GET | `/api/v1/user/verify` | Public | Verify email and create account |
| POST | `/api/v1/auth` | Public | Login |
| POST | `/api/v1/auth/refresh-token` | Header | Refresh access token |
| GET | `/api/v1/user/find?id=` | JWT | Get user by ID |
| GET | `/api/v1/user` | JWT | Get all users |
| DELETE | `/api/v1/user/{id}` | JWT | Delete user |
| POST | `/api/v1/storage/save` | JWT | Upload file to S3 |
| GET | `/api/v1/storage/download/{fileName}` | JWT | Download file from S3 |
| DELETE | `/api/v1/storage/{id}/{fileName}` | JWT | Delete file from S3 |
