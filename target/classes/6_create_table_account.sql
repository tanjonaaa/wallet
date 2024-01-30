-- name : compte epargne , compte courant
CREATE TABLE IF NOT EXISTS account (
    account_id varchar(250) primary key default gen_random_uuid(),
    name varchar(250) not null,
    currency_id varchar(250) REFERENCES currency(currency_id),
    account_type account_type NOT NULL DEFAULT 'CASH'
);
