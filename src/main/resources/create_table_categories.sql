CREATE TABLE IF NOT EXISTS categories (
    category_id varchar(250) PRIMARY KEY default gen_random_uuid(),
    category_name VARCHAR(255) NOT NULL,
    transaction_type category_type NOT NULL
    );

INSERT INTO categories(category_name, transaction_type)
VALUES ('Bar, cafe', 'EXPENSE'),
       ('Groceries', 'EXPENSE'),
       ('Restaurant, fast-food', 'EXPENSE'),
       ('Clothes & shoes', 'EXPENSE'),
       ('Drug-store, chemist', 'EXPENSE'),
       ('Electronics, accessories', 'EXPENSE'),
       ('Free time', 'EXPENSE'),
       ('Gifts, joy', 'EXPENSE'),
       ('Health and beauty',
        'EXPENSE'),
       ('Home, garden', 'EXPENSE'),
       ('Jewels, accessories', 'EXPENSE'),
       ('Kids', 'EXPENSE'),
       ('Pets, animals', 'EXPENSE'),
       ('Stationery, tools', 'EXPENSE'),
       ('Energy, utilities', 'EXPENSE'),
       ('Maintenance, repairs', 'EXPENSE'),
       ('Mortgage', 'EXPENSE'),
       ('Property insurance', 'EXPENSE'),
       ('Rent', 'EXPENSE'),
       ('Services', 'EXPENSE'),
       ('Business trips', 'EXPENSE'),
       ('Long distance', 'EXPENSE'),
       ('Public transport', 'EXPENSE'),
       ('Taxi', 'EXPENSE'),
       ('Vehicle', 'EXPENSE'),
       ('Fuel', 'EXPENSE'),
       ('leasing', 'EXPENSE'),
       ('Parking', 'EXPENSE'),
       ('Rentals', 'EXPENSE'),
       ('Vehicle insurance', 'EXPENSE'),
       ('Vehicle maintenance', 'EXPENSE'),
       ('Active sport, fitness', 'EXPENSE'),
       ('Alcohol, tobacco', 'EXPENSE'),
       ('Books, audio, subscriptions', 'EXPENSE'),
       ('Charity, gifts', 'EXPENSE'),
       ('Culture, sport events', 'EXPENSE'),
       ('Education, development', 'EXPENSE'),
       ('Health care, doctor', 'EXPENSE'),
       ('Hobbies', 'EXPENSE'),
       ('Holiday, trips, hotels', 'EXPENSE'),
       ('Life events', 'EXPENSE'),
       ('Lottery, gambling', 'EXPENSE'),
       ('TV, Streaming', 'EXPENSE'),
       ('Wellness, beauty', 'EXPENSE'),
       ('Internet', 'EXPENSE'),
       ('Phone, mobile phone','EXPENSE'),
       ('Postal services', 'EXPENSE'),
       ('Software, apps, games', 'EXPENSE'),
       ('Advisory', 'EXPENSE'),
       ('Charges, Fees', 'EXPENSE'),
       ('Child Support', 'EXPENSE'),
       ('Fines', 'EXPENSE'),
       ('Checks, coupons', 'INCOME'),
       ('Child Support', 'INCOME'),
       ('Dues & grants', 'INCOME'),
       ('Gifts', 'INCOME'),
       ('Interests, dividends', 'INCOME'),
       ('Lending, renting', 'INCOME'),
       ('Lottery, gambling', 'INCOME'),
       ('Refunds (tax, purchase)', 'INCOME'),
       ('Rental INCOME', 'INCOME'),
       ('Sale', 'INCOME'),
       ('Wage, invoices', 'INCOME') ,
       ('Others' , 'EXPENSE') ,
       ('Others' , 'INCOME') ,
       ('Unknown INCOME' , 'INCOME') ,
       ('Unknown EXPENSE' , 'EXPENSE');

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Bar, cafe' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Bar, cafe' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Groceries' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Groceries' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Restaurant, fast-food' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Restaurant, fast-food' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Clothes & shoes' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Clothes & shoes' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Drug-store, chemist' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Drug-store, chemist' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Electronics, accessories' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Electronics, accessories' AND transaction_type = 'EXPENSE'
);



