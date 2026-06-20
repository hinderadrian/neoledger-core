package com.neoledger.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.neoledger.core.domain")
@EnableJpaRepositories("com.neoledger.core.infrastructure.persistence")
public class NeoLedgerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NeoLedgerApplication.class, args);
    }
}
