package com.neoledger.core.domain.ledger;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
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

    @Enumerated(EnumType.STRING)
    private EntryType type; // DEBIT or CREDIT

    private String transactionId;
    private LocalDateTime timestamp;

    public enum EntryType {
        DEBIT, CREDIT
    }
}
