package com.healthsync.controller;

import com.healthsync.dao.PatientDAO;
import com.healthsync.model.Patient;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class PatientServlet extends HttpServlet {

    private PatientDAO patientDAO = new PatientDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            
            List<Patient> patients = patientDAO.findAll();
            request.setAttribute("patients", patients);

            response.getWriter().println("Listing all patients");
        } else {
    
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                Patient patient = patientDAO.findById(id);
                if (patient != null) {
                    response.getWriter().println("Patient found: " + patient.getFullName());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().println("Patient not found");
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid patient ID");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Patient patient = new Patient();

        patient.setUserId(Integer.parseInt(request.getParameter("userId")));
        patient.setDateOfBirth(request.getParameter("dob"));
        patient.setGender(request.getParameter("gender"));
        patient.setAddress(request.getParameter("address"));
        patient.setBloodGroup(request.getParameter("bloodGroup"));
        patient.setEmergencyContact(request.getParameter("emergencyContact"));

        if (patientDAO.save(patient)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().println("Patient created successfully");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Failed to create patient");
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
            Patient patient = patientDAO.findById(id);
            if (patient != null) {
                patient.setDateOfBirth(request.getParameter("dob"));
                patient.setGender(request.getParameter("gender"));
                patient.setAddress(request.getParameter("address"));
                patient.setBloodGroup(request.getParameter("bloodGroup"));
                patient.setEmergencyContact(request.getParameter("emergencyContact"));

                if (patientDAO.update(patient)) {
                    response.getWriter().println("Patient updated successfully");
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().println("Failed to update patient");
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
            if (patientDAO.delete(id)) {
                response.getWriter().println("Patient deleted successfully");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().println("Patient not found or delete failed");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
