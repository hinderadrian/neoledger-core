package com.neoledger.core.infrastructure.persistence;

import com.neoledger.core.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    java.util.Optional<Transaction> findByIdempotencyKey(String key);
}
