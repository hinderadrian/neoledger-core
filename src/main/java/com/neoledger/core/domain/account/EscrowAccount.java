package com.neoledger.core.domain.account;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "accounts_escrow")
public class EscrowAccount extends Account {
    private String releaseCondition;
    private boolean isReleased = false;

    @Override
    public boolean canWithdraw(BigDecimal amount) {
        return isReleased && this.getBalance().compareTo(amount) >= 0;
    }
}
