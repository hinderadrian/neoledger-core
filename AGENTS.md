# NeoLedger Core Agent Guide

## Developer Commands
- **Infrastructure**: `docker-compose up -d` (Starts Postgres 16, Redis, Prometheus, Grafana)
- **Run Application**: `./mvnw spring-boot:run`
- **Build & Test**: `mvn clean install` or `mvn test`
- **DB Migrations**: Managed by Flyway; scripts located in `src/main/resources/db/migration`

## Architecture & Patterns
- **Pattern**: Layered Monolith $\rightarrow$ API $\rightarrow$ Application $\rightarrow$ Domain $\rightarrow$ Infrastructure.
- **Domain**: Implements Double-Entry Accounting. Every financial movement must balance between accounts.
- **Tech Stack**: Java 17 (Note: README may mention Java 8 as a starting point for upgrade practice, but `pom.xml` targets 17), Spring Boot 3.2.3, PostgreSQL, Redis.
- **Persistence**: Spring Data JPA with PostgreSQL.

## Key Constraints
- **Strict Layering**: Avoid bypassing the Application layer when calling Domain logic from the API layer.
- **Double-Entry**: Ensure all transaction logic maintains the fundamental accounting equation.
- **Future State**: The codebase is designed for eventual migration to a Modular Monolith (Spring Modulith).
