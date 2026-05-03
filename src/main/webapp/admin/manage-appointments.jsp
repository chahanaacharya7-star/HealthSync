<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Appointments | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="dashboard-container">
        <div class="dashboard-header">
            <h1 class="dashboard-title">System Appointments</h1>
            <p class="dashboard-subtitle">Monitor all consultations across the platform.</p>
        </div>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Patient</th>
                        <th>Doctor</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="app" items="${appointments}">
                        <tr>
                            <td>#${app.appointmentId}</td>
                            <td>${app.patientName}</td>
                            <td>Dr. ${app.doctorName}</td>
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
                                    <c:when test="${app.status == 'completed'}">
                                        <span class="badge badge-success">Completed</span>
                                    </c:when>
                                    <c:when test="${app.status == 'cancelled'}">
                                        <span class="badge badge-danger">Cancelled</span>
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <button onclick="deleteAppointment(${app.appointmentId})" class="btn btn-danger btn-sm">Remove</button>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty appointments}">
                        <tr>
                            <td colspan="7" style="text-align: center; padding: 40px; color: #777;">
                                No appointments found in the system.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </main>

    <jsp:include page="/includes/footer.jsp" />

    <script>
        function deleteAppointment(id) {
            if (confirm('Are you sure you want to remove this appointment?')) {
                fetch('${pageContext.request.contextPath}/appointments/' + id, {
                    method: 'DELETE'
                }).then(res => {
                    if (res.ok) location.reload();
                    else alert('Action failed');
                });
            }
        }
    </script>

</body>
</html>
