package repository;

import db.DatabaseConnection;
import db.DatabaseUtil;
import dto.Card;
import dto.Profile;
import dto.ProfileCard;
import enums.CardStatus;
import enums.ProfileRole;
import enums.ProfileStatus;
import enums.TerminalStatus;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class UserRepository {
    public Card getCardByNumber(String number) {
        Connection connection = null;
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
                    throw new RuntimeException(e);
                }
            }
        }
        return card;
    }

    public Profile getProfileByNumber(String phone) {
        Connection connection = null;
        Profile profile = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "select * from profile where phone = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setPhone(resultSet.getString("phone"));

                profile.setPassword(resultSet.getString("pswd"));
                profile.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                profile.setVisible(resultSet.getBoolean("visible"));
                profile.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profile.setRole(ProfileRole.valueOf(resultSet.getString("role")));

            } else {
                profile = null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return profile;
    }

    public void addCard(String cardNumber, String exp_date) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "";
        Card cardByNumber = getCardByNumber(cardNumber);
        String expDate = String.valueOf(exp_date);
        Profile profileByNumber = getProfileByNumber(cardByNumber.getNumber());
        if (cardByNumber.getStatus() == CardStatus.ACTIVE) {
            sql = "insert into profilecard(cardid,profileid,visible,created_date) values(?,?,?,?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, cardByNumber.getId());
                preparedStatement.setInt(2, profileByNumber.getId());
                preparedStatement.setBoolean(3, cardByNumber.isVisible());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(cardByNumber.getCreatedDate()));
                preparedStatement.executeUpdate();
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

    public ProfileCard ProfileCardList() {
        Connection connection = null;
        ProfileCard profileCard = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from profilecard");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                profileCard = new ProfileCard();
                profileCard.setId(resultSet.getInt("id"));
                profileCard.setCardId(resultSet.getInt("cardid"));
                profileCard.setProfileId(resultSet.getInt("profileid"));
                profileCard.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));

                profileCard.setBalance(resultSet.getInt("balance"));
                profileCard.setVisible(resultSet.getBoolean("visible"));
                profileCard.setCreated_date(resultSet.getTimestamp("created_date").toLocalDateTime());
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
            return profileCard;
        }
    }

    public void changeProfileCardStatus(ProfileCard profileCard) {
        Connection connection = null;
        int n = 0;
        String sql = "";
        if (profileCard.getStatus() == ProfileStatus.BLOCK) {
            sql = "update profilecard set status = 'ACTIVE' where id = ? ";
        } else {
            sql = "update profilecard set status = 'BLOCK' where id = ? ";
        }
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, profileCard.getId());
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
    }

    public int delete(ProfileCard profileCard) {
        Connection connection = null;
        int n = 0;
        String sql = "";
        if (profileCard.getStatus() == ProfileStatus.ACTIVE) {
            sql = "update profilecard set visible = 'true' where id = ? ";
        } else {
            sql = "update profilecard set visible = 'false' where id = ? ";
        }
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, profileCard.getId());
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

}

