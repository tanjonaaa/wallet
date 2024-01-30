
INSERT INTO account (account_id, name, currency_id, account_type)
SELECT
    '7f588973-24cc-4c4a-b97d-3f6a71f5c307',
    'Compte Epargne',
    '4a292832-970d-48b6-b553-afc2a8e0c8ca',
    'CASH'
    WHERE NOT EXISTS (
    SELECT 1 FROM account WHERE account_id = '7f588973-24cc-4c4a-b97d-3f6a71f5c307'
);


INSERT INTO account (account_id, name, currency_id, account_type)
SELECT
    'bf37d19a-6e1b-4213-a073-07f5c268ba3d',
    'Compte Courant',
    '8c0c76a2-950d-4bd1-8093-09768c038860',
    'BANK'
    WHERE NOT EXISTS (
    SELECT 1 FROM account WHERE account_id = 'bf37d19a-6e1b-4213-a073-07f5c268ba3d'
);

INSERT INTO account (account_id, name, currency_id, account_type)
SELECT
    'bd8f407e-74de-41bc-8111-74c8dd7b93c5',
    'Compte Courant',
    '8c0c76a2-950d-4bd1-8093-09768c038860',
    'MOBILE_MONEY'
    WHERE NOT EXISTS (
    SELECT 1 FROM account WHERE account_id = 'bd8f407e-74de-41bc-8111-74c8dd7b93c5'
);



