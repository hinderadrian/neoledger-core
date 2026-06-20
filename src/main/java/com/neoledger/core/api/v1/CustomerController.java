package com.neoledger.core.api.v1;

import com.neoledger.core.domain.customer.Customer;
import com.neoledger.core.infrastructure.persistence.CustomerRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Tag(name = "Customers", description = "Customer Onboarding and KYC Management")
public class CustomerController {
    private final CustomerRepository customerRepository;

    @PostMapping
    @Operation(summary = "Onboard a new customer", description = "Creates a customer profile and starts the KYC process.")
    public ResponseEntity<Customer> onboard(@RequestBody Customer customer) {
        customer.setCreatedAt(java.time.LocalDateTime.now());
        customer.setKycStatus(Customer.KycStatus.SUBMITTED);
        return ResponseEntity.ok(customerRepository.save(customer));
    }
}
