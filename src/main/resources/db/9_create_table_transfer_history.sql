CREATE TABLE IF NOT EXISTS "transfer_history"
(
  id varchar primary key default gen_random_uuid(),
  debit_transaction_id varchar not null references transaction(transaction_id),
  credit_transaction_id varchar not null references transaction(transaction_id),
  transfer_date timestamp not null default current_timestamp
);