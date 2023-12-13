CREATE TABLE IF NOT EXISTS currency (
    currency_id varchar(250) primary key default gen_random_uuid(),
    name varchar(50) not null,
    symbol varchar(6) not null,
    currency_code currency_code not null
);
