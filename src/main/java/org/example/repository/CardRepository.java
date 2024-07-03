package org.example.repository;

import lombok.Setter;
import org.example.db.DatabaseConnection;
import org.example.dto.Card;
import org.example.enums.CardStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
@Repository
public class CardRepository {
    private Connection connection;
    public Card getCardByNumber(String number) {
        Card card = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "select * from card where number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                card = new Card();
                card.setId(resultSet.getInt("id"));
                card.setNumber(resultSet.getString("number"));
                card.setExpDate(resultSet.getDate("exp_date").toLocalDate());
                card.setBalance(resultSet.getInt("balance"));

                card.setStatus(CardStatus.valueOf(resultSet.getString("status")));

                card.setVisible(resultSet.getBoolean("visible"));
                card.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
            } else {
                card = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return card;
    }
    public int createCard(Card card) {
        int n = 0;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "insert into card(number,exp_date,balance,status,created_date) values(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(card.getNumber()));
            preparedStatement.setDate(2, Date.valueOf(card.getExpDate()));
            preparedStatement.setInt(3, Integer.parseInt(String.valueOf(card.getBalance())));
            preparedStatement.setString(4, card.getStatus().name());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(card.getCreatedDate()));
            n = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return n;
    }

    public Integer updateCard(LocalDate expDate, String number) {
        int effectedRow = 0;
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "update card set exp_date = ? where number=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDate(1, Date.valueOf(expDate));
            preparedStatement.setString(2, number);

            effectedRow = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return effectedRow;
    }

    public List<Card> cardList() {
        List<Card> cards = new LinkedList<>();
        try {
            connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from  card");
            while (resultSet.next()) {
                Card card = new Card();
                card.setId(resultSet.getInt("id"));
                card.setNumber(resultSet.getString("number"));
                card.setExpDate(resultSet.getDate("exp_date").toLocalDate());
                card.setBalance(resultSet.getInt("balance"));
                card.setStatus(CardStatus.valueOf(resultSet.getString("status")));
                card.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                cards.add(card);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return cards;
        }
    }

    public int delete(Card card) {
        int n = 0;
        String sql = "";
        if (card.getStatus() == CardStatus.BLOCK) {
            sql = "update card set visible = 'true' where number = ? ";
        } else {
            sql = "update card set visible = 'false' where number = ? ";
        }
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, card.getNumber());
            n = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return n;
    }

    public int changeStatus(Card card) {
        int n = 0;
        String sql = "";
        if (card.getStatus() == CardStatus.BLOCK) {
            sql = "update card set status = 'ACTIVE' where number = ? ";
        } else {
            sql = "update card set status = 'BLOCK' where number = ? ";
        }
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, card.getNumber());
            n = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return n;
    }

    public void reFill(Integer cardNumber, Double amount) {
        Connection connection = DatabaseConnection.getConnection();
        String query = "update card SET balance = balance + ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, amount);
            statement.setInt(2, cardNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

