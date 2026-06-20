package com.neoledger.core.api.v1;

import com.neoledger.core.application.LedgerService;
import com.neoledger.core.domain.account.Account;
import com.neoledger.core.domain.transaction.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/ledger")
@RequiredArgsConstructor
@Tag(name = "Ledger", description = "Core Double-Entry Accounting Operations")
public class LedgerController {
    private final LedgerService ledgerService;

    @PostMapping("/transfer")
    @Operation(summary = "Execute a balance transfer", description = "Performs a double-entry transfer between two accounts with idempotency check.")
    public ResponseEntity<Transaction> transfer(
            @RequestParam String fromAccount,
            @RequestParam String toAccount,
            @RequestParam BigDecimal amount,
            @RequestHeader("X-Idempotency-Key") String idempotencyKey) {
        
        Transaction result = ledgerService.executeTransfer(fromAccount, toAccount, amount, idempotencyKey);
        return ResponseEntity.ok(result);
    }
}
