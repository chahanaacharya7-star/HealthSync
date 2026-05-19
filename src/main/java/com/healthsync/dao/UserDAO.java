package com.healthsync.dao;

import com.healthsync.model.User;
import com.healthsync.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAO {

    /** Save new user to database */
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
            System.out.println("UserDAO.save() error: " + e.getMessage());
            return false;
        }
    }

    /** Find user by email — used for login */
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSet(rs);

        } catch (SQLException e) {
            System.out.println("UserDAO.findByEmail() error: " + e.getMessage());
        }
        return null;
    }

    /** Find user by user_id */
    public User findById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSet(rs);

        } catch (SQLException e) {
            System.out.println("UserDAO.findById() error: " + e.getMessage());
        }
        return null;
    }

    /** Find user by phone number */
    public User findByPhone(String phone) {
        String sql = "SELECT * FROM users WHERE phone = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSet(rs);

        } catch (SQLException e) {
            System.out.println("UserDAO.findByPhone() error: " + e.getMessage());
        }
        return null;
    }

    /** Get all users */
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY created_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) users.add(mapResultSet(rs));

        } catch (SQLException e) {
            System.out.println("UserDAO.findAll() error: " + e.getMessage());
        }
        return users;
    }

    /** Check if email already exists */
    public boolean emailExists(String email) {
        String sql = "SELECT user_id FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            return ps.executeQuery().next();

        } catch (SQLException e) {
            System.out.println("UserDAO.emailExists() error: " + e.getMessage());
            return false;
        }
    }

    /** Check if phone already exists */
    public boolean phoneExists(String phone) {
        String sql = "SELECT user_id FROM users WHERE phone = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phone);
            return ps.executeQuery().next();

        } catch (SQLException e) {
            System.out.println("UserDAO.phoneExists() error: " + e.getMessage());
            return false;
        }
    }

    /** Update user full name and phone */
    public boolean update(User user) {
        String sql = "UPDATE users SET full_name = ?, phone = ? WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getPhone());
            ps.setInt(3, user.getUserId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("UserDAO.update() error: " + e.getMessage());
            return false;
        }
    }

    /** Update user account status: approved / rejected / pending */
    public boolean updateStatus(int userId, String status) {
        String sql = "UPDATE users SET status = ? WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("UserDAO.updateStatus() error: " + e.getMessage());
            return false;
        }
    }

    /** Update user password (hashed) */
    public boolean updatePassword(int userId, String hashedPassword) {
        String sql = "UPDATE users SET password = ? WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, hashedPassword);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("UserDAO.updatePassword() error: " + e.getMessage());
            return false;
        }
    }

    /** Delete user by ID — cascades to patients/doctors */
    public boolean delete(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("UserDAO.delete() error: " + e.getMessage());
            return false;
        }
    }

    /** Get last inserted user ID */
    public int getLastInsertedId() {
        String sql = "SELECT MAX(user_id) FROM users";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("UserDAO.getLastInsertedId() error: " + e.getMessage());
        }
        return -1;
    }

    /** Map ResultSet row to User object */
    private User mapResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setFullName(rs.getString("full_name"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        user.setStatus(rs.getString("status"));
        user.setCreatedAt(rs.getString("created_at"));
        return user;
    }

    public Map<String, Integer> getCounts() {
        Map<String, Integer> counts = new java.util.HashMap<>();
        counts.put("patients", new PatientDAO().count());
        counts.put("doctors", new DoctorDAO().count());
        counts.put("appointments", new AppointmentDAO().count());
        return counts;
    }

    public List<User> findAllByStatus(String status) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE status = ? ORDER BY created_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) users.add(mapResultSet(rs));

        } catch (SQLException e) {
            System.out.println("UserDAO.findAllByStatus() error: " + e.getMessage());
        }
        return users;
    }
}