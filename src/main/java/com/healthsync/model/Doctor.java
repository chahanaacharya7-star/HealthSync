package com.healthsync.model;

public class Doctor extends User {
    private int doctorId;
    private String specialization;
    private String qualification;
    private int experienceYears;
    private String availableDays;

    public Doctor() {}

    // Getters and Setters
    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }

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
}