CREATE TABLE IF NOT EXISTS categories (
    category_id varchar(250) PRIMARY KEY default gen_random_uuid(),
    category_name VARCHAR(255) NOT NULL,
    transaction_type category_type NOT NULL
    );

INSERT INTO categories(category_name, transaction_type)
VALUES ('Bar, cafe', 'expense'),
       ('Groceries', 'expense'),
       ('Restaurant, fast-food', 'expense'),
       ('Clothes & shoes', 'expense'),
       ('Drug-store, chemist', 'expense'),
       ('Electronics, accessories', 'expense'),
       ('Free time', 'expense'),
       ('Gifts, joy', 'expense'),
       ('Health and beauty',
        'expense'),
       ('Home, garden', 'expense'),
       ('Jewels, accessories', 'expense'),
       ('Kids', 'expense'),
       ('Pets, animals', 'expense'),
       ('Stationery, tools', 'expense'),
       ('Energy, utilities', 'expense'),
       ('Maintenance, repairs', 'expense'),
       ('Mortgage', 'expense'),
       ('Property insurance', 'expense'),
       ('Rent', 'expense'),
       ('Services', 'expense'),
       ('Business trips', 'expense'),
       ('Long distance', 'expense'),
       ('Public transport', 'expense'),
       ('Taxi', 'expense'),
       ('Vehicle', 'expense'),
       ('Fuel', 'expense'),
       ('leasing', 'expense'),
       ('Parking', 'expense'),
       ('Rentals', 'expense'),
       ('Vehicle insurance', 'expense'),
       ('Vehicle maintenance', 'expense'),
       ('Active sport, fitness', 'expense'),
       ('Alcohol, tobacco', 'expense'),
       ('Books, audio, subscriptions', 'expense'),
       ('Charity, gifts', 'expense'),
       ('Culture, sport events', 'expense'),
       ('Education, development', 'expense'),
       ('Health care, doctor', 'expense'),
       ('Hobbies', 'expense'),
       ('Holiday, trips, hotels', 'expense'),
       ('Life events', 'expense'),
       ('Lottery, gambling', 'expense'),
       ('TV, Streaming', 'expense'),
       ('Wellness, beauty', 'expense'),
       ('Internet', 'expense'),
       ('Phone, mobile phone','expense'),
       ('Postal services', 'expense'),
       ('Software, apps, games', 'expense'),
       ('Advisory', 'expense'),
       ('Charges, Fees', 'expense'),
       ('Child Support', 'expense'),
       ('Fines', 'expense'),
       ('Checks, coupons', 'income'),
       ('Child Support', 'income'),
       ('Dues & grants', 'income'),
       ('Gifts', 'income'),
       ('Interests, dividends', 'income'),
       ('Lending, renting', 'income'),
       ('Lottery, gambling', 'income'),
       ('Refunds (tax, purchase)', 'income'),
       ('Rental income', 'income'),
       ('Sale', 'income'),
       ('Wage, invoices', 'income') ,
       ('Others' , 'expense') ,
       ('Others' , 'income') ,
       ('Unknown income' , 'income') ,
       ('Unknown expense' , 'expense');

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Bar, cafe' AS category_name,
    'expense' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Bar, cafe' AND transaction_type = 'expense'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Groceries' AS category_name,
    'expense' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Groceries' AND transaction_type = 'expense'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Restaurant, fast-food' AS category_name,
    'expense' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Restaurant, fast-food' AND transaction_type = 'expense'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Clothes & shoes' AS category_name,
    'expense' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Clothes & shoes' AND transaction_type = 'expense'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Drug-store, chemist' AS category_name,
    'expense' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Drug-store, chemist' AND transaction_type = 'expense'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Electronics, accessories' AS category_name,
    'expense' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Electronics, accessories' AND transaction_type = 'expense'
);



