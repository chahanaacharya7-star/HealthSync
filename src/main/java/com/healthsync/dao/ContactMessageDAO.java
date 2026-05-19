package com.healthsync.dao;

import com.healthsync.model.ContactMessage;
import com.healthsync.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactMessageDAO {

    /** Save contact message */
    public boolean save(ContactMessage msg) {
        String sql = "INSERT INTO contact_messages " +
                "(full_name, email, subject, message) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, msg.getFullName());
            ps.setString(2, msg.getEmail());
            ps.setString(3, msg.getSubject());
            ps.setString(4, msg.getMessage());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("ContactMessageDAO.save() error: " + e.getMessage());
            return false;
        }
    }

    /** Get all messages */
    public List<ContactMessage> findAll() {
        List<ContactMessage> list = new ArrayList<>();
        String sql = "SELECT * FROM contact_messages ORDER BY sent_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ContactMessage m = new ContactMessage();
                m.setMessageId(rs.getInt("message_id"));
                m.setFullName(rs.getString("full_name"));
                m.setEmail(rs.getString("email"));
                m.setSubject(rs.getString("subject"));
                m.setMessage(rs.getString("message"));
                m.setSentAt(rs.getString("sent_at"));
                list.add(m);
            }

        } catch (SQLException e) {
            System.out.println("ContactMessageDAO.findAll() error: " + e.getMessage());
        }
        return list;
    }
}