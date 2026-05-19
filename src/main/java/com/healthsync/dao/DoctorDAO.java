package com.healthsync.dao;

import com.healthsync.model.Doctor;
import com.healthsync.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    /** Save new doctor profile */
    public boolean save(Doctor doctor) {
        String sql = "INSERT INTO doctors (user_id, specialization, qualification, " +
                "experience_years, available_days) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctor.getUserId());
            ps.setString(2, doctor.getSpecialization());
            ps.setString(3, doctor.getQualification());
            ps.setInt(4, doctor.getExperienceYears());
            ps.setString(5, doctor.getAvailableDays());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("DoctorDAO.save() error: " + e.getMessage());
            return false;
        }
    }

    /** Get all doctors joined with user info */
    public List<Doctor> findAll() {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT d.*, u.full_name, u.email, u.phone, u.status " +
                "FROM doctors d JOIN users u ON d.user_id = u.user_id " +
                "ORDER BY d.doctor_id DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapResultSet(rs));

        } catch (SQLException e) {
            System.out.println("DoctorDAO.findAll() error: " + e.getMessage());
        }
        return list;
    }

    /** Find doctor by doctor_id */
    public Doctor findById(int doctorId) {
        String sql = "SELECT d.*, u.full_name, u.email, u.phone, u.status " +
                "FROM doctors d JOIN users u ON d.user_id = u.user_id " +
                "WHERE d.doctor_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSet(rs);

        } catch (SQLException e) {
            System.out.println("DoctorDAO.findById() error: " + e.getMessage());
        }
        return null;
    }

    /** Find doctor by user_id */
    public Doctor findByUserId(int userId) {
        String sql = "SELECT d.*, u.full_name, u.email, u.phone, u.status " +
                "FROM doctors d JOIN users u ON d.user_id = u.user_id " +
                "WHERE d.user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSet(rs);

        } catch (SQLException e) {
            System.out.println("DoctorDAO.findByUserId() error: " + e.getMessage());
        }
        return null;
    }

    /** Update doctor profile */
    public boolean update(Doctor doctor) {
        String sql = "UPDATE doctors SET specialization=?, qualification=?, " +
                "experience_years=?, available_days=? WHERE doctor_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, doctor.getSpecialization());
            ps.setString(2, doctor.getQualification());
            ps.setInt(3, doctor.getExperienceYears());
            ps.setString(4, doctor.getAvailableDays());
            ps.setInt(5, doctor.getDoctorId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("DoctorDAO.update() error: " + e.getMessage());
            return false;
        }
    }

    /** Delete doctor — deletes user which cascades */
    public boolean delete(int doctorId) {
        String getUid = "SELECT user_id FROM doctors WHERE doctor_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(getUid)) {

            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                PreparedStatement ps2 = conn.prepareStatement(
                        "DELETE FROM users WHERE user_id = ?");
                ps2.setInt(1, userId);
                return ps2.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("DoctorDAO.delete() error: " + e.getMessage());
        }
        return false;
    }

    /** Count total doctors */
    public int count() {
        String sql = "SELECT COUNT(*) FROM doctors";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("DoctorDAO.count() error: " + e.getMessage());
        }
        return 0;
    }

    private Doctor mapResultSet(ResultSet rs) throws SQLException {
        Doctor d = new Doctor();
        d.setDoctorId(rs.getInt("doctor_id"));
        d.setUserId(rs.getInt("user_id"));
        d.setSpecialization(rs.getString("specialization"));
        d.setQualification(rs.getString("qualification"));
        d.setExperienceYears(rs.getInt("experience_years"));
        d.setAvailableDays(rs.getString("available_days"));
        d.setFullName(rs.getString("full_name"));
        d.setEmail(rs.getString("email"));
        d.setPhone(rs.getString("phone"));
        d.setStatus(rs.getString("status"));
        return d;
    }
}