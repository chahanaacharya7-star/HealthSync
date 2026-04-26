package com.healthsync.model;

public class Doctor {
    private int doctorId;
    private int userId;
    private String specialization;
    private String qualification;
    private int experienceYears;
    private String availableDays;

    // From users table (for display)
    private String fullName;
    private String email;
    private String phone;
    private String status;

    public Doctor() {}

    // Getters and Setters
    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getAvailableDays() { return availableDays; }
    public void setAvailableDays(String availableDays) {
        this.availableDays = availableDays;
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}