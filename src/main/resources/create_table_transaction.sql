CREATE TABLE IF NOT EXISTS transaction (
    transaction_id varchar(255) primary key default gen_random_uuid(),
    description varchar(255) not null,
    amount float not null,
    transaction_date timestamp default CURRENT_TIMESTAMP,
    transaction_type transaction_type not null,
    account_id varchar(255) references account(account_id)
    );

INSERT INTO transaction (transaction_id, description, amount, transaction_date, account_id)
VALUES
    ('a8b3a0bf-9121-11ee-b9d1-0242ac120002', 'Purchase 1', 50.00, CURRENT_TIMESTAMP, '1624c5be-911f-11ee-b9d1-0242ac120002'),
    ('a8b3a0bf-9121-11ee-b9d1-0242ac120003', 'Purchase 2', 75.50, CURRENT_TIMESTAMP, '67184ed2-911f-11ee-b9d1-0242ac120002'),
    ('a8b3a0bf-9121-11ee-b9d1-0242ac120004', 'Payment 3' , 70.60, CURRENT_TIMESTAMP, 'd701dc54-911f-11ee-b9d1-0242ac120002')
ON CONFLICT (transaction_id) DO NOTHING;

INSERT INTO transaction ( description, amount, transaction_type, account_id)
VALUES
    ('Ecolage M1', 100.0, 'transfer', '1624c5be-911f-11ee-b9d1-0242ac120002')
    ON CONFLICT (transaction_id) DO NOTHING;

INSERT INTO transaction (description, amount, transaction_type, account_id)
VALUES
    ('Nourriture', 50.0, 'income', '67184ed2-911f-11ee-b9d1-0242ac120002')
    ON CONFLICT (transaction_id) DO NOTHING;

INSERT INTO transaction (description, amount, transaction_type, account_id)
VALUES
    ('Ecolage M2', 200.0, 'transfer', '1624c5be-911f-11ee-b9d1-0242ac120002')
    ON CONFLICT (transaction_id) DO NOTHING;
