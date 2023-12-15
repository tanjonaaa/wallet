INSERT INTO currency (currency_id, name, currency_code)
SELECT '4a292832-970d-48b6-b553-afc2a8e0c8ca', 'Euro', 'EUR'
    WHERE NOT EXISTS (SELECT 1 FROM currency WHERE currency_id = '4a292832-970d-48b6-b553-afc2a8e0c8ca');

INSERT INTO currency (currency_id, name, currency_code)
SELECT '8c0c76a2-950d-4bd1-8093-09768c038860', 'Ariary', 'MGA'
    WHERE NOT EXISTS (SELECT 1 FROM currency WHERE currency_id = '8c0c76a2-950d-4bd1-8093-09768c038860');
