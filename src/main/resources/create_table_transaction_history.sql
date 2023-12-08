CREATE TABLE IF NOT EXISTS transaction_history(
    transaction_history_id varchar(255) primary key default gen_random_uuid(),
    debit_transaction_id VARCHAR(255) NOT NULL,
    credit_transaction_id VARCHAR(255) NOT NULL,
    transfer_date TIMESTAMP NOT NULL,
    FOREIGN KEY (debit_transaction_id) REFERENCES balance(balance_id),
    FOREIGN KEY (credit_transaction_id) REFERENCES balance(balance_id)
);