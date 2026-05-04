package com.healthsync.controller;

import com.healthsync.dao.MedicalRecordDAO;
import com.healthsync.dao.PatientDAO;
import com.healthsync.dao.DoctorDAO;
import com.healthsync.model.MedicalRecord;
import com.healthsync.model.Patient;
import com.healthsync.model.Doctor;
import com.healthsync.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class PatientRecordsServlet extends HttpServlet {
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
    private PatientDAO patientDAO = new PatientDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String patientIdParam = request.getParameter("patientId");
        if (patientIdParam != null) {
            try {
                int patientId = Integer.parseInt(patientIdParam);
                Patient patient = patientDAO.findById(patientId);
                List<MedicalRecord> records = medicalRecordDAO.findByPatientId(patientId);
                
                request.setAttribute("patient", patient);
                request.setAttribute("records", records);
                request.getRequestDispatcher("/doctor/patient-records.jsp").forward(request, response);
                return;
            } catch (NumberFormatException e) {
                // Ignore
            }
        }
        response.sendRedirect(request.getContextPath() + "/doctor/patients");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDAO.findByUserId(user.getUserId());
        
        if (doctor == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        int patientId = Integer.parseInt(request.getParameter("patientId"));
        String diagnosis = request.getParameter("diagnosis");
        String prescription = request.getParameter("prescription");
        String visitDate = request.getParameter("visitDate");
        String followUpDate = request.getParameter("followUpDate");

        MedicalRecord record = new MedicalRecord();
        record.setPatientId(patientId);
        record.setDoctorId(doctor.getDoctorId());
        record.setDiagnosis(diagnosis);
        record.setPrescription(prescription);
        record.setVisitDate(visitDate);
        record.setFollowUpDate(followUpDate);

        if (medicalRecordDAO.save(record)) {
            response.sendRedirect(request.getContextPath() + "/doctor/patient-records?patientId=" + patientId + "&success=true");
        } else {
            response.sendRedirect(request.getContextPath() + "/doctor/patient-records?patientId=" + patientId + "&error=true");
        }
    }
}
