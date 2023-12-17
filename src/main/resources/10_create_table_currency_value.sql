CREATE TABLE "currency_value" (
  currency_value_id varchar primary key default gen_random_uuid(),
  id_currency_source varchar not null references currency(currency_id),
  id_currency_destination varchar not null references currency(currency_id),
  change_rate double precision not null,
  currency_value_date timestamp default current_timestamp
);