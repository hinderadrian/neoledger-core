# 🏗️ Phase 1: The Blueprint Specification

The goal of this phase is to build a high-quality, "legacy-modern" baseline. This project simulates a production-ready FinTech core that will later be upgraded.

## 🎯 Domain Specifications

### 1. Customer Onboarding
- **KYC State Machine**: `SUBMITTED` $\rightarrow$ `VERIFIED` $\rightarrow$ `ACTIVE`.
- **Profile**: Personal details, Tax ID, Risk Level.

### 2. Account Engine
Four account types with distinct business rules:
- **Savings**: Monthly withdrawal limits, interest accrual.
- **Checking**: High liquidity, Overdraft limit.
- **Investment**: Multi-asset support, tax tracking.
- **Escrow**: Third-party hold, release-based transfers.

### 3. Core Ledger (The "Golden Record")
- **Double-Entry Principle**: Every transaction must have at least one debit and one credit.
- **Immutable Journal**: All ledger entries are append-only.
- **Consistency**: `Sum(Debits) == Sum(Credits)`.

### 4. Transaction Management
- **Idempotency**: Every request must have a unique `Idempotency-Key`.
- **Orchestration**: `PENDING` $\rightarrow$ `PROCESSING` $\rightarrow$ `SETTLED`.
- **Fee Engine**: Tiered fee calculation based on account type.

## 🛠️ Infrastructure Map

- **API Versioning**: `/api/v1/...`
- **Persistence**: PostgreSQL 16 (ACID compliance).
- **Caching**: Redis (for account balances and idempotency tokens).
- **Migrations**: Flyway (Versioned SQL).
- **Observability**: Prometheus & Grafana (JVM and Business metrics).

## ✅ Success Criteria for Phase 1
- [ ] Project builds successfully with Java 8 target.
- [ ] Docker Compose raises Postgres, Redis, and Grafana.
- [ ] All 4 Account types are implemented with their specific logic.
- [ ] Double-entry ledger prevents unbalanced transactions.
- [ ] Swagger documentation is accessible.
- [ ] API v1 is functional for onboarding and transfers.
