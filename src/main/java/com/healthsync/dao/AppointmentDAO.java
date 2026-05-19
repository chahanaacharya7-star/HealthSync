package com.healthsync.dao;

import com.healthsync.model.Appointment;
import com.healthsync.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    /** Save new appointment */
    public boolean save(Appointment a) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, " +
                "appointment_date, appointment_time, status, reason) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, a.getPatientId());
            ps.setInt(2, a.getDoctorId());
            ps.setString(3, a.getAppointmentDate());
            ps.setString(4, a.getAppointmentTime());
            ps.setString(5, "pending");
            ps.setString(6, a.getReason());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("AppointmentDAO.save() error: " + e.getMessage());
            return false;
        }
    }

    /** Get all appointments with patient and doctor names */
    public List<Appointment> findAll() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT a.*, " +
                "up.full_name AS patient_name, " +
                "ud.full_name AS doctor_name, " +
                "d.specialization " +
                "FROM appointments a " +
                "JOIN patients p  ON a.patient_id = p.patient_id " +
                "JOIN users up    ON p.user_id = up.user_id " +
                "JOIN doctors d   ON a.doctor_id = d.doctor_id " +
                "JOIN users ud    ON d.user_id = ud.user_id " +
                "ORDER BY a.appointment_date DESC, a.appointment_time DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapResultSet(rs));

        } catch (SQLException e) {
            System.out.println("AppointmentDAO.findAll() error: " + e.getMessage());
        }
        return list;
    }

    /** Find single appointment by ID */
    public Appointment findById(int appointmentId) {
        String sql = "SELECT a.*, " +
                "up.full_name AS patient_name, " +
                "ud.full_name AS doctor_name, " +
                "d.specialization " +
                "FROM appointments a " +
                "JOIN patients p  ON a.patient_id = p.patient_id " +
                "JOIN users up    ON p.user_id = up.user_id " +
                "JOIN doctors d   ON a.doctor_id = d.doctor_id " +
                "JOIN users ud    ON d.user_id = ud.user_id " +
                "WHERE a.appointment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSet(rs);

        } catch (SQLException e) {
            System.out.println("AppointmentDAO.findById() error: " + e.getMessage());
        }
        return null;
    }

    /** Get appointments for a specific patient */
    public List<Appointment> findByPatientId(int patientId) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT a.*, " +
                "up.full_name AS patient_name, " +
                "ud.full_name AS doctor_name, " +
                "d.specialization " +
                "FROM appointments a " +
                "JOIN patients p  ON a.patient_id = p.patient_id " +
                "JOIN users up    ON p.user_id = up.user_id " +
                "JOIN doctors d   ON a.doctor_id = d.doctor_id " +
                "JOIN users ud    ON d.user_id = ud.user_id " +
                "WHERE a.patient_id = ? " +
                "ORDER BY a.appointment_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapResultSet(rs));

        } catch (SQLException e) {
            System.out.println("AppointmentDAO.findByPatientId() error: " + e.getMessage());
        }
        return list;
    }

    /** Get appointments for a specific doctor */
    public List<Appointment> findByDoctorId(int doctorId) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT a.*, " +
                "up.full_name AS patient_name, " +
                "ud.full_name AS doctor_name, " +
                "d.specialization " +
                "FROM appointments a " +
                "JOIN patients p  ON a.patient_id = p.patient_id " +
                "JOIN users up    ON p.user_id = up.user_id " +
                "JOIN doctors d   ON a.doctor_id = d.doctor_id " +
                "JOIN users ud    ON d.user_id = ud.user_id " +
                "WHERE a.doctor_id = ? " +
                "ORDER BY a.appointment_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapResultSet(rs));

        } catch (SQLException e) {
            System.out.println("AppointmentDAO.findByDoctorId() error: " + e.getMessage());
        }
        return list;
    }

    /** Update appointment status */
    public boolean updateStatus(int appointmentId, String status) {
        String sql = "UPDATE appointments SET status = ? WHERE appointment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, appointmentId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("AppointmentDAO.updateStatus() error: " + e.getMessage());
            return false;
        }
    }

    /** Update appointment notes */
    public boolean updateNotes(int appointmentId, String notes) {
        String sql = "UPDATE appointments SET notes = ? WHERE appointment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, notes);
            ps.setInt(2, appointmentId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("AppointmentDAO.updateNotes() error: " + e.getMessage());
            return false;
        }
    }

    /** Delete appointment */
    public boolean delete(int appointmentId) {
        String sql = "DELETE FROM appointments WHERE appointment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("AppointmentDAO.delete() error: " + e.getMessage());
            return false;
        }
    }

    /** Count total appointments */
    public int count() {
        String sql = "SELECT COUNT(*) FROM appointments";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("AppointmentDAO.count() error: " + e.getMessage());
        }
        return 0;
    }

    /** Count appointments by status */
    public int countByStatus(String status) {
        String sql = "SELECT COUNT(*) FROM appointments WHERE status = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("AppointmentDAO.countByStatus() error: " + e.getMessage());
        }
        return 0;
    }

    /** Count appointments for a patient by status */
    public int countByPatientAndStatus(int patientId, String status) {
        String sql = "SELECT COUNT(*) FROM appointments " +
                "WHERE patient_id = ? AND status = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            ps.setString(2, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("AppointmentDAO.countByPatientAndStatus() error: "
                    + e.getMessage());
        }
        return 0;
    }

    /** Count appointments for a doctor by status */
    public int countByDoctorAndStatus(int doctorId, String status) {
        String sql = "SELECT COUNT(*) FROM appointments " +
                "WHERE doctor_id = ? AND status = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ps.setString(2, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("AppointmentDAO.countByDoctorAndStatus() error: "
                    + e.getMessage());
        }
        return 0;
    }

    private Appointment mapResultSet(ResultSet rs) throws SQLException {
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
        a.setDoctorSpecialization(rs.getString("specialization"));
        return a;
    }

    public List<Appointment> findByDate(String value) {
        return null;
    }

    public boolean update(Appointment app) {
        return false;
    }
}