package com.healthsync.dao;

import com.healthsync.model.User;
import com.healthsync.util.DBConnection;
import java.sql.*;

public class UserDAO {

    // Save new user to database
    public boolean save(User user) {
        String sql = "INSERT INTO users (full_name, email, phone, password, role, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());
            ps.setString(6, user.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("UserDAO save error: " + e.getMessage());
            return false;
        }
    }

    // Find user by email (for login)
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
                return user;
            }

        } catch (SQLException e) {
            System.out.println("UserDAO findByEmail error: " + e.getMessage());
        }
        return null;
    }

    // Check if email already exists
    public boolean emailExists(String email) {
        String sql = "SELECT user_id FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("UserDAO emailExists error: " + e.getMessage());
            return false;
        }
    }

    // Check if phone already exists
    public boolean phoneExists(String phone) {
        String sql = "SELECT user_id FROM users WHERE phone = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("UserDAO phoneExists error: " + e.getMessage());
            return false;
        }
    }

    // Get last inserted user ID
    public int getLastInsertedId() {
        String sql = "SELECT MAX(user_id) FROM users";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("UserDAO getLastId error: " + e.getMessage());
        }
        return -1;
    }
}