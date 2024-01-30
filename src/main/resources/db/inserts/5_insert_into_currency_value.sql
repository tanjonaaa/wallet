INSERT INTO "currency_value" (currency_value_id, id_currency_source, id_currency_destination, change_rate)
SELECT
    'e5a218c2-4e6b-4fb5-83b0-cfc4c8b87894',
    '4a292832-970d-48b6-b553-afc2a8e0c8ca',
    '8c0c76a2-950d-4bd1-8093-09768c038860',
    4500.00
    WHERE NOT EXISTS (
    SELECT 1 FROM "currency_value" WHERE currency_value_id = 'e5a218c2-4e6b-4fb5-83b0-cfc4c8b87894'
);


INSERT INTO "currency_value" (currency_value_id, id_currency_source, id_currency_destination, change_rate)
SELECT
    '8b18f7bb-759f-4b16-b340-c6f0ef2e1b42',
    '4a292832-970d-48b6-b553-afc2a8e0c8ca',
    '8c0c76a2-950d-4bd1-8093-09768c038860',
    4600.00
    WHERE NOT EXISTS (
    SELECT 1 FROM "currency_value" WHERE currency_value_id = '8b18f7bb-759f-4b16-b340-c6f0ef2e1b42'
);

INSERT INTO "currency_value" (currency_value_id, id_currency_source, id_currency_destination, change_rate)
SELECT
    '7l1815bb-759f-4b16-b3gh-c6f0ef2e1b6g',
    '4a292832-970d-48b6-b553-afc2a8e0c8ca',
    '8c0c76a2-950d-4bd1-8093-09768c038860',
    4750.00
    WHERE NOT EXISTS (
    SELECT 1 FROM "currency_value" WHERE currency_value_id = '7l1815bb-759f-4b16-b3gh-c6f0ef2e1b6g'
);

