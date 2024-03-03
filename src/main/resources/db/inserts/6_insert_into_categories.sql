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

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Free time' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Free time' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Gifts, joy' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Gifts, joy' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Health and beauty' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Health and beauty' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Home, garden' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Home, garden' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Jewels, accessories' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Jewels, accessories' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Kids' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Kids' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Pets, animals' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Pets, animals' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Stationery, tools' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Stationery, tools' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Energy, utilities' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Energy, utilities' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Maintenance, repairs' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Maintenance, repairs' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Mortgage' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Mortgage' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Property insurance' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Property insurance' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Rent' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Rent' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Services' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Services' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Business trips' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Business trips' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Long distance' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Long distance' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Public transport' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Public transport' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Taxi' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Taxi' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Fuel' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Fuel' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Leasing' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Leasing' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Parking' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Parking' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Rentals' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Rentals' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Vehicle insurance' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Vehicle insurance' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Vehicle maintenance' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Vehicle maintenance' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Active sport, fitness' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Active sport, fitness' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Alcohol, tobacco' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Alcohol, tobacco' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Books, audio, subscriptions' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Books, audio, subscriptions' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Charity, gifts' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Charity, gifts' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Culture, sport events' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Culture, sport events' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Health care, doctor' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Health care, doctor' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Hobbies' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Hobbies' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Holiday, trips, hotels' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Holiday, trips, hotels' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Life events' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Life events' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Lottery, gambling' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Lottery, gambling' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'TV, Streaming' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'TV, Streaming' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Wellness, beauty' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Wellness, beauty' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Internet' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Internet' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Phone, mobile phone' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Phone, mobile phone' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Postal services' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Postal services' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Software, apps, games' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Software, apps, games' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Advisory' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Advisory' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Charges, Fees' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Charges, Fees' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Child Support' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Child Support' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Fines' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Fines' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Loan, interests' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Loan, interests' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Taxes' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Taxes' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Collections' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Collections' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Financial investments' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Financial investments' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Realty' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Realty' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Savings' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Savings' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Checks, coupons' AS category_name,
    'INCOME' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Checks, coupons' AND transaction_type = 'INCOME'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Child Support' AS category_name,
    'INCOME' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Child Support' AND transaction_type = 'INCOME'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Dues & grants' AS category_name,
    'INCOME' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Dues & grants' AND transaction_type = 'INCOME'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Gifts' AS category_name,
    'INCOME' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Gifts' AND transaction_type = 'INCOME'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Interests, dividends' AS category_name,
    'INCOME' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Interests, dividends' AND transaction_type = 'INCOME'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Lending, renting' AS category_name,
    'INCOME' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Lending, renting' AND transaction_type = 'INCOME'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Lottery, gambling' AS category_name,
    'INCOME' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Lottery, gambling' AND transaction_type = 'INCOME'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Refunds (tax, purchase)' AS category_name,
    'INCOME' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Refunds (tax, purchase)' AND transaction_type = 'INCOME'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Rental INCOME' AS category_name,
    'INCOME' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Rental INCOME' AND transaction_type = 'INCOME'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Sale' AS category_name,
    'INCOME' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Sale' AND transaction_type = 'INCOME'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Wage, invoices' AS category_name,
    'INCOME' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Wage, invoices' AND transaction_type = 'INCOME'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Unknown INCOME' AS category_name,
    'INCOME' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Unknown INCOME' AND transaction_type = 'INCOME'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Others' AS category_name,
    'INCOME' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Others' AND transaction_type = 'INCOME'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Unknown EXPENSE' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Unknown EXPENSE' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Others' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Others' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Transfer' AS category_name,
    'EXPENSE' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Transfer' AND transaction_type = 'EXPENSE'
);

INSERT INTO categories(category_id, category_name, transaction_type)
SELECT
    gen_random_uuid(),
    'Transfer' AS category_name,
    'INCOME' AS transaction_type
    WHERE NOT EXISTS (
    SELECT 1 FROM categories
    WHERE category_name = 'Transfer' AND transaction_type = 'INCOME'
);










