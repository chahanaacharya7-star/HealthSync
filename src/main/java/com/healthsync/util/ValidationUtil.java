package com.healthsync.util;

public class ValidationUtil {

    // Check if string is empty or null
    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    // Check if name contains only letters and spaces
    public static boolean isValidName(String name) {
        return name != null && name.matches("[a-zA-Z ]+");
    }

    // Check valid email format
    public static boolean isValidEmail(String email) {
        return email != null &&
                email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
    }

    // Check valid phone (10 digits)
    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }

    // Check password length
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}