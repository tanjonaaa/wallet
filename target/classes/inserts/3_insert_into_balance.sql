INSERT INTO balance (balance_id,amount, account_id)
SELECT
    'b5f80b4d-23b9-4a19-9a8b-12e23847b049',
    5000.00,
    '7f588973-24cc-4c4a-b97d-3f6a71f5c307'
    WHERE NOT EXISTS (
    SELECT 1 FROM balance WHERE balance_id = 'b5f80b4d-23b9-4a19-9a8b-12e23847b049'
);


INSERT INTO balance (balance_id,amount, account_id)
SELECT
    'a1f84302-512b-4db3-8b4c-3d02c8f1c6ec',
    100000.00,
    'bf37d19a-6e1b-4213-a073-07f5c268ba3d'
    WHERE NOT EXISTS (
    SELECT 1 FROM balance WHERE balance_id = 'a1f84302-512b-4db3-8b4c-3d02c8f1c6ec'
);


INSERT INTO balance (balance_id, amount, account_id)
SELECT
    'c9dcd0ee-8b21-4f9d-8c2b-e1417dbf178d',
    750000.00,
    '7f588973-24cc-4c4a-b97d-3f6a71f5c307'
    WHERE NOT EXISTS (
    SELECT 1 FROM balance WHERE balance_id = 'c9dcd0ee-8b21-4f9d-8c2b-e1417dbf178d'
);
