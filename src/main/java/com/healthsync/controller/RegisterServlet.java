package com.healthsync.controller;

import com.healthsync.dao.PatientDAO;
import com.healthsync.dao.UserDAO;
import com.healthsync.model.Patient;
import com.healthsync.model.User;
import com.healthsync.util.PasswordUtil;
import com.healthsync.util.ValidationUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get all form fields
        String fullName    = request.getParameter("fullName");
        String email       = request.getParameter("email");
        String phone       = request.getParameter("phone");
        String password    = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPassword");
        String dob         = request.getParameter("dateOfBirth");
        String gender      = request.getParameter("gender");
        String address     = request.getParameter("address");
        String bloodGroup  = request.getParameter("bloodGroup");
        String emergency   = request.getParameter("emergencyContact");

        // Validations
        if (ValidationUtil.isEmpty(fullName) || ValidationUtil.isEmpty(email) ||
                ValidationUtil.isEmpty(phone) || ValidationUtil.isEmpty(password)) {
            request.setAttribute("error", "All required fields must be filled.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if (!ValidationUtil.isValidName(fullName)) {
            request.setAttribute("error",
                    "Full name must contain letters only, no numbers or symbols.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if (!ValidationUtil.isValidEmail(email)) {
            request.setAttribute("error", "Please enter a valid email address.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if (!ValidationUtil.isValidPhone(phone)) {
            request.setAttribute("error", "Phone number must be exactly 10 digits.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if (!ValidationUtil.isValidPassword(password)) {
            request.setAttribute("error", "Password must be at least 6 characters.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPass)) {
            request.setAttribute("error", "Passwords do not match.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // Check duplicates
        UserDAO userDAO = new UserDAO();

        if (userDAO.emailExists(email)) {
            request.setAttribute("error",
                    "An account with this email already exists.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if (userDAO.phoneExists(phone)) {
            request.setAttribute("error",
                    "An account with this phone number already exists.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // Create user
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(PasswordUtil.encrypt(password));
        user.setRole("patient");
        user.setStatus("pending"); // needs admin approval

        boolean userSaved = userDAO.save(user);

        if (!userSaved) {
            request.setAttribute("error",
                    "Registration failed. Please try again.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // Create patient record
        int userId = userDAO.getLastInsertedId();

        Patient patient = new Patient();
        patient.setUserId(userId);
        patient.setDateOfBirth(dob);
        patient.setGender(gender);
        patient.setAddress(address);
        patient.setBloodGroup(bloodGroup);
        patient.setEmergencyContact(emergency);

        PatientDAO patientDAO = new PatientDAO();
        patientDAO.save(patient);

        // Success
        request.setAttribute("success",
                "Registration successful! Please wait for admin approval before logging in.");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}