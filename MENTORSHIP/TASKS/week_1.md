# Week 1: The Integrity of the Golden Record

## 🎯 Objective
Refine the Transactional Core to move from a prototype to a production-ready financial engine.

## ✅ Checklist
- [ ] **Domain-Driven Validation**: Move transfer validation logic from `LedgerService` to the domain layer (Account entity or a Domain Service).
- [ ] **Exception Hierarchy**: Implement a custom exception package in `com.neoledger.core.domain.exception` (e.g., `InsufficientFundsException`).
- [ ] **Atomic Integrity**: Verify and ensure that the transaction, ledger entries, and balance updates are wrapped in a single atomic transaction.
- [ ] **Financial Precision**: Audit all `BigDecimal` operations to ensure no precision loss or incorrect rounding.

## 📦 Deliverables
- Refactored `LedgerService.java`
- New domain exception classes
- Updated `Account.java` or new Domain Service
