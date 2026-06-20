-- Migration for Customers and Accounts
CREATE TABLE customers (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    tax_id VARCHAR(50) UNIQUE NOT NULL,
    kyc_status VARCHAR(20),
    risk_level VARCHAR(20),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE accounts (
    id BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(50) UNIQUE NOT NULL,
    customer_id BIGINT REFERENCES customers(id),
    balance DECIMAL(19, 4) NOT NULL DEFAULT 0,
    status VARCHAR(20),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE accounts_savings (
    id BIGINT PRIMARY KEY REFERENCES accounts(id),
    interest_rate DECIMAL(5, 4),
    monthly_withdrawal_limit INTEGER,
    current_month_withdrawals INTEGER DEFAULT 0
);

CREATE TABLE accounts_checking (
    id BIGINT PRIMARY KEY REFERENCES accounts(id),
    overdraft_limit DECIMAL(19, 4)
);

CREATE TABLE accounts_investment (
    id BIGINT PRIMARY KEY REFERENCES accounts(id),
    asset_class VARCHAR(50)
);

CREATE TABLE accounts_escrow (
    id BIGINT PRIMARY KEY REFERENCES accounts(id),
    release_condition TEXT,
    is_released BOOLEAN DEFAULT FALSE
);

CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    idempotency_key VARCHAR(100) UNIQUE NOT NULL,
    total_amount DECIMAL(19, 4) NOT NULL,
    status VARCHAR(20),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    settled_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE ledger_entries (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT REFERENCES accounts(id),
    amount DECIMAL(19, 4) NOT NULL,
    entry_type VARCHAR(10) NOT NULL, -- DEBIT or CREDIT
    transaction_id VARCHAR(100),
    timestamp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
