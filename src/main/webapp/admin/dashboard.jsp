<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="dashboard-container">
        <div class="dashboard-header">
            <h1 class="dashboard-title">System Overview</h1>
            <p class="dashboard-subtitle">Manage system users, appointments, and registrations.</p>
        </div>

        <c:if test="${not empty param.success}">
            <div class="alert alert-success">${param.success}</div>
        </c:if>

        <!-- System Stats -->
        <div class="stats-grid">
            <div class="stat-card">
                <h3>${counts.patients}</h3>
                <p>Total Patients</p>
            </div>
            <div class="stat-card">
                <h3>${counts.doctors}</h3>
                <p>Total Doctors</p>
            </div>
            <div class="stat-card">
                <h3>${counts.appointments}</h3>
                <p>Appointments</p>
            </div>
            <div class="stat-card" style="border-top-color: #f59e0b;">
                <h3 style="color: #f59e0b;">${pendingUsers.size()}</h3>
                <p>Pending Approvals</p>
            </div>
        </div>

        <!-- Pending Approvals -->
        <div class="table-container">
            <div class="table-header">
                <h3>Pending Registrations</h3>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Registered On</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${pendingUsers}">
                        <tr>
                            <td>${user.fullName}</td>
                            <td>${user.email}</td>
                            <td>
                                <span class="badge ${user.role == 'doctor' ? 'badge-info' : 'badge-warning'}">
                                    ${user.role}
                                </span>
                            </td>
                            <td>${user.createdAt}</td>
                            <td style="display: flex; gap: 8px;">
                                <form action="${pageContext.request.contextPath}/admin/approve-user" method="POST">
                                    <input type="hidden" name="userId" value="${user.userId}">
                                    <input type="hidden" name="status" value="approved">
                                    <button type="submit" class="btn btn-success btn-sm">Approve</button>
                                </form>
                                <form action="${pageContext.request.contextPath}/admin/approve-user" method="POST">
                                    <input type="hidden" name="userId" value="${user.userId}">
                                    <input type="hidden" name="status" value="rejected">
                                    <button type="submit" class="btn btn-danger btn-sm">Reject</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty pendingUsers}">
                        <tr>
                            <td colspan="5" style="text-align: center; padding: 30px; color: #777;">
                                No pending registrations found.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </main>

    <jsp:include page="/includes/footer.jsp" />

</body>
</html>
