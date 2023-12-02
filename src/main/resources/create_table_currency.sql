CREATE TABLE IF NOT EXISTS currency (
    currency_id varchar(250) primary key default gen_random_uuid(),
    name varchar(50) not null,
    symbol varchar(6) not null
    );

INSERT INTO currency (currency_id, name, symbol)
SELECT 'b123533a-2b0d-4ce5-81a2-caf078ba35fe' AS currency_id, 'Euro' AS name, '€' AS symbol
    WHERE NOT EXISTS (SELECT 1 FROM currency WHERE currency_id = 'b123533a-2b0d-4ce5-81a2-caf078ba35fe')
ON CONFLICT (currency_id) DO NOTHING;

INSERT INTO currency (currency_id, name, symbol)
SELECT 'a153753a-8c6e-6bd8-97b1-cge087ji39ga' AS currency_id, 'US Dollar' AS name, '$' AS symbol
    WHERE NOT EXISTS (SELECT 1 FROM currency WHERE currency_id = 'a153753a-8c6e-6bd8-97b1-cge087ji39ga')
ON CONFLICT (currency_id) DO NOTHING;

INSERT INTO currency (currency_id , name, symbol)
SELECT 'cf4575c4-911b-11ee-b9d1-0242ac120002' AS currency_id, 'Madagascar Ariary' AS name, 'Ar' AS symbol
    WHERE NOT EXISTS (SELECT 1 FROM currency WHERE currency_id = 'cf4575c4-911b-11ee-b9d1-0242ac120002')
ON CONFLICT (currency_id) DO NOTHING;