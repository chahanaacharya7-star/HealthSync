<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.healthsync.model.MedicalRecord" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Medical Records - HealthSync</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<div class="dashboard-container">
    <h2 class="dashboard-title">My Medical Records</h2>
    <p class="dashboard-subtitle">Your complete medical history</p>

    <%
        List<MedicalRecord> records =
                (List<MedicalRecord>) request.getAttribute("records");
        if (records == null || records.isEmpty()) {
    %>
    <div class="empty-state">
        <div class="empty-icon">📋</div>
        <h3>No Medical Records Found</h3>
        <p>Your medical records will appear here after doctor visits.</p>
    </div>
    <%
    } else {
        for (MedicalRecord r : records) {
    %>
    <div class="record-card">
        <div class="record-header">
            <div>
                <h3>Dr. <%= r.getDoctorName() %></h3>
                <span class="record-spec">
                        <%= r.getDoctorSpecialization() %>
                    </span>
            </div>
            <div class="record-date">
                📅 <%= r.getVisitDate() %>
            </div>
        </div>
        <div class="record-body">
            <div class="record-section">
                <h4>Diagnosis</h4>
                <p><%= r.getDiagnosis() %></p>
            </div>
            <% if (r.getPrescription() != null &&
                    !r.getPrescription().isEmpty()) { %>
            <div class="record-section">
                <h4>Prescription</h4>
                <p><%= r.getPrescription() %></p>
            </div>
            <% } %>
            <% if (r.getFollowUpDate() != null &&
                    !r.getFollowUpDate().isEmpty()) { %>
            <div class="record-section">
                <h4>Follow-up Date</h4>
                <p><%= r.getFollowUpDate() %></p>
            </div>
            <% } %>
        </div>
    </div>
    <%
            }
        }
    %>
</div>

<%@ include file="/includes/footer.jsp" %>

</body>
</html>