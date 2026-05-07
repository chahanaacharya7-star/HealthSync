<%@ page language="java" contentType="text/html; charset=UTF-8" %>
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

    <!-- Stats Cards -->
    <div class="stats-grid">
        <div class="stat-card">
            <div class="stat-icon">📅</div>
            <h3>${totalAppointments}</h3>
            <p>My Appointments</p>
        </div>
        <div class="stat-card">
            <div class="stat-icon">📋</div>
            <h3>${totalRecords != null ? totalRecords : 0}</h3>
            <p>Medical Records</p>
        </div>
        <div class="stat-card">
            <div class="stat-icon">✅</div>
            <h3>Active</h3>
            <p>Account Status</p>
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