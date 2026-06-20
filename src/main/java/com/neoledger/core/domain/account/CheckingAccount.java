package com.neoledger.core.domain.account;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "accounts_checking")
public class CheckingAccount extends Account {
    private BigDecimal overdraftLimit;

    @Override
    public boolean canWithdraw(BigDecimal amount) {
        BigDecimal availableFunds = this.getBalance().add(overdraftLimit);
        return availableFunds.compareTo(amount) >= 0;
    }
}
