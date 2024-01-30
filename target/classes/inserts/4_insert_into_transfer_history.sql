INSERT INTO "transfer_history" (id, debit_transaction_id, credit_transaction_id, transfer_date)
SELECT
    'aad5dbf3-2085-4b0a-874a-9b72a0b9bc87',
    '5f8b7362-24c2-4b3f-8e04-2577cc9f003a',
    'a2a3a5c6-9019-4b67-9f2b-3ae86c3d44d7',
    '2023-01-02 14:30:00'
    WHERE NOT EXISTS (
    SELECT 1 FROM "transfer_history" WHERE id = 'aad5dbf3-2085-4b0a-874a-9b72a0b9bc87'
);


INSERT INTO "transfer_history" (id, debit_transaction_id, credit_transaction_id, transfer_date)
SELECT
    'e8d9e78a-731f-4ac8-92b3-2f1b7c81f9b2',
    'a2a3a5c6-9019-4b67-9f2b-3ae86c3d44d7',
    'b41a72b7-d5c9-4be7-b063-9bb9ce6d0bda',
    '2023-01-03 10:45:00'
    WHERE NOT EXISTS (
    SELECT 1 FROM "transfer_history" WHERE id = 'e8d9e78a-731f-4ac8-92b3-2f1b7c81f9b2'
);


INSERT INTO "transfer_history" (id, debit_transaction_id, credit_transaction_id, transfer_date)
SELECT
    'fb9865d2-15f7-4d3e-b270-54754ec31bb1',
    '5f8b7362-24c2-4b3f-8e04-2577cc9f003a',
    'b41a72b7-d5c9-4be7-b063-9bb9ce6d0bda',
    '2023-01-04 16:15:00'
    WHERE NOT EXISTS (
    SELECT 1 FROM "transfer_history" WHERE id = 'fb9865d2-15f7-4d3e-b270-54754ec31bb1'
);
