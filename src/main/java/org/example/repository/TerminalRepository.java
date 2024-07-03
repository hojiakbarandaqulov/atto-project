package org.example.repository;

import org.example.db.DatabaseConnection;
import org.example.dto.Terminal;
import org.example.dto.TransactionDTO;
import org.example.enums.TerminalStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
@Repository
public class TerminalRepository {
    public int createTerminal(Terminal terminal) {
        Connection connection = null;
        int n = 0;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "insert into terminal(code,address) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, terminal.getCode());
            preparedStatement.setString(2, terminal.getAddress());
            n = preparedStatement.executeUpdate();
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
        return n;
    }

    public List<Terminal> terminalList() {
        Connection connection = null;
        List<Terminal> terminalList = new LinkedList<>();
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from terminal");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Terminal terminal = new Terminal();
                terminal.setId(resultSet.getInt("id"));
                terminal.setCode(resultSet.getString("code"));
                terminal.setAddress(resultSet.getString("address"));
                terminal.setStatus(TerminalStatus.valueOf(resultSet.getString("status")));
                terminal.setVisible(resultSet.getBoolean("visible"));
                terminal.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                terminalList.add(terminal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return terminalList;
    }

    public int updateTerminal(String code, String address) {
        int effectedRow = 0;
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "update terminal set code =? where address =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, code);
            preparedStatement.setString(2, address);

            effectedRow = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return effectedRow;
    }

    public Terminal getTerminalByNumber(String code) {
        Connection connection = null;
        Terminal terminal = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "select * from terminal where code = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                terminal = new Terminal();
                terminal.setId(resultSet.getInt("id"));
                terminal.setCode(resultSet.getString("code"));
                terminal.setAddress(resultSet.getString("address"));
                terminal.setStatus(TerminalStatus.valueOf(resultSet.getString("status")));
                terminal.setVisible(resultSet.getBoolean("visible"));
                terminal.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());

            } else {
                terminal = null;
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
        return terminal;
    }

    public int changeStatus(Terminal terminal) {
        Connection connection = null;
        int n = 0;
        String sql = "";
        if (terminal.getStatus() == TerminalStatus.BLOCK) {
            sql = "update termianl set status = 'ACTIVE' where code = ? ";
        } else {
            sql = "update terminal set status = 'BLOCK' where code = ? ";
        }
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, terminal.getCode());
            n = preparedStatement.executeUpdate();
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
        return n;
    }

    public int deleteTerminal(Terminal terminal) {
        Connection connection = null;
        int n = 0;
        String sql = "";
        if (terminal.getStatus() == TerminalStatus.BLOCK) {
            sql = "update termianl set visible = 'true' where code = ? ";
        } else {
            sql = "update terminal set visible = 'false' where code = ? ";
        }
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, terminal.getCode());
            n = preparedStatement.executeUpdate();
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
        return n;
    }

    public Terminal getTerminalByCode(String code) {
        Connection connection = null;
        Terminal terminal = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "select * from terminal where code = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                terminal = new Terminal();
                terminal.setId(resultSet.getInt("id"));
                terminal.setCode(resultSet.getString("code"));
                terminal.setAddress(resultSet.getString("address"));
                terminal.setStatus(TerminalStatus.valueOf(resultSet.getString("status")));
                terminal.setVisible(resultSet.getBoolean("visible"));
                terminal.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
            } else {
                terminal = null;
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
        return terminal;
    }
}