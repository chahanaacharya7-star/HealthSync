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
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }

            User user = (User) session.getAttribute("user");
            Patient patient = patientDAO.findByUserId(user.getUserId());

            if (patient == null) {
                // Auto-create missing patient profile for seeded users
                Patient newPatient = new Patient();
                newPatient.setUserId(user.getUserId());
                newPatient.setDateOfBirth("2000-01-01");
                newPatient.setGender("Other");
                newPatient.setAddress("Not provided");
                newPatient.setBloodGroup("O+");
                newPatient.setEmergencyContact("0000000000");
                patientDAO.save(newPatient);

                patient = patientDAO.findByUserId(user.getUserId());
                if (patient == null) {
                    response.sendRedirect(request.getContextPath() + "/error.jsp");
                    return;
                }
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
                // Handle cancellation
                String action = request.getParameter("action");
                if ("cancel".equals(action)) {
                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        try {
                            int appointmentId = Integer.parseInt(idStr.trim());
                            Appointment app = appointmentDAO.findById(appointmentId);
                            if (app != null && app.getPatientId() == patient.getPatientId()) {
                                appointmentDAO.updateStatus(appointmentId, "cancelled");
                                response.sendRedirect(request.getContextPath() + "/patient/my-appointments?success=Appointment+cancelled+successfully");
                                return;
                            }
                        } catch (NumberFormatException e) {
                            // ignore and continue to list
                        }
                    }
                }

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
        } catch (Exception e) {
            response.setContentType("text/plain");
            response.getWriter().println("Exception occurred:");
            e.printStackTrace(response.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
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

            String path = request.getServletPath();

            if ("/patient/book-appointment".equals(path)) {
                String doctorIdStr = request.getParameter("doctorId");
                String appointmentDate = request.getParameter("appointmentDate");
                String appointmentTime = request.getParameter("appointmentTime");
                String reason = request.getParameter("reason");

                if (doctorIdStr != null && !doctorIdStr.isEmpty() && appointmentDate != null && appointmentTime != null) {
                    Appointment app = new Appointment();
                    app.setPatientId(patient.getPatientId());
                    app.setDoctorId(Integer.parseInt(doctorIdStr));
                    app.setAppointmentDate(appointmentDate);
                    app.setAppointmentTime(appointmentTime);
                    app.setReason(reason != null ? reason : "");
                    app.setStatus("pending");

                    if (appointmentDAO.save(app)) {
                        response.sendRedirect(request.getContextPath() + "/patient/dashboard?success=Appointment+Booked+Successfully");
                        return;
                    }
                }
                
                request.setAttribute("error", "Failed to book appointment. Please make sure all required fields are filled.");
                request.setAttribute("doctors", doctorDAO.findAll());
                request.getRequestDispatcher("/patient/book-appointment.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            }
        } catch (Exception e) {
            response.setContentType("text/plain");
            response.getWriter().println("Exception occurred in POST:");
            e.printStackTrace(response.getWriter());
        }
    }
}
