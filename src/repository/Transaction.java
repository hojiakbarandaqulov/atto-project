package repository;

import db.DatabaseConnection;
import db.DatabaseUtil;

import java.sql.*;
import java.time.LocalDate;


public class Transaction {
    public void listTransactions() {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT card.number AS number, profile.name AS name, profile.surname AS surname, " +
                "transactions.terminal_id AS terminal, terminal.address AS address, transactions.amount, " +
                "transactions.created_date AS created_date, transactions.type " +
                "FROM transactions " +
                "INNER JOIN card ON transactions.id = card.id " +
                "INNER JOIN profile_card ON card.id = profile_card.id " +
                "INNER JOIN profile ON profile_card.profileId = profile.id " +
                "INNER JOIN terminal ON transactions.id = terminal.id " +
                "ORDER BY transactions.created_date DESC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String cardNumber = resultSet.getString("card_number");
                String profileName = resultSet.getString("profile_name");
                String profileSurname = resultSet.getString("profile_surname");
                int terminalNumber = resultSet.getInt("terminal_number");
                String terminalAddress = resultSet.getString("terminal_address");
                double amount = resultSet.getDouble("amount");
                Timestamp transactionDate = resultSet.getTimestamp("transaction_date");
                String type = resultSet.getString("type");
                System.out.println("CardNumber: " + cardNumber + ", profile Name: " + profileName +
                        ", Profile Surname: " + profileSurname + ", TerminalNumber: " + terminalNumber +
                        ", Terminal Address: " + terminalAddress + ", Amount: " + amount +
                        ", TransactionDate: " + transactionDate + ", Type: " + type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewLastTransaction(int cardNumber) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT card.number AS card_number, terminal.address AS terminal_address, Transactions.amount, " +
                "transactions.created_date AS transaction_date, transactions.type " +
                "FROM transactions " +
                "INNER JOIN card ON transactions.card_id = card.id " +
                "INNER JOIN terminal ON transactions.terminal_id = terminal.id " +
                "WHERE card.number = ? " +
                "ORDER BY transactions.created_date DESC " +
                "LIMIT 1";
//        String sql= """
//                SELECT card.number AS card_number, terminal.address AS terminal_address, t.amount,
//                                t.created_date AS transaction_date, t.type
//                                FROM transactions t
//                                INNER JOIN card ON t.cardid = card.id
//                                INNER JOIN terminal ON t.terminal_id = terminal.id
//                                WHERE card.number = ?
//                                ORDER BY t.created_date DESC
//                                LIMIT 1
//                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cardNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cardNumberResult = resultSet.getString("card_number");
                String terminalAddress = resultSet.getString("terminal_address");
                double amount = resultSet.getDouble("amount");
                String transactionDate = resultSet.getString("transaction_date");
                String type = resultSet.getString("type");
                System.out.println("CardNumber: " + cardNumberResult + ", Address: " + terminalAddress +
                        ", Amount: " + amount + ", TransactionDate: " + transactionDate + ", Type: " + type);
            } else {
                System.out.println("No transactions found for the specified card number.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 14. Company Card Balance
    public void viewCompanyCardBalance(int cardId) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT balance FROM card WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cardId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                double balance = resultSet.getDouble("balance");
                System.out.println("Company Card Balance: " + balance);
            } else {
                System.out.println("Company card not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // 15. Bugungi to'lovlar
    public void listTodayTransactions() {
        Connection connection = DatabaseConnection.getConnection();
        LocalDate today = LocalDate.now();
        String query = "SELECT card.number AS number, terminal.address AS address, transactions.amount, " +
                "transactions.created_date AS created_date, transactions.type " +
                "FROM transactions " +
                "INNER JOIN card ON transactions.id = card.id " +
                "INNER JOIN terminal ON transactions.id = terminal.id " +
                "WHERE DATE(transactions.created_date) = ? " +
                "ORDER BY transactions.created_date DESC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, today);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String cardNumber = resultSet.getString("card_number");
                String address = resultSet.getString("address");
                double amount = resultSet.getDouble("amount");
                Timestamp transactionDate = resultSet.getTimestamp("transaction_date");
                String type = resultSet.getString("type");
                System.out.println("CardNumber: " + cardNumber + ", Address: " + address + ", Amount: " + amount +
                        ", TransactionDate: " + transactionDate + ", Type: " + type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 16. Kunlik to'lovlar
    public void listDailyTransactions(LocalDate date) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT card.number AS number, terminal.address AS address, transactions.amount, " +
                "transactions.created_date AS created_date, transactions.type " +
                "FROM transactions " +
                "INNER JOIN card ON transactions.id = card.id " +
                "INNER JOIN terminal ON transactions.id = terminal.id " +
                "WHERE DATE(transactions.created_date) = ? " +
                "ORDER BY transactions.created_date DESC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, date);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String cardNumber = resultSet.getString("card_number");
                String address = resultSet.getString("address");
                double amount = resultSet.getDouble("amount");
                Timestamp transactionDate = resultSet.getTimestamp("transaction_date");
                String type = resultSet.getString("type");
                System.out.println("CardNumber: " + cardNumber + ", Address: " + address + ", Amount: " + amount +
                        ", TransactionDate: " + transactionDate + ", Type: " + type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 17. Oraliq to'lovlar
    public void listRangeTransactions(LocalDate fromDate, LocalDate toDate) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT card.number AS number, terminal.address AS address, transactions.amount, " +
                "transactions.created_date AS created_date, transactions.type " +
                "FROM transactions " +
                "INNER JOIN card ON transactions.id = card.id " +
                "INNER JOIN terminal ON transactions.id = terminal.id " +
                "WHERE DATE(transactions.created_date) BETWEEN ? AND ? " +
                "ORDER BY transactions.created_date DESC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, fromDate);
            statement.setObject(2, toDate);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String cardNumber = resultSet.getString("card_number");
                String address = resultSet.getString("address");
                double amount = resultSet.getDouble("amount");
                Timestamp transactionDate = resultSet.getTimestamp("transaction_date");
                String type = resultSet.getString("type");
                System.out.println("CardNumber: " + cardNumber + ", Address: " + address + ", Amount: " + amount +
                        ", TransactionDate: " + transactionDate + ", Type: " + type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 18. Transaction by terminal
    public void listTransactionsByTerminal(int terminalCode) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT card.number AS number, terminal.address AS address, transactions.amount, " +
                "transactions.created_date AS created_date, transactions.type " +
                "FROM transactions " +
                "INNER JOIN card ON transactions.id = card.id " +
                "INNER JOIN terminal ON transactions.id = terminal.id " +
                "WHERE terminal.code = ? " +
                "ORDER BY transactions.created_date DESC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, terminalCode);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String cardNumber = resultSet.getString("card_number");
                String address = resultSet.getString("address");
                double amount = resultSet.getDouble("amount");
                Timestamp transactionDate = resultSet.getTimestamp("transaction_date");
                String type = resultSet.getString("type");
                System.out.println("CardNumber: " + cardNumber + ", Address: " + address + ", Amount: " + amount +
                        ", TransactionDate: " + transactionDate + ", Type: " + type);
            }
        } catch (SQLException e) {
        }
    }
    public void listTransactionsByCard(String cardNumber) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT card.number AS number, terminal.address AS address, transactions.amount, " +
                "transactions.created_date AS created_date, transactions.type " +
                "FROM transactions " +
                "INNER JOIN card ON transactions.id = card.id " +
                "INNER JOIN terminal ON transactions.id = terminal.id " +
                "WHERE card.number = ? " +
                "ORDER BY transactions.created_date DESC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cardNumber);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String address = resultSet.getString("address");
                double amount = resultSet.getDouble("amount");
                Timestamp transactionDate = resultSet.getTimestamp("transaction_date");
                String type = resultSet.getString("type");
                System.out.println("CardNumber: " + cardNumber + ", Address: " + address + ", Amount: " + amount +
                        ", TransactionDate: " + transactionDate + ", Type: " + type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}