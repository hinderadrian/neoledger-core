package com.neoledger.core.domain.account;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "accounts_savings")
public class SavingsAccount extends Account {
    private BigDecimal interestRate;
    private Integer monthlyWithdrawalLimit;
    private Integer currentMonthWithdrawals = 0;

    @Override
    public boolean canWithdraw(BigDecimal amount) {
        return this.getBalance().compareTo(amount) >= 0 
            && this.currentMonthWithdrawals < this.monthlyWithdrawalLimit;
    }
}
