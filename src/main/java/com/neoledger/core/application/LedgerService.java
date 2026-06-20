package com.neoledger.core.application;

import com.neoledger.core.domain.account.*;
import com.neoledger.core.domain.ledger.*;
import com.neoledger.core.domain.transaction.*;
import com.neoledger.core.infrastructure.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LedgerService {
    private final AccountRepository accountRepository;
    private final LedgerRepository ledgerRepository;
    private final TransactionRepository transactionRepository;

    public LedgerService(AccountRepository accountRepository, LedgerRepository ledgerRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.ledgerRepository = ledgerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction executeTransfer(String fromAccountNo, String toAccountNo, BigDecimal amount, String idempotencyKey) {
        // 1. Idempotency Check
        Transaction existingTx = transactionRepository.findByIdempotencyKey(idempotencyKey).orElse(null);
        if (existingTx != null) return existingTx;

        // 2. Fetch Accounts
        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNo)
            .orElseThrow(() -> new RuntimeException("Source account not found"));
        Account toAccount = accountRepository.findByAccountNumber(toAccountNo)
            .orElseThrow(() -> new RuntimeException("Destination account not found"));

        // 3. Validation
        if (!fromAccount.canWithdraw(amount)) {
            throw new RuntimeException("Insufficient funds or withdrawal limit reached");
        }

        // 4. Create Transaction Record
        Transaction tx = new Transaction();
        tx.setIdempotencyKey(idempotencyKey);
        tx.setTotalAmount(amount);
        tx.setStatus(Transaction.TransactionStatus.PROCESSING);
        tx.setCreatedAt(LocalDateTime.now());
        transactionRepository.save(tx);

        // 5. Double-Entry Bookkeeping
        // Debit from source
        createLedgerEntry(fromAccount, amount.negate(), LedgerEntry.EntryType.DEBIT, tx.getId().toString());
        // Credit to destination
        createLedgerEntry(toAccount, amount, LedgerEntry.EntryType.CREDIT, tx.getId().toString());

        // 6. Update Balances
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));
        
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        tx.setStatus(Transaction.TransactionStatus.SETTLED);
        tx.setSettledAt(LocalDateTime.now());
        return transactionRepository.save(tx);
    }

    private void createLedgerEntry(Account account, BigDecimal amount, LedgerEntry.EntryType type, String txId) {
        LedgerEntry entry = new LedgerEntry();
        entry.setAccount(account);
        entry.setAmount(amount);
        entry.setType(type);
        entry.setTransactionId(txId);
        entry.setTimestamp(LocalDateTime.now());
        ledgerRepository.save(entry);
    }
}
