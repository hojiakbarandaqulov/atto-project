package org.example.repository;

import org.example.db.DatabaseConnection;
import org.example.dto.Profile;
import org.example.dto.ProfileCard;
import org.example.enums.ProfileRole;
import org.example.enums.ProfileStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
@Repository

public class ProfileRepository {

    public Profile getProfileByPhone(String phone) {
        Connection connection = null;
        Profile profile = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "select * from profile where phone = ? and visible = true";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setPhone(resultSet.getString("phone"));
                profile.setPassword(resultSet.getString("password"));

                Timestamp timestamp = resultSet.getTimestamp("created_date");
                profile.setCreatedDate(timestamp.toLocalDateTime());

                String status = resultSet.getString("status");
                profile.setStatus(ProfileStatus.valueOf(status));

                String role = resultSet.getString("role");
                profile.setRole(ProfileRole.valueOf(role));

                profile.setVisible(resultSet.getBoolean("visible"));

            } else {
                return null;
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

    //    public Profile getByPhone(String phone) {
//        Profile profileDTO = null;
//        Connection connection = null;
//        try {
//            connection = DatabaseConnection.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from profile where phone = ? ");
//            preparedStatement.setString(1, phone);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                profileDTO = new Profile();
//                profileDTO.setId(resultSet.getInt("id"));
//                profileDTO.setName(resultSet.getString("name"));
//                profileDTO.setSurname(resultSet.getString("surname"));
//                profileDTO.setPhone(resultSet.getString("phone"));
//                profileDTO.setPassword(resultSet.getString("password"));
//                profileDTO.setCreated_date(LocalDate.from((resultSet.getTimestamp("created_date").toLocalDateTime())));
//                profileDTO.setVisible(resultSet.getBoolean("visible"));
//                profileDTO.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
//                profileDTO.setUserType(UserType.valueOf(ProfileStatus.ACTIVE.toString()));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return profileDTO;
//    }
    public int create(Profile profile) {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = " insert into profile(name,surname,phone,password,status,role) values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, profile.getName());
            preparedStatement.setString(2, profile.getSurname());
            preparedStatement.setString(3, profile.getPhone());
            preparedStatement.setString(4, profile.getPassword());
            preparedStatement.setString(5, profile.getStatus().name());
            preparedStatement.setString(6, profile.getRole().name());
            int n = preparedStatement.executeUpdate();
            return n;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
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

    public Profile loginUser(String phone, String password) {
        Connection connection = null;
        Profile profile = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "select * from profile where phone = ? and password = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                profile = new Profile();
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setPhone(resultSet.getString("phone"));
                profile.setPassword(resultSet.getString("password"));
//                profile.setCreated_date(resultSet.getTimestamp("created_date").toLocalDateTime().toLocalDate());
//                profile.setUserType(UserType.valueOf(resultSet.getString("role")));
                profile.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profile.setVisible(resultSet.getBoolean("visible"));
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

    public List<Profile> ProfileList() {
        Connection connection = null;
        Profile profile = null;
        ProfileCard profileCard = null;
        List<Profile> profileList = new LinkedList<>();
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select profile.id, profile.name, profile.surname, profile.phone, " +
                    " (select count(*) from profile_card where profileid = id) as cardCount, " +
                    " profile.status, profile.created_date " +
                    " from profile as profile ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setPhone(resultSet.getString("phone"));
                profile.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profile.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                profileList.add(profile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profileList;
    }
    public int changeStatus(ProfileCard profileCard) {
        Connection connection = null;
        int n = 0;
        String sql = "";
        if (profileCard.getStatus() == ProfileStatus.ACTIVE) {
            sql = "update profile_card set status = 'BLOCK' where profileid = ? ";
        } else {
            sql = "update profile_card set status = 'ACTIVE' where profileid = ? ";
        }
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, profileCard.getProfileId());
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

