CREATE OR REPLACE FUNCTION sum_of_transaction_amount_for_each_category (
    account_id_param varchar(255),
    start_date_param TIMESTAMP,
    end_date_param TIMESTAMP
)
    RETURNS TABLE (category_name VARCHAR, sum_amount DECIMAL) AS
$$
BEGIN
    RETURN QUERY
        SELECT
            c.category_name,
            COALESCE(SUM(CASE WHEN t.transaction_date BETWEEN start_date_param AND end_date_param THEN t.amount ELSE 0.0 END)::DECIMAL, 0.0) AS sum_amount
        FROM categories c
                 LEFT JOIN "transaction" t ON c.category_id = t.category_id
            AND t.account_id = account_id_param
        GROUP BY c.category_name;
END;
$$ LANGUAGE plpgsql;

