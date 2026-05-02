package com.healthsync.controller;

import com.healthsync.dao.DoctorDAO;
import com.healthsync.model.Doctor;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class DoctorServlet extends HttpServlet {

    private DoctorDAO doctorDAO = new DoctorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            List<Doctor> doctors = doctorDAO.findAll();
            request.setAttribute("doctors", doctors);
            response.getWriter().println("Listing all doctors");
        } else {
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                Doctor doctor = doctorDAO.findById(id);
                if (doctor != null) {
                    response.getWriter().println("Doctor found: " + doctor.getFullName());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().println("Doctor not found");
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid doctor ID");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Doctor doctor = new Doctor();
        doctor.setUserId(Integer.parseInt(request.getParameter("userId")));
        doctor.setSpecialization(request.getParameter("specialization"));
        doctor.setQualification(request.getParameter("qualification"));
        doctor.setExperienceYears(Integer.parseInt(request.getParameter("experienceYears")));
        doctor.setAvailableDays(request.getParameter("availableDays"));

        if (doctorDAO.save(doctor)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().println("Doctor created successfully");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Failed to create doctor");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            int id = Integer.parseInt(pathInfo.substring(1));
            Doctor doctor = doctorDAO.findById(id);
            if (doctor != null) {
                doctor.setSpecialization(request.getParameter("specialization"));
                doctor.setQualification(request.getParameter("qualification"));
                doctor.setExperienceYears(Integer.parseInt(request.getParameter("experienceYears")));
                doctor.setAvailableDays(request.getParameter("availableDays"));

                if (doctorDAO.update(doctor)) {
                    response.getWriter().println("Doctor updated successfully");
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().println("Failed to update doctor");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            int id = Integer.parseInt(pathInfo.substring(1));
            if (doctorDAO.delete(id)) {
                response.getWriter().println("Doctor deleted successfully");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().println("Doctor not found or delete failed");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
