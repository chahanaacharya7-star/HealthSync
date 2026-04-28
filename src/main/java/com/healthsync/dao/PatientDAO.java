package com.healthsync.dao;

import com.healthsync.model.Patient;
import com.healthsync.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    // Save patient details
    public boolean save(Patient patient) {
        String sql = "INSERT INTO patients (user_id, date_of_birth, gender, " +
                "address, blood_group, emergency_contact) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patient.getUserId());
            ps.setString(2, patient.getDateOfBirth());
            ps.setString(3, patient.getGender());
            ps.setString(4, patient.getAddress());
            ps.setString(5, patient.getBloodGroup());
            ps.setString(6, patient.getEmergencyContact());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("PatientDAO save error: " + e.getMessage());
            return false;
        }
    }

    // Get all patients with user details
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT p.*, u.full_name, u.email, u.phone, u.status " +
                "FROM patients p JOIN users u ON p.user_id = u.user_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Patient p = new Patient();
                p.setPatientId(rs.getInt("patient_id"));
                p.setUserId(rs.getInt("user_id"));
                p.setDateOfBirth(rs.getString("date_of_birth"));
                p.setGender(rs.getString("gender"));
                p.setAddress(rs.getString("address"));
                p.setBloodGroup(rs.getString("blood_group"));
                p.setEmergencyContact(rs.getString("emergency_contact"));
                p.setFullName(rs.getString("full_name"));
                p.setEmail(rs.getString("email"));
                p.setPhone(rs.getString("phone"));
                p.setStatus(rs.getString("status"));
                patients.add(p);
            }

        } catch (SQLException e) {
            System.out.println("PatientDAO findAll error: " + e.getMessage());
        }
        return patients;
    }

    // Find patient by patient_id
    public Patient findById(int patientId) {
        String sql = "SELECT p.*, u.full_name, u.email, u.phone, u.status " +
                "FROM patients p JOIN users u ON p.user_id = u.user_id " +
                "WHERE p.patient_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Patient p = new Patient();
                p.setPatientId(rs.getInt("patient_id"));
                p.setUserId(rs.getInt("user_id"));
                p.setDateOfBirth(rs.getString("date_of_birth"));
                p.setGender(rs.getString("gender"));
                p.setAddress(rs.getString("address"));
                p.setBloodGroup(rs.getString("blood_group"));
                p.setEmergencyContact(rs.getString("emergency_contact"));
                p.setFullName(rs.getString("full_name"));
                p.setEmail(rs.getString("email"));
                p.setPhone(rs.getString("phone"));
                p.setStatus(rs.getString("status"));
                return p;
            }

        } catch (SQLException e) {
            System.out.println("PatientDAO findById error: " + e.getMessage());
        }
        return null;
    }

    // Update patient
    public boolean update(Patient patient) {
        String sql = "UPDATE patients SET date_of_birth=?, gender=?, address=?, " +
                "blood_group=?, emergency_contact=? WHERE patient_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, patient.getDateOfBirth());
            ps.setString(2, patient.getGender());
            ps.setString(3, patient.getAddress());
            ps.setString(4, patient.getBloodGroup());
            ps.setString(5, patient.getEmergencyContact());
            ps.setInt(6, patient.getPatientId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("PatientDAO update error: " + e.getMessage());
            return false;
        }
    }

    // Delete patient (also deletes user due to CASCADE)
    public boolean delete(int patientId) {
        // First get user_id
        String getUserId = "SELECT user_id FROM patients WHERE patient_id = ?";
        String deleteUser = "DELETE FROM users WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection()) {

            // Get user_id
            PreparedStatement ps1 = conn.prepareStatement(getUserId);
            ps1.setInt(1, patientId);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                // Delete user (CASCADE deletes patient too)
                PreparedStatement ps2 = conn.prepareStatement(deleteUser);
                ps2.setInt(1, userId);
                return ps2.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("PatientDAO delete error: " + e.getMessage());
        }
        return false;
    }
}