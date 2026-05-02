package com.healthsync.controller;

import com.healthsync.dao.AppointmentDAO;
import com.healthsync.dao.DoctorDAO;
import com.healthsync.dao.PatientDAO;
import com.healthsync.dao.UserDAO;
import com.healthsync.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AdminDashboardServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();
    private PatientDAO patientDAO = new PatientDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = request.getServletPath();

        if ("/admin/dashboard".equals(path)) {
            Map<String, Integer> counts = userDAO.getCounts();
            List<User> pendingUsers = userDAO.findAllByStatus("pending");
            
            request.setAttribute("counts", counts);
            request.setAttribute("pendingUsers", pendingUsers);
            request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);

        } else if ("/admin/manage-patients".equals(path)) {
            request.setAttribute("patients", patientDAO.findAll());
            request.getRequestDispatcher("/admin/manage-patients.jsp").forward(request, response);

        } else if ("/admin/manage-doctors".equals(path)) {
            request.setAttribute("doctors", doctorDAO.findAll());
            request.getRequestDispatcher("/admin/manage-doctors.jsp").forward(request, response);

        } else if ("/admin/manage-appointments".equals(path)) {
            request.setAttribute("appointments", appointmentDAO.findAll());
            request.getRequestDispatcher("/admin/manage-appointments.jsp").forward(request, response);

        } else if ("/admin/edit-patient".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("p", patientDAO.findById(id));
            request.getRequestDispatcher("/admin/edit-patient.jsp").forward(request, response);

        } else if ("/admin/edit-doctor".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("d", doctorDAO.findById(id));
            request.getRequestDispatcher("/admin/edit-doctor.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = request.getServletPath();

        if ("/admin/approve-user".equals(path)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String status = request.getParameter("status");
            
            if (userDAO.updateStatus(userId, status)) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard?success=User " + status);
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard?error=Action failed");
            }
        } else if ("/admin/update-patient".equals(path)) {
            int pId = Integer.parseInt(request.getParameter("patientId"));
            int uId = Integer.parseInt(request.getParameter("userId"));
            
            com.healthsync.model.Patient p = new com.healthsync.model.Patient();
            p.setPatientId(pId);
            p.setUserId(uId);
            p.setFullName(request.getParameter("fullName"));
            p.setEmail(request.getParameter("email"));
            p.setPhone(request.getParameter("phone"));
            p.setDateOfBirth(request.getParameter("dob"));
            p.setGender(request.getParameter("gender"));
            p.setAddress(request.getParameter("address"));
            p.setBloodGroup(request.getParameter("bloodGroup"));
            p.setEmergencyContact(request.getParameter("emergency"));

            userDAO.update(p); // update users table
            patientDAO.update(p); // update patients table
            
            response.sendRedirect(request.getContextPath() + "/admin/manage-patients?success=Updated");

        } else if ("/admin/update-doctor".equals(path)) {
            int dId = Integer.parseInt(request.getParameter("doctorId"));
            int uId = Integer.parseInt(request.getParameter("userId"));
            
            com.healthsync.model.Doctor d = new com.healthsync.model.Doctor();
            d.setDoctorId(dId);
            d.setUserId(uId);
            d.setFullName(request.getParameter("fullName"));
            d.setEmail(request.getParameter("email"));
            d.setPhone(request.getParameter("phone"));
            d.setSpecialization(request.getParameter("specialization"));
            d.setQualification(request.getParameter("qualification"));
            d.setExperienceYears(Integer.parseInt(request.getParameter("experience")));
            d.setAvailableDays(request.getParameter("availableDays"));

            userDAO.update(d); // update users table
            doctorDAO.update(d); // update doctors table
            
            response.sendRedirect(request.getContextPath() + "/admin/manage-doctors?success=Updated");
        }
    }
}
