<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Appointments | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="dashboard-container">
        <div class="dashboard-header">
            <h1 class="dashboard-title">My Appointments</h1>
            <p class="dashboard-subtitle">Manage and track all your scheduled consultations.</p>
        </div>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Doctor</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Reason</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="app" items="${appointments}">
                        <tr>
                            <td>#${app.appointmentId}</td>
                            <td>
                                <div style="font-weight: 600;">Dr. ${app.doctorName}</div>
                            </td>
                            <td>${app.appointmentDate}</td>
                            <td>${app.appointmentTime}</td>
                            <td>${app.reason}</td>
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
                                <c:if test="${app.status == 'pending' || app.status == 'confirmed'}">
                                    <button onclick="cancelAppointment(${app.appointmentId})" class="btn btn-danger btn-sm">Cancel</button>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty appointments}">
                        <tr>
                            <td colspan="7" style="text-align: center; padding: 40px; color: #777;">
                                No appointments found. <a href="${pageContext.request.contextPath}/patient/book-appointment" style="color: #1a73e8; font-weight: 600;">Book one now!</a>
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </main>

    <jsp:include page="/includes/footer.jsp" />

    <script>
        function cancelAppointment(id) {
            if (confirm('Are you sure you want to cancel this appointment?')) {
                fetch('${pageContext.request.contextPath}/appointments/' + id + '/cancel', {
                    method: 'PATCH'
                })
                .then(response => {
                    if (response.ok) {
                        location.reload();
                    } else {
                        alert('Failed to cancel appointment. Please try again.');
                    }
                });
            }
        }
    </script>

</body>
</html>
