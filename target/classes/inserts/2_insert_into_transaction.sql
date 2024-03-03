
INSERT INTO transaction (transaction_id, description, amount, account_id, transaction_type)
SELECT
    '5f8b7362-24c2-4b3f-8e04-2577cc9f003a',
    'Achat de nourriture',
    50.0,
    '7f588973-24cc-4c4a-b97d-3f6a71f5c307',
    'EXPENSE'
    WHERE NOT EXISTS (
    SELECT 1 FROM transaction WHERE transaction_id = '5f8b7362-24c2-4b3f-8e04-2577cc9f003a'
);


INSERT INTO transaction (transaction_id, description, amount, account_id, transaction_type)
SELECT
    'a2a3a5c6-9019-4b67-9f2b-3ae86c3d44d7',
    'Salaire',
    20400.00,
    'bf37d19a-6e1b-4213-a073-07f5c268ba3d',
    'INCOME'
    WHERE NOT EXISTS (
    SELECT 1 FROM transaction WHERE transaction_id = 'a2a3a5c6-9019-4b67-9f2b-3ae86c3d44d7'
);


INSERT INTO transaction (transaction_id, description, amount, account_id, transaction_type)
SELECT
    'b41a72b7-d5c9-4be7-b063-9bb9ce6d0bda',
    'Achat en ligne',
    150.0,
    '7f588973-24cc-4c4a-b97d-3f6a71f5c307',
    'EXPENSE'
    WHERE NOT EXISTS (
    SELECT 1 FROM transaction WHERE transaction_id = 'b41a72b7-d5c9-4be7-b063-9bb9ce6d0bda'
);