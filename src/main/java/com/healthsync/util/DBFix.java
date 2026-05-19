package com.healthsync.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DBFix {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT IGNORE INTO patients (patient_id, user_id, date_of_birth, gender, address, blood_group, emergency_contact) VALUES (1, 2, '2000-01-01', 'Other', 'Kathmandu', 'O+', '9800000000')";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                int rows = ps.executeUpdate();
                System.out.println("Fix applied. Rows affected: " + rows);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
