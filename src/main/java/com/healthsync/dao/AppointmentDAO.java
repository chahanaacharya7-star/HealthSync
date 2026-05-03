package com.healthsync.dao;

import com.healthsync.model.Appointment;
import com.healthsync.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    public boolean save(Appointment app) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, " +
                "appointment_time, reason, notes, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, app.getPatientId());
            ps.setInt(2, app.getDoctorId());
            ps.setString(3, app.getAppointmentDate());
            ps.setString(4, app.getAppointmentTime());
            ps.setString(5, app.getReason());
            ps.setString(6, app.getNotes());
            ps.setString(7, app.getStatus() != null ? app.getStatus() : "pending");

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("AppointmentDAO save error: " + e.getMessage());
            return false;
        }
    }

    public List<Appointment> findAll() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT a.*, p.full_name as patient_name, d.full_name as doctor_name " +
                "FROM appointments a " +
                "JOIN patients pat ON a.patient_id = pat.patient_id " +
                "JOIN users p ON pat.user_id = p.user_id " +
                "JOIN doctors doc ON a.doctor_id = doc.doctor_id " +
                "JOIN users d ON doc.user_id = d.user_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToAppointment(rs));
            }
        } catch (SQLException e) {
            System.out.println("AppointmentDAO findAll error: " + e.getMessage());
        }
        return list;
    }

    public Appointment findById(int id) {
        String sql = "SELECT a.*, p.full_name as patient_name, d.full_name as doctor_name " +
                "FROM appointments a " +
                "JOIN patients pat ON a.patient_id = pat.patient_id " +
                "JOIN users p ON pat.user_id = p.user_id " +
                "JOIN doctors doc ON a.doctor_id = doc.doctor_id " +
                "JOIN users d ON doc.user_id = d.user_id " +
                "WHERE a.appointment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToAppointment(rs);
            }
        } catch (SQLException e) {
            System.out.println("AppointmentDAO findById error: " + e.getMessage());
        }
        return null;
    }

    public boolean update(Appointment app) {
        String sql = "UPDATE appointments SET appointment_date=?, appointment_time=?, " +
                "reason=?, notes=?, status=? WHERE appointment_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, app.getAppointmentDate());
            ps.setString(2, app.getAppointmentTime());
            ps.setString(3, app.getReason());
            ps.setString(4, app.getNotes());
            ps.setString(5, app.getStatus());
            ps.setInt(6, app.getAppointmentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("AppointmentDAO update error: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM appointments WHERE appointment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("AppointmentDAO delete error: " + e.getMessage());
            return false;
        }
    }

    public List<Appointment> findByPatientId(int patientId) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT a.*, p.full_name as patient_name, d.full_name as doctor_name " +
                "FROM appointments a " +
                "JOIN patients pat ON a.patient_id = pat.patient_id " +
                "JOIN users p ON pat.user_id = p.user_id " +
                "JOIN doctors doc ON a.doctor_id = doc.doctor_id " +
                "JOIN users d ON doc.user_id = d.user_id " +
                "WHERE a.patient_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToAppointment(rs));
            }
        } catch (SQLException e) {
            System.out.println("AppointmentDAO findByPatientId error: " + e.getMessage());
        }
        return list;
    }

    public List<Appointment> findByDoctorId(int doctorId) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT a.*, p.full_name as patient_name, d.full_name as doctor_name " +
                "FROM appointments a " +
                "JOIN patients pat ON a.patient_id = pat.patient_id " +
                "JOIN users p ON pat.user_id = p.user_id " +
                "JOIN doctors doc ON a.doctor_id = doc.doctor_id " +
                "JOIN users d ON doc.user_id = d.user_id " +
                "WHERE a.doctor_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToAppointment(rs));
            }
        } catch (SQLException e) {
            System.out.println("AppointmentDAO findByDoctorId error: " + e.getMessage());
        }
        return list;
    }

    public List<Appointment> findByDate(String date) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT a.*, p.full_name as patient_name, d.full_name as doctor_name " +
                "FROM appointments a " +
                "JOIN patients pat ON a.patient_id = pat.patient_id " +
                "JOIN users p ON pat.user_id = p.user_id " +
                "JOIN doctors doc ON a.doctor_id = doc.doctor_id " +
                "JOIN users d ON doc.user_id = d.user_id " +
                "WHERE a.appointment_date = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToAppointment(rs));
            }
        } catch (SQLException e) {
            System.out.println("AppointmentDAO findByDate error: " + e.getMessage());
        }
        return list;
    }

    public boolean updateStatus(int id, String status) {
        String sql = "UPDATE appointments SET status = ? WHERE appointment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("AppointmentDAO updateStatus error: " + e.getMessage());
            return false;
        }
    }

    private Appointment mapResultSetToAppointment(ResultSet rs) throws SQLException {
        Appointment a = new Appointment();
        a.setAppointmentId(rs.getInt("appointment_id"));
        a.setPatientId(rs.getInt("patient_id"));
        a.setDoctorId(rs.getInt("doctor_id"));
        a.setAppointmentDate(rs.getString("appointment_date"));
        a.setAppointmentTime(rs.getString("appointment_time"));
        a.setStatus(rs.getString("status"));
        a.setReason(rs.getString("reason"));
        a.setNotes(rs.getString("notes"));
        a.setCreatedAt(rs.getString("created_at"));
        a.setPatientName(rs.getString("patient_name"));
        a.setDoctorName(rs.getString("doctor_name"));
        return a;
    }
}
