CREATE TABLE "currency_value" (
  currency_value_id varchar primary key default gen_random_uuid(),
  id_currency_source varchar not null references currency(currency_id),
  id_currency_destination varchar not null references currency(currency_id),
  change_rate double precision not null,
  currency_value_date timestamp not null default current_timestamp
);

-- Insérer des enregistrements dans la table currency_value
INSERT INTO currency_value (id_currency_source, id_currency_destination, change_rate, currency_value_date)
VALUES
  ('b123533a-2b0d-4ce5-81a2-caf078ba35fe', 'cf4575c4-911b-11ee-b9d1-0242ac120002', 4500.00, '2023-12-06 00:00:00'),
  ('b123533a-2b0d-4ce5-81a2-caf078ba35fe', 'cf4575c4-911b-11ee-b9d1-0242ac120002', 4690.00, '2023-12-06 12:00:00'),
  ('b123533a-2b0d-4ce5-81a2-caf078ba35fe', 'cf4575c4-911b-11ee-b9d1-0242ac120002', 4700.00, '2023-12-06 18:00:00');

SELECT AVG(change_rate) AS weighted_average
FROM currency_value
WHERE currency_value_date >= '2023-12-06 00:00:00'
  AND currency_value_date < '2023-12-07 00:00:00';
