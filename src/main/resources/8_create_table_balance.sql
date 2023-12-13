CREATE TABLE IF NOT EXISTS balance(
    balance_id varchar(255) PRIMARY KEY DEFAULT gen_random_uuid(),
    balance_timestamp timestamp NOT NULL DEFAULT current_timestamp,
    amount double precision NOT NULL,
    account_id varchar(255) REFERENCES account(account_id)
);