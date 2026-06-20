package com.neoledger.core.domain.customer;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String taxId;

    @Enumerated(EnumType.STRING)
    private KycStatus kycStatus;

    private String riskLevel;

    private LocalDateTime createdAt;

    public enum KycStatus {
        SUBMITTED, VERIFIED, ACTIVE, REJECTED
    }
}
