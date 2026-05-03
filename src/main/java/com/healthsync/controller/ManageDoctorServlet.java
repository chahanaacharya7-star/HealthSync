package com.healthsync.controller;

import com.healthsync.dao.DoctorDAO;
import com.healthsync.dao.UserDAO;
import com.healthsync.model.Doctor;
import com.healthsync.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ManageDoctorServlet extends HttpServlet {

    private DoctorDAO doctorDAO = new DoctorDAO();
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "add":
                    showAddForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteDoctor(request, response);
                    break;
                default:
                    listDoctors(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "insert":
                    insertDoctor(request, response);
                    break;
                case "update":
                    updateDoctor(request, response);
                    break;
                default:
                    response.sendRedirect("manage-doctors");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listDoctors(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Doctor> doctors = doctorDAO.findAll();
        request.setAttribute("doctors", doctors);
        request.getRequestDispatcher("/admin/manage-doctors.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/admin/add-doctor.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Doctor existingDoctor = doctorDAO.findById(id);
        request.setAttribute("doctor", existingDoctor);
        request.getRequestDispatcher("/admin/edit-doctor.jsp").forward(request, response);
    }

    private void insertDoctor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String specialization = request.getParameter("specialization");
        String qualification = request.getParameter("qualification");
        int experience = Integer.parseInt(request.getParameter("experienceYears"));
        String availableDays = request.getParameter("availableDays");

        // Create User first
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setRole("DOCTOR");
        user.setStatus("approved");

        if (userDAO.save(user)) {
            int userId = userDAO.getLastInsertedId();
            Doctor doctor = new Doctor();
            doctor.setUserId(userId);
            doctor.setSpecialization(specialization);
            doctor.setQualification(qualification);
            doctor.setExperienceYears(experience);
            doctor.setAvailableDays(availableDays);
            
            if (doctorDAO.save(doctor)) {
                response.sendRedirect("manage-doctors?success=Doctor added");
            } else {
                response.sendRedirect("manage-doctors?error=Failed to add doctor profile");
            }
        } else {
            response.sendRedirect("manage-doctors?error=Failed to create user");
        }
    }

    private void updateDoctor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        
        Doctor d = new Doctor();
        d.setDoctorId(doctorId);
        d.setUserId(userId);
        d.setFullName(request.getParameter("fullName"));
        d.setEmail(request.getParameter("email"));
        d.setPhone(request.getParameter("phone"));
        d.setSpecialization(request.getParameter("specialization"));
        d.setQualification(request.getParameter("qualification"));
        d.setExperienceYears(Integer.parseInt(request.getParameter("experienceYears")));
        d.setAvailableDays(request.getParameter("availableDays"));

        userDAO.update(d);
        doctorDAO.update(d);
        
        response.sendRedirect("manage-doctors?success=Doctor updated");
    }

    private void deleteDoctor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        doctorDAO.delete(id);
        response.sendRedirect("manage-doctors?success=Doctor deleted");
    }
}
