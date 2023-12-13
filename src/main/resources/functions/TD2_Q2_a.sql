/*If we want the SUM OF incomes AND expenses*/
CREATE OR REPLACE FUNCTION get_income_and_expense_sum (
    account_id INT,
    start_date TIMESTAMP,
    end_date TIMESTAMP
)
    RETURNS DECIMAL AS
$$
DECLARE
    sum DECIMAL;
BEGIN
    SELECT COALESCE(SUM(CASE WHEN transaction_type = 'income' THEN amount ELSE 0 END), 0) -
           COALESCE(SUM(CASE WHEN transaction_type = 'expense' THEN amount ELSE 0 END), 0)
    INTO sum
    FROM transaction
    WHERE account_id = account_id
      AND transaction_date BETWEEN start_date AND end_date;

    RETURN sum;
END;
$$

/*If we want the sum of incomes AND the sum of expenses*/
CREATE OR REPLACE FUNCTION somme_income_expense (
    account_id INT,
    start_date TIMESTAMP,
    end_date TIMESTAMP
)
    RETURNS TABLE (income_sum DECIMAL, expense_sum DECIMAL) AS
$$
BEGIN
    RETURN QUERY
        SELECT
            COALESCE(SUM(CASE WHEN transaction_type = 'income' THEN amount ELSE 0 END), 0) AS income_sum,
            COALESCE(SUM(CASE WHEN transaction_type = 'expense' THEN amount ELSE 0 END), 0) AS expense_sum
        FROM transaction
        WHERE account_id = account_id
          AND transaction_date BETWEEN start_date AND end_date;
END;
$$
