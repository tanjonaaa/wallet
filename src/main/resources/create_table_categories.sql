CREATE TABLE IF NOT EXISTS categories (
    category_id varchar(250) PRIMARY KEY default gen_random_uuid(),
    category_name VARCHAR(255) NOT NULL,
    transaction_type category_type NOT NULL
    );

