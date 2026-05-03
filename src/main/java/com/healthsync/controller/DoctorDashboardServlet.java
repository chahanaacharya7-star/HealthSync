package com.healthsync.controller;

import com.healthsync.dao.AppointmentDAO;
import com.healthsync.dao.DoctorDAO;
import com.healthsync.dao.PatientDAO;
import com.healthsync.model.Appointment;
import com.healthsync.model.Doctor;
import com.healthsync.model.Patient;
import com.healthsync.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorDashboardServlet extends HttpServlet {
    private DoctorDAO doctorDAO = new DoctorDAO();
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    private PatientDAO patientDAO = new PatientDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDAO.findByUserId(user.getUserId());

        if (doctor == null) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        request.setAttribute("doctor", doctor);
        String path = request.getServletPath();

        if ("/doctor/dashboard".equals(path)) {
            List<Appointment> allAppointments = appointmentDAO.findByDoctorId(doctor.getDoctorId());
            long pendingCount = allAppointments.stream().filter(a -> "pending".equals(a.getStatus())).count();
            long confirmedCount = allAppointments.stream().filter(a -> "confirmed".equals(a.getStatus())).count();

            List<Appointment> upcoming = allAppointments.stream()
                    .filter(a -> !"cancelled".equals(a.getStatus()) && !"completed".equals(a.getStatus()))
                    .limit(5)
                    .collect(Collectors.toList());

            request.setAttribute("pendingCount", pendingCount);
            request.setAttribute("confirmedCount", confirmedCount);
            request.setAttribute("upcomingAppointments", upcoming);
            request.getRequestDispatcher("/doctor/dashboard.jsp").forward(request, response);

        } else if ("/doctor/appointments".equals(path)) {
            List<Appointment> appointments = appointmentDAO.findByDoctorId(doctor.getDoctorId());
            request.setAttribute("appointments", appointments);
            request.getRequestDispatcher("/doctor/appointments.jsp").forward(request, response);
            
        } else if ("/doctor/patients".equals(path)) {
            // Find unique patients linked to doctor's appointments
            List<Appointment> appointments = appointmentDAO.findByDoctorId(doctor.getDoctorId());
            List<Integer> patientIds = appointments.stream()
                    .map(Appointment::getPatientId)
                    .distinct()
                    .collect(Collectors.toList());
            
            List<Patient> patients = new ArrayList<>();
            for (Integer pid : patientIds) {
                Patient p = patientDAO.findById(pid);
                if (p != null) patients.add(p);
            }
            request.setAttribute("patients", patients);
            request.getRequestDispatcher("/doctor/patients.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
