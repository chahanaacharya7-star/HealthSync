package com.healthsync.controller;

import com.healthsync.dao.DoctorDAO;
import com.healthsync.dao.PatientDAO;
import com.healthsync.dao.UserDAO;
import com.healthsync.model.Doctor;
import com.healthsync.model.Patient;
import com.healthsync.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class UserSettingsServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    private PatientDAO patientDAO = new PatientDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        String path = request.getServletPath();

        if ("/patient/profile".equals(path)) {
            Patient patient = patientDAO.findByUserId(user.getUserId());
            request.setAttribute("patient", patient);
            request.getRequestDispatcher("/patient/profile.jsp").forward(request, response);
        } else if ("/doctor/profile".equals(path)) {
            Doctor doctor = doctorDAO.findByUserId(user.getUserId());
            request.setAttribute("doctor", doctor);
            request.getRequestDispatcher("/doctor/profile.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        String action = request.getParameter("action");

        if ("updateProfile".equals(action)) {
            // Update User details
            user.setFullName(request.getParameter("fullName"));
            user.setEmail(request.getParameter("email"));
            user.setPhone(request.getParameter("phone"));
            userDAO.update(user);
            session.setAttribute("user", user); // Update session

            if ("patient".equals(user.getRole())) {
                Patient patient = patientDAO.findByUserId(user.getUserId());
                patient.setDateOfBirth(request.getParameter("dateOfBirth"));
                patient.setGender(request.getParameter("gender"));
                patient.setAddress(request.getParameter("address"));
                patient.setBloodGroup(request.getParameter("bloodGroup"));
                patient.setEmergencyContact(request.getParameter("emergencyContact"));
                patientDAO.update(patient);
                response.sendRedirect(request.getContextPath() + "/patient/profile?success=true");
            } else if ("doctor".equals(user.getRole())) {
                Doctor doctor = doctorDAO.findByUserId(user.getUserId());
                doctor.setSpecialization(request.getParameter("specialization"));
                doctor.setQualification(request.getParameter("qualification"));
                try {
                    int experience = Integer.parseInt(request.getParameter("experienceYears"));
                    doctor.setExperienceYears(experience);
                } catch (NumberFormatException e) {
                    doctor.setExperienceYears(0);
                }
                doctor.setAvailableDays(request.getParameter("availableDays"));
                doctorDAO.update(doctor);
                response.sendRedirect(request.getContextPath() + "/doctor/profile?success=true");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
