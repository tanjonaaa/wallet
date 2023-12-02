CREATE TABLE IF NOT EXISTS account (
    account_id varchar(250) primary key default gen_random_uuid(),
    name varchar(250) not null,
    last_name varchar(250) not null,
    email varchar(250) not null,
    password varchar(250) not null,
    balance float not null default 0,
    currency_id varchar(250) REFERENCES currency(currency_id)
    );

INSERT INTO account (account_id, name, last_name, email, password, balance, currency_id)
SELECT
    '1624c5be-911f-11ee-b9d1-0242ac120002' AS account_id,
    'Tendry' AS name,
    'Rk' AS last_name,
    'Tendry_Rk@gmail.com' AS email,
    '12345687' AS password,
     100.50 AS balance,
    'cf4575c4-911b-11ee-b9d1-0242ac120002' AS currency_id
    WHERE NOT EXISTS (SELECT 1 FROM account WHERE account_id = '1624c5be-911f-11ee-b9d1-0242ac120002')
ON CONFLICT (account_id) DO NOTHING;

INSERT INTO account (account_id, name, last_name, email, password, balance, currency_id)
SELECT
    '67184ed2-911f-11ee-b9d1-0242ac120002' AS account_id,
    'Tanjona' AS name,
    'Adn' AS last_name,
    'Tanjona_Adn@gmail.com' AS email,
    '87654312' AS password,
    169.40 AS balance,
    'a153753a-8c6e-6bd8-97b1-cge087ji39ga' AS currency_id
    WHERE NOT EXISTS (SELECT 1 FROM account WHERE account_id = '67184ed2-911f-11ee-b9d1-0242ac120002')
ON CONFLICT (account_id) DO NOTHING;

INSERT INTO account (account_id, name, last_name, email, password, balance, currency_id)
SELECT
    'd701dc54-911f-11ee-b9d1-0242ac120002' AS account_id,
    'John' AS name,
    'Doe' AS last_name,
    'JohnDoe@gmail.com' AS email,
    '87654568' AS password,
    100000.00 AS balance,
    'b123533a-2b0d-4ce5-81a2-caf078ba35fe' AS currency_id
    WHERE NOT EXISTS (SELECT 1 FROM account WHERE account_id = 'd701dc54-911f-11ee-b9d1-0242ac120002')
ON CONFLICT (account_id) DO NOTHING;
