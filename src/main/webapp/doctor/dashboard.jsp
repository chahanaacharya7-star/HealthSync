<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctor Dashboard | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="dashboard-container">
        <div class="dashboard-header">
            <h1 class="dashboard-title">Welcome Dr. ${doctor.fullName}!</h1>
            <p class="dashboard-subtitle">Manage your appointments, patients, and schedule.</p>
        </div>

        <!-- Quick Stats -->
        <div class="stats-grid">
            <div class="stat-card" style="border-top-color: #f59e0b;">
                <h3 style="color: #f59e0b;">${pendingCount}</h3>
                <p>Pending Requests</p>
            </div>
            <div class="stat-card" style="border-top-color: #10b981;">
                <h3 style="color: #10b981;">${confirmedCount}</h3>
                <p>Upcoming Appointments</p>
            </div>
            <div class="stat-card" style="border-top-color: #3b82f6;">
                <h3 style="color: #3b82f6;">${doctor.experienceYears}</h3>
                <p>Years Experience</p>
            </div>
        </div>

        <div class="dashboard-content">
            <!-- Upcoming Appointments -->
            <div class="table-container">
                <div class="table-header">
                    <h3>Recent & Upcoming Appointments</h3>
                    <a href="${pageContext.request.contextPath}/doctor/appointments" class="btn btn-outline btn-sm">Manage Schedule</a>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>Patient</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="app" items="${upcomingAppointments}">
                            <tr>
                                <td>
                                    <div style="font-weight: 600;">${app.patientName}</div>
                                </td>
                                <td>${app.appointmentDate}</td>
                                <td>${app.appointmentTime}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${app.status == 'pending'}">
                                            <span class="badge badge-warning">Pending</span>
                                        </c:when>
                                        <c:when test="${app.status == 'confirmed'}">
                                            <span class="badge badge-info">Confirmed</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-secondary">${app.status}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty upcomingAppointments}">
                            <tr>
                                <td colspan="4" style="text-align: center; padding: 30px; color: #777;">
                                    No immediate appointments to display.
                                </td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>

            <!-- Quick Actions -->
            <div style="margin-top: 30px;">
                <h3 style="margin-bottom: 20px; font-size: 18px;">Quick Actions</h3>
                <div class="hero-buttons" style="margin-top: 0;">
                    <a href="${pageContext.request.contextPath}/doctor/patients" class="btn btn-primary">
                        🧑‍🤝‍🧑 View Your Patients
                    </a>
                    <a href="${pageContext.request.contextPath}/doctor/profile" class="btn btn-outline">
                        ⚙️ Update Availability
                    </a>
                </div>
            </div>
        </div>
    </main>

    <jsp:include page="/includes/footer.jsp" />

</body>
</html>
