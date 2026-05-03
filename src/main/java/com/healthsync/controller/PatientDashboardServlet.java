package com.healthsync.controller;

import com.healthsync.dao.AppointmentDAO;
import com.healthsync.dao.DoctorDAO;
import com.healthsync.dao.PatientDAO;
import com.healthsync.model.Appointment;
import com.healthsync.model.Doctor;
import com.healthsync.model.Patient;
import com.healthsync.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PatientDashboardServlet extends HttpServlet {

    private PatientDAO patientDAO = new PatientDAO();
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        Patient patient = patientDAO.findByUserId(user.getUserId());
        
        if (patient == null) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        // Set patient for all sub-pages
        request.setAttribute("patient", patient);

        String path = request.getServletPath();

        if ("/patient/dashboard".equals(path)) {
            // Dashboard data
            List<Appointment> allAppointments = appointmentDAO.findByPatientId(patient.getPatientId());
            
            long pendingCount = allAppointments.stream().filter(a -> "pending".equals(a.getStatus())).count();
            long confirmedCount = allAppointments.stream().filter(a -> "confirmed".equals(a.getStatus())).count();
            
            List<Appointment> upcoming = allAppointments.stream()
                    .filter(a -> !"cancelled".equals(a.getStatus()) && !"completed".equals(a.getStatus()))
                    .limit(5)
                    .collect(Collectors.toList());

            request.setAttribute("totalAppointments", allAppointments.size());
            request.setAttribute("pendingCount", pendingCount);
            request.setAttribute("confirmedCount", confirmedCount);
            request.setAttribute("upcomingAppointments", upcoming);

            request.getRequestDispatcher("/patient/dashboard.jsp").forward(request, response);

        } else if ("/patient/my-appointments".equals(path)) {
            // All appointments
            List<Appointment> appointments = appointmentDAO.findByPatientId(patient.getPatientId());
            request.setAttribute("appointments", appointments);
            request.getRequestDispatcher("/patient/my-appointments.jsp").forward(request, response);

        } else if ("/patient/book-appointment".equals(path)) {
            // Booking form data
            List<Doctor> doctors = doctorDAO.findAll();
            request.setAttribute("doctors", doctors);
            request.getRequestDispatcher("/patient/book-appointment.jsp").forward(request, response);
        }
    }
}
