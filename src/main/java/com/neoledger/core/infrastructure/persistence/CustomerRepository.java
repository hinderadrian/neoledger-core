package com.neoledger.core.infrastructure.persistence;

import com.neoledger.core.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    java.util.Optional<Customer> findByTaxId(String taxId);
}
