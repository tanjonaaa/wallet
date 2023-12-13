package org.wallet.CrudOperations;

import org.wallet.Components.TransferHistoryComponent;
import org.wallet.Models.Currency;
import org.wallet.Models.TranferHistory;
import org.wallet.Models.Transaction;
import org.wallet.connectionDB.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransferHistoryCrudOperations implements CrudOperations<TranferHistory> {
    private static final AccountCrudOperations accountCrud = new AccountCrudOperations();
    private static final TransactionCrudOperations transactionCrud = new TransactionCrudOperations();
    public static final String TRANSFER_HISTORY_ID_COLUMN = "transfer_history_id";
    public static final String DEBIT_TRANSACTION_ID_COLUMN = "debit_transaction_id";
    public static final String CREDIT_TRANSACTION_ID_COLUMN = "credit_transaction_id";
    public static final String TRANSFER_DATE_COLUMN = "transfer_date";
    @Override
    public List<TranferHistory> findAll() {
        return null;
    }

    @Override
    public List<TranferHistory> saveAll(List<TranferHistory> toSave) {
        return null;
    }

    @Override
    public TranferHistory save(TranferHistory toSave) {
        TranferHistory savedTransferHistory;
        Connection connection = ConnectionDB.getConnection();

        String sql;

        if(toSave.getTransferHistoryId() == null){
            sql = "INSERT INTO \"transfer_history\" (debit_transaction_id, credit_transaction_id) " +
                    "VALUES(?, ?) RETURNING *";
        }else{
            sql = "UPDATE \"transfer_history\" " +
                    "SET debit_transaction_id = ?, credit_transaction_id = ? " +
                    "WHERE transaction_history_id = ? RETURNING *";
        }

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, toSave.getDebitTransactionId());
            statement.setString(2, toSave.getCreditTransactionId());

            if(toSave.getTransferHistoryId() != null){
                statement.setString(3, toSave.getTransferHistoryId());
            }

            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            savedTransferHistory = mapResultSet(resultSet);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return savedTransferHistory;
    }

    @Override
    public TranferHistory delete(TranferHistory toDelete) {
        return null;
    }

    public List<TransferHistoryComponent> getHistory(LocalDateTime startDate, LocalDateTime endDate){
        if(endDate.isAfter(startDate) || endDate.equals(startDate)){
            List<TransferHistoryComponent> histories = new ArrayList<>();
            Connection connection = ConnectionDB.getConnection();
            String sql = "SELECT * FROM \"transfer_history\" " +
                    "WHERE transfer_date >= ? AND transfer_date <= ?";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setObject(1, startDate);
                statement.setObject(2, endDate);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()){
                    TranferHistory transferHistory = mapResultSet(resultSet);

                    Transaction debitTransaction = transactionCrud.getById(
                            transferHistory.getDebitTransactionId()
                    );

                    Transaction creditTransaction = transactionCrud.getById(
                            transferHistory.getCreditTransactionId()
                    );

                    TransferHistoryComponent component = TransferHistoryComponent.builder()
                                    .transferHistoryId(transferHistory.getTransferHistoryId())
                                    .debitAccount(
                                            accountCrud.getAccountById(debitTransaction.getAccountId())
                                    )
                                    .creditAccount(
                                            accountCrud.getAccountById(creditTransaction.getAccountId())
                                    )
                                    .amount(creditTransaction.getAmount())
                                    .transferDate(creditTransaction.getTransactionDate())
                                    .build();
                    histories.add(component);
                }

                statement.close();
                connection.close();
                return histories;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            return null;
        }
    }

    private TranferHistory mapResultSet(ResultSet resultSet) throws SQLException {
        return TranferHistory.builder()
                .debitTransactionId(resultSet.getString(DEBIT_TRANSACTION_ID_COLUMN))
                .creditTransactionId(resultSet.getString(CREDIT_TRANSACTION_ID_COLUMN))
                .build();
    }
}
