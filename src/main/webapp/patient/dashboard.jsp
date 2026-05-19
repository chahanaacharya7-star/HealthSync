<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.healthsync.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patient Dashboard - HealthSync</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<div class="dashboard-container">

    <div class="dashboard-welcome">
        <h2 class="dashboard-title">
            Welcome, <%= session.getAttribute("userName") %> 👋
        </h2>
        <p class="dashboard-subtitle">
            Manage your health appointments and records here.
        </p>
    </div>

    <!-- Success/Error Messages -->
    <% if (request.getParameter("success") != null) { %>
    <div class="alert alert-success">
        ✔ <%= request.getParameter("success").replace("+", " ") %>
    </div>
    <% } %>

    <!-- Quick Stats -->
    <div class="stats-grid">
        <div class="stat-card" style="border-top-color: #3b82f6;">
            <h3 style="color: #3b82f6;">${totalAppointments}</h3>
            <p>Total Appointments</p>
        </div>
        <div class="stat-card" style="border-top-color: #f59e0b;">
            <h3 style="color: #f59e0b;">${pendingCount}</h3>
            <p>Pending Requests</p>
        </div>
        <div class="stat-card" style="border-top-color: #10b981;">
            <h3 style="color: #10b981;">${confirmedCount}</h3>
            <p>Confirmed Appointments</p>
        </div>
    </div>

    <!-- Quick Actions -->
    <div class="quick-actions">
        <h3>Quick Actions</h3>
        <div class="action-grid">
            <a href="${pageContext.request.contextPath}/patient/book-appointment"
               class="action-card">
                <div class="action-icon">📅</div>
                <h4>Book Appointment</h4>
                <p>Schedule a new appointment with a doctor</p>
            </a>
            <a href="${pageContext.request.contextPath}/patient/my-appointments"
               class="action-card">
                <div class="action-icon">📋</div>
                <h4>My Appointments</h4>
                <p>View and manage your appointments</p>
            </a>
            <a href="${pageContext.request.contextPath}/patient/medical-records"
               class="action-card">
                <div class="action-icon">🏥</div>
                <h4>Medical Records</h4>
                <p>View your medical history and records</p>
            </a>
            <a href="${pageContext.request.contextPath}/patient/profile"
               class="action-card">
                <div class="action-icon">👤</div>
                <h4>My Profile</h4>
                <p>Update your personal information</p>
            </a>
        </div>
    </div>

</div>

<%@ include file="/includes/footer.jsp" %>

</body>
</html>