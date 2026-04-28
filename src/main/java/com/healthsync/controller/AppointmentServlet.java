package com.healthsync.controller;

import com.healthsync.dao.AppointmentDAO;
import com.healthsync.model.Appointment;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AppointmentServlet extends HttpServlet {

    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("PATCH")) {
            doPatch(req, resp);
        } else {
            super.service(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            List<Appointment> list = appointmentDAO.findAll();
            response.getWriter().println("Listing all appointments");
        } else {
            String[] parts = pathInfo.split("/");
            
            if (parts.length == 2) {
                try {
                    int id = Integer.parseInt(parts[1]);
                    Appointment app = appointmentDAO.findById(id);
                    if (app != null) {
                        response.getWriter().println("Appointment found for patient: " + app.getPatientName());
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    }
                } catch (NumberFormatException e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else if (parts.length == 3) {
                String type = parts[1];
                String value = parts[2];

                if ("patient".equals(type)) {
                    List<Appointment> list = appointmentDAO.findByPatientId(Integer.parseInt(value));
                    response.getWriter().println("Listing appointments for patient " + value);
                } else if ("doctor".equals(type)) {
                    List<Appointment> list = appointmentDAO.findByDoctorId(Integer.parseInt(value));
                    response.getWriter().println("Listing appointments for doctor " + value);
                } else if ("date".equals(type)) {
                    List<Appointment> list = appointmentDAO.findByDate(value);
                    response.getWriter().println("Listing appointments for date " + value);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Appointment app = new Appointment();
        app.setPatientId(Integer.parseInt(request.getParameter("patientId")));
        app.setDoctorId(Integer.parseInt(request.getParameter("doctorId")));
        app.setAppointmentDate(request.getParameter("date"));
        app.setAppointmentTime(request.getParameter("time"));
        app.setReason(request.getParameter("reason"));
        app.setNotes(request.getParameter("notes"));
        app.setStatus("pending");

        if (appointmentDAO.save(app)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().println("Appointment created successfully");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
            Appointment app = appointmentDAO.findById(id);
            if (app != null) {
                app.setAppointmentDate(request.getParameter("date"));
                app.setAppointmentTime(request.getParameter("time"));
                app.setReason(request.getParameter("reason"));
                app.setNotes(request.getParameter("notes"));
                app.setStatus(request.getParameter("status"));

                if (appointmentDAO.update(app)) {
                    response.getWriter().println("Appointment updated successfully");
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
            if (appointmentDAO.delete(id)) {
                response.getWriter().println("Appointment deleted successfully");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected void doPatch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] parts = pathInfo.split("/");
        if (parts.length == 3) {
            try {
                int id = Integer.parseInt(parts[1]);
                String action = parts[2];
                String status = "";

                if ("confirm".equals(action)) status = "confirmed";
                else if ("cancel".equals(action)) status = "cancelled";
                else if ("complete".equals(action)) status = "completed";

                if (!status.isEmpty()) {
                    if (appointmentDAO.updateStatus(id, status)) {
                        response.getWriter().println("Appointment " + status + " successfully");
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
