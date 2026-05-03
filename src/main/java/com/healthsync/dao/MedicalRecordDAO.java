package com.healthsync.dao;

import com.healthsync.model.MedicalRecord;
import com.healthsync.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {

    public boolean save(MedicalRecord record) {
        String sql = "INSERT INTO medical_records (patient_id, doctor_id, diagnosis, prescription, visit_date, follow_up_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
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
            System.out.println("MedicalRecordDAO save error: " + e.getMessage());
        }
        return false;
    }

    public List<MedicalRecord> findByPatientId(int patientId) {
        List<MedicalRecord> records = new ArrayList<>();
        String sql = "SELECT r.*, ud.full_name AS doctor_name, d.specialization " +
                     "FROM medical_records r " +
                     "JOIN doctors d ON r.doctor_id = d.doctor_id " +
                     "JOIN users ud ON d.user_id = ud.user_id " +
                     "WHERE r.patient_id = ? ORDER BY r.visit_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                records.add(extractRecordFromResultSet(rs, true));
            }
        } catch (SQLException e) {
            System.out.println("MedicalRecordDAO findByPatientId error: " + e.getMessage());
        }
        return records;
    }

    public List<MedicalRecord> findByDoctorId(int doctorId) {
        List<MedicalRecord> records = new ArrayList<>();
        String sql = "SELECT r.*, up.full_name AS patient_name " +
                     "FROM medical_records r " +
                     "JOIN patients p ON r.patient_id = p.patient_id " +
                     "JOIN users up ON p.user_id = up.user_id " +
                     "WHERE r.doctor_id = ? ORDER BY r.visit_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                records.add(extractRecordFromResultSet(rs, false));
            }
        } catch (SQLException e) {
            System.out.println("MedicalRecordDAO findByDoctorId error: " + e.getMessage());
        }
        return records;
    }

    private MedicalRecord extractRecordFromResultSet(ResultSet rs, boolean includeDoctorInfo) throws SQLException {
        MedicalRecord record = new MedicalRecord();
        record.setRecordId(rs.getInt("record_id"));
        record.setPatientId(rs.getInt("patient_id"));
        record.setDoctorId(rs.getInt("doctor_id"));
        record.setDiagnosis(rs.getString("diagnosis"));
        record.setPrescription(rs.getString("prescription"));
        record.setVisitDate(rs.getString("visit_date"));
        record.setFollowUpDate(rs.getString("follow_up_date"));
        record.setCreatedAt(rs.getString("created_at"));
        
        try {
            if (includeDoctorInfo) {
                record.setDoctorName(rs.getString("doctor_name"));
                record.setDoctorSpecialization(rs.getString("specialization"));
            } else {
                record.setPatientName(rs.getString("patient_name"));
            }
        } catch (SQLException e) {
            // Ignore if column doesn't exist in result set
        }
        return record;
    }
}
