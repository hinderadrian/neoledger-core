# 🏦 NeoLedger Core

A high-performance Double-Entry Accounting Engine designed as a blueprint for learning the evolution of the Java ecosystem.

## 🚀 Project Vision
NeoLedger is not just a wallet; it is a professional-grade accounting system. It follows the double-entry principle where every financial movement is a balanced transaction between two or more accounts.

## 🛠️ Tech Stack (Baseline)
- **Language**: Java 8 (Targeted for upgrade practice)
- **Framework**: Spring Boot 3.x
- **Database**: PostgreSQL 16
- **Cache**: Redis
- **Migrations**: Flyway
- **Observability**: Prometheus, Grafana, Spring Actuator
- **API**: REST v1, OpenAPI/Swagger

## 📐 Architecture
The project follows a **Layered Monolith** approach to facilitate a future migration to a Modular Monolith (Spring Modulith):

`API Layer` $\rightarrow$ `Application Layer` $\rightarrow$ `Domain Layer` $\rightarrow$ `Infrastructure Layer`

## 🛠️ Getting Started

### 1. Prerequisites
- Docker and Docker Compose
- JDK 8+ (Build target)
- Maven

### 2. Launch Infrastructure
```bash
docker-compose up -d
```

### 3. Running the App
```bash
./mvnw spring-boot:run
```

## 📈 Upgrade Roadmap
This project is designed to be upgraded. Follow the `MENTORSHIP/LEARNING_TRACK.md` to move from Java 8 to Java 25.
