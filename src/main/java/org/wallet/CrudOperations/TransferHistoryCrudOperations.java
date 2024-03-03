package org.wallet.CrudOperations;

import org.springframework.stereotype.Repository;
import org.wallet.Components.TransferHistoryComponent;
import org.wallet.Models.TransferHistory;
import org.wallet.Models.Transaction;
import org.wallet.ConnectionDB.ConnectionDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransferHistoryCrudOperations extends AutoCrudOperations<TransferHistory> {
    private static final AccountCrudOperations accountCrud = new AccountCrudOperations();
    private static final TransactionCrudOperations transactionCrud = new TransactionCrudOperations();

    @Override
    public List<TransferHistory> saveAll(List<TransferHistory> toSave) {
        return null;
    }

    @Override
    public TransferHistory save(TransferHistory toSave) {
        TransferHistory savedTransferHistory;
        Connection connection = ConnectionDB.getConnection();

        String sql;

        if(toSave.getTransferHistoryId() == null){
            sql = "INSERT INTO \"transfer_history\" (debit_transaction_id, credit_transaction_id) " +
                    "VALUES(?, ?) RETURNING *";
        }else{
            sql = "UPDATE \"transfer_history\" " +
                    "SET debit_transaction_id = ?, credit_transaction_id = ? " +
                    "WHERE id = ? RETURNING *";
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
    public TransferHistory delete(TransferHistory toDelete) {
        return null;
    }

    public TransferHistory getByCreditTransaction(String id, LocalDateTime limit){
        Connection connection = ConnectionDB.getConnection();
        TransferHistory result = null;
        String sql = "SELECT * FROM transfer_history " +
                "WHERE credit_transaction_id = ? ORDER BY  transfer_date DESC LIMIT 1";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                result = mapResultSet(resultSet);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
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
                    TransferHistory transferHistory = mapResultSet(resultSet);

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
}
