package com.vila.dao;

import com.vila.entity.User;
import com.vila.util.DatabaseHelper;

import java.sql.*;

public class UserDao {

    private final Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public static UserDao create() throws SQLException {
        Connection conn = DatabaseHelper.getConnection();
        DatabaseHelper.initTables(conn);
        return new UserDao(conn);
    }

    public boolean insert(User user) throws SQLException {
        String sql = "INSERT INTO user (user_name, user_password, user_role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getUserPassword());
            stmt.setString(3, user.getUserRole());
            return stmt.executeUpdate() == 1;
        }
    }

    public User findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE user_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getString("user_name"),
                    rs.getString("user_password"),
                    rs.getString("user_role"),
                    rs.getInt("user_id")
                );
            }
        }
        return null;
    }

    public boolean updatePassword(String username, String newPassword) throws SQLException {
        String sql = "UPDATE user SET user_password = ? WHERE user_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            return stmt.executeUpdate() == 1;
        }
    }

    public boolean delete(String username) throws SQLException {
        String sql = "DELETE FROM user WHERE user_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            return stmt.executeUpdate() == 1;
        }
    }
}
