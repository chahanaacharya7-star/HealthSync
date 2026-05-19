package com.healthsync.controller;

import com.healthsync.dao.AppointmentDAO;
import com.healthsync.dao.DoctorDAO;
import com.healthsync.dao.PatientDAO;
import com.healthsync.model.Appointment;
import com.healthsync.model.Doctor;
import com.healthsync.model.Patient;
import com.healthsync.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/patient/book-appointment")
public class BookAppointmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Load all approved doctors for dropdown
        DoctorDAO doctorDAO = new DoctorDAO();
        List<Doctor> doctors = doctorDAO.findAll();
        request.setAttribute("doctors", doctors);

        request.getRequestDispatcher("/patient/book-appointment.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String doctorIdStr       = request.getParameter("doctorId");
        String appointmentDate   = request.getParameter("appointmentDate");
        String appointmentTime   = request.getParameter("appointmentTime");
        String reason            = request.getParameter("reason");

        // Validation
        if (doctorIdStr == null || appointmentDate.isEmpty()
                || appointmentTime.isEmpty()) {
            request.setAttribute("error",
                    "Please fill all required fields.");
            DoctorDAO doctorDAO = new DoctorDAO();
            request.setAttribute("doctors", doctorDAO.findAll());
            request.getRequestDispatcher("/patient/book-appointment.jsp")
                    .forward(request, response);
            return;
        }

        // Get logged in patient
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        PatientDAO patientDAO = new PatientDAO();
        Patient patient = patientDAO.findByUserId(user.getUserId());

        if (patient == null) {
            request.setAttribute("error", "Patient profile not found.");
            request.getRequestDispatcher("/patient/book-appointment.jsp")
                    .forward(request, response);
            return;
        }

        // Save appointment
        Appointment appointment = new Appointment();
        appointment.setPatientId(patient.getPatientId());
        appointment.setDoctorId(Integer.parseInt(doctorIdStr));
        appointment.setAppointmentDate(appointmentDate);
        appointment.setAppointmentTime(appointmentTime);
        appointment.setReason(reason);
        appointment.setStatus("pending");

        AppointmentDAO appointmentDAO = new AppointmentDAO();
        boolean saved = appointmentDAO.save(appointment);

        if (saved) {
            response.sendRedirect(request.getContextPath() +
                    "/patient/my-appointments?success=Appointment+booked+successfully");
        } else {
            request.setAttribute("error",
                    "Failed to book appointment. Please try again.");
            request.setAttribute("doctors", new DoctorDAO().findAll());
            request.getRequestDispatcher("/patient/book-appointment.jsp")
                    .forward(request, response);
        }
    }
}