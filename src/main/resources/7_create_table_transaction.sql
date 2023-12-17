CREATE TABLE IF NOT EXISTS transaction (
    transaction_id varchar(255) primary key default gen_random_uuid(),
    description varchar(255) not null,
    amount double precision not null,
    transaction_date timestamp default CURRENT_TIMESTAMP,
    account_id varchar(255) references account(account_id),
    transaction_type transaction_type NOT NULL DEFAULT 'INCOME'
);