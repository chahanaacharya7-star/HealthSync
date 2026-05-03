<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Appointments | Doctor | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="dashboard-container">
        <div class="dashboard-header">
            <h1 class="dashboard-title">Manage Appointments</h1>
            <p class="dashboard-subtitle">Review new requests and confirm or cancel appointments.</p>
        </div>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Date & Time</th>
                        <th>Patient Name</th>
                        <th>Reason</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="app" items="${appointments}">
                        <tr>
                            <td>
                                <div style="font-weight: 600;">${app.appointmentDate}</div>
                                <div style="font-size: 12px; color: #666;">${app.appointmentTime}</div>
                            </td>
                            <td style="font-weight: 600;">${app.patientName}</td>
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
                                <c:if test="${app.status == 'pending'}">
                                    <button onclick="updateStatus(${app.appointmentId}, 'confirm')" class="btn btn-sm" style="background: #10b981; color: white; border: none;">Confirm</button>
                                    <button onclick="updateStatus(${app.appointmentId}, 'cancel')" class="btn btn-sm" style="background: #ef4444; color: white; border: none; margin-left: 5px;">Reject</button>
                                </c:if>
                                <c:if test="${app.status == 'confirmed'}">
                                    <button onclick="updateStatus(${app.appointmentId}, 'complete')" class="btn btn-sm" style="background: #3b82f6; color: white; border: none;">Mark Completed</button>
                                    <button onclick="updateStatus(${app.appointmentId}, 'cancel')" class="btn btn-sm" style="background: #ef4444; color: white; border: none; margin-left: 5px;">Cancel</button>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty appointments}">
                        <tr>
                            <td colspan="5" style="text-align: center; padding: 40px; color: #777;">
                                You don't have any appointments assigned yet.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </main>

    <jsp:include page="/includes/footer.jsp" />

    <script>
        function updateStatus(id, action) {
            if (confirm('Are you sure you want to ' + action + ' this appointment?')) {
                fetch('${pageContext.request.contextPath}/appointments/' + id + '/' + action, {
                    method: 'PATCH'
                })
                .then(response => {
                    if (response.ok) {
                        location.reload();
                    } else {
                        alert('Failed to update appointment. Please try again.');
                    }
                });
            }
        }
    </script>

</body>
</html>
