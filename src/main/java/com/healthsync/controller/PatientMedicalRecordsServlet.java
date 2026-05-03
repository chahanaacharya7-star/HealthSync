package com.healthsync.controller;

import com.healthsync.dao.MedicalRecordDAO;
import com.healthsync.dao.PatientDAO;
import com.healthsync.model.MedicalRecord;
import com.healthsync.model.Patient;
import com.healthsync.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class PatientMedicalRecordsServlet extends HttpServlet {
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
    private PatientDAO patientDAO = new PatientDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        Patient patient = patientDAO.findByUserId(user.getUserId());

        if (patient != null) {
            List<MedicalRecord> records = medicalRecordDAO.findByPatientId(patient.getPatientId());
            request.setAttribute("records", records);
            request.setAttribute("patient", patient);
            request.getRequestDispatcher("/patient/medical-records.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
