package com.neoledger.core.domain.account;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "accounts_investment")
public class InvestmentAccount extends Account {
    private String assetClass; // e.g., STOCKS, BONDS

    @Override
    public boolean canWithdraw(BigDecimal amount) {
        return this.getBalance().compareTo(amount) >= 0;
    }
}
