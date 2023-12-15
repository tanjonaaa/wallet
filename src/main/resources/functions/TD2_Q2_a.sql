CREATE OR REPLACE FUNCTION sum_of_money_in_out (
    account_id_param varchar(255),
    start_date_param TIMESTAMP,
    end_date_param TIMESTAMP
)
    RETURNS TABLE (income DECIMAL, expense DECIMAL) AS
$$
BEGIN
    RETURN QUERY
        SELECT
            COALESCE(SUM(CASE WHEN t.transaction_type = 'income' THEN t.amount ELSE 0.0 END)::DECIMAL, 0.0) AS income,
            COALESCE(SUM(CASE WHEN t.transaction_type = 'expense' THEN t.amount ELSE 0.0 END)::DECIMAL, 0.0) AS expense
        FROM "transaction" t
        WHERE t.account_id = account_id_param
          AND t.transaction_date BETWEEN start_date_param AND end_date_param;
END;
$$ LANGUAGE plpgsql;