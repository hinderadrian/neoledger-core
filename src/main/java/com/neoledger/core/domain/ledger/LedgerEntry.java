package com.neoledger.core.domain.ledger;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ledger_entries")
public class LedgerEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private com.neoledger.core.domain.account.Account account;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "entry_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EntryType type;

    private String transactionId;
    private LocalDateTime timestamp;

    public enum EntryType {
        DEBIT, CREDIT
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public com.neoledger.core.domain.account.Account getAccount() { return account; }
    public void setAccount(com.neoledger.core.domain.account.Account account) { this.account = account; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public EntryType getType() { return type; }
    public void setType(EntryType type) { this.type = type; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
