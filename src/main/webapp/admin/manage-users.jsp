<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Users | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="dashboard-container">
        <div class="table-header" style="margin-bottom: 30px;">
            <div>
                <h1 class="dashboard-title">Manage Users</h1>
                <p class="dashboard-subtitle">View and manage all system users (Admins, Doctors, Patients).</p>
            </div>
            <a href="${pageContext.request.contextPath}/admin/manage-users?action=add" class="btn btn-primary">Add New User</a>
        </div>

        <c:if test="${not empty param.success}">
            <div class="alert alert-success">${param.success}</div>
        </c:if>
        <c:if test="${not empty param.error}">
            <div class="alert alert-error">${param.error}</div>
        </c:if>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Registered On</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="u" items="${users}">
                        <tr>
                            <td>#${u.userId}</td>
                            <td>${u.fullName}</td>
                            <td>${u.email}</td>
                            <td><span style="text-transform: capitalize;">${u.role}</span></td>
                            <td>
                                <span class="badge ${u.status == 'approved' ? 'badge-success' : (u.status == 'pending' ? 'badge-warning' : 'badge-danger')}">
                                    ${u.status}
                                </span>
                            </td>
                            <td>${u.createdAt}</td>
                            <td>
                                <div style="display: flex; gap: 8px;">
                                    <a href="${pageContext.request.contextPath}/admin/manage-users?action=edit&id=${u.userId}" class="btn btn-outline btn-sm">Edit</a>
                                    <a href="${pageContext.request.contextPath}/admin/manage-users?action=delete&id=${u.userId}" 
                                       onclick="return confirm('Are you sure you want to delete this user?')" class="btn btn-danger btn-sm">Delete</a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>

    <jsp:include page="/includes/footer.jsp" />

</body>
</html>
