package com.healthsync.dao;

import com.healthsync.model.MedicalRecord;
import com.healthsync.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {

    /** Save new medical record */
    public boolean save(MedicalRecord record) {
        String sql = "INSERT INTO medical_records " +
                "(patient_id, doctor_id, diagnosis, prescription, " +
                "visit_date, follow_up_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, record.getPatientId());
            ps.setInt(2, record.getDoctorId());
            ps.setString(3, record.getDiagnosis());
            ps.setString(4, record.getPrescription());
            ps.setString(5, record.getVisitDate());
            ps.setString(6, record.getFollowUpDate());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("MedicalRecordDAO.save() error: " + e.getMessage());
            return false;
        }
    }

    /** Get all records for a patient */
    public List<MedicalRecord> findByPatientId(int patientId) {
        List<MedicalRecord> list = new ArrayList<>();
        String sql = "SELECT mr.*, " +
                "ud.full_name AS doctor_name, " +
                "d.specialization, " +
                "up.full_name AS patient_name " +
                "FROM medical_records mr " +
                "JOIN doctors d  ON mr.doctor_id = d.doctor_id " +
                "JOIN users ud   ON d.user_id = ud.user_id " +
                "JOIN patients p ON mr.patient_id = p.patient_id " +
                "JOIN users up   ON p.user_id = up.user_id " +
                "WHERE mr.patient_id = ? " +
                "ORDER BY mr.visit_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapResultSet(rs));

        } catch (SQLException e) {
            System.out.println("MedicalRecordDAO.findByPatientId() error: " + e.getMessage());
        }
        return list;
    }

    /** Get all records for a doctor */
    public List<MedicalRecord> findByDoctorId(int doctorId) {
        List<MedicalRecord> list = new ArrayList<>();
        String sql = "SELECT mr.*, " +
                "ud.full_name AS doctor_name, " +
                "d.specialization, " +
                "up.full_name AS patient_name " +
                "FROM medical_records mr " +
                "JOIN doctors d  ON mr.doctor_id = d.doctor_id " +
                "JOIN users ud   ON d.user_id = ud.user_id " +
                "JOIN patients p ON mr.patient_id = p.patient_id " +
                "JOIN users up   ON p.user_id = up.user_id " +
                "WHERE mr.doctor_id = ? " +
                "ORDER BY mr.visit_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapResultSet(rs));

        } catch (SQLException e) {
            System.out.println("MedicalRecordDAO.findByDoctorId() error: " + e.getMessage());
        }
        return list;
    }

    /** Find single record by ID */
    public MedicalRecord findById(int recordId) {
        String sql = "SELECT mr.*, " +
                "ud.full_name AS doctor_name, " +
                "d.specialization, " +
                "up.full_name AS patient_name " +
                "FROM medical_records mr " +
                "JOIN doctors d  ON mr.doctor_id = d.doctor_id " +
                "JOIN users ud   ON d.user_id = ud.user_id " +
                "JOIN patients p ON mr.patient_id = p.patient_id " +
                "JOIN users up   ON p.user_id = up.user_id " +
                "WHERE mr.record_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, recordId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSet(rs);

        } catch (SQLException e) {
            System.out.println("MedicalRecordDAO.findById() error: " + e.getMessage());
        }
        return null;
    }

    /** Count records for a patient */
    public int countByPatientId(int patientId) {
        String sql = "SELECT COUNT(*) FROM medical_records WHERE patient_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("MedicalRecordDAO.countByPatientId() error: " + e.getMessage());
        }
        return 0;
    }

    /** Delete a record */
    public boolean delete(int recordId) {
        String sql = "DELETE FROM medical_records WHERE record_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, recordId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("MedicalRecordDAO.delete() error: " + e.getMessage());
            return false;
        }
    }

    private MedicalRecord mapResultSet(ResultSet rs) throws SQLException {
        MedicalRecord r = new MedicalRecord();
        r.setRecordId(rs.getInt("record_id"));
        r.setPatientId(rs.getInt("patient_id"));
        r.setDoctorId(rs.getInt("doctor_id"));
        r.setDiagnosis(rs.getString("diagnosis"));
        r.setPrescription(rs.getString("prescription"));
        r.setVisitDate(rs.getString("visit_date"));
        r.setFollowUpDate(rs.getString("follow_up_date"));
        r.setCreatedAt(rs.getString("created_at"));
        r.setDoctorName(rs.getString("doctor_name"));
        r.setDoctorSpecialization(rs.getString("specialization"));
        r.setPatientName(rs.getString("patient_name"));
        return r;
    }
}