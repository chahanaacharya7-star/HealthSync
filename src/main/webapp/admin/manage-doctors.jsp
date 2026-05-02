<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Doctors | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="dashboard-container">
        <div class="table-header" style="margin-bottom: 30px;">
            <div>
                <h1 class="dashboard-title">Manage Doctors</h1>
                <p class="dashboard-subtitle">View and manage all healthcare providers.</p>
            </div>
            <a href="${pageContext.request.contextPath}/admin/add-doctor.jsp" class="btn btn-primary">Add New Doctor</a>
        </div>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Specialization</th>
                        <th>Email</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="d" items="${doctors}">
                        <tr>
                            <td>#${d.doctorId}</td>
                            <td>${d.fullName}</td>
                            <td>${d.specialization}</td>
                            <td>${d.email}</td>
                            <td>
                                <span class="badge ${d.status == 'approved' ? 'badge-success' : 'badge-warning'}">
                                    ${d.status}
                                </span>
                            </td>
                            <td>
                                <div style="display: flex; gap: 8px;">
                                    <a href="${pageContext.request.contextPath}/admin/edit-doctor?id=${d.doctorId}" class="btn btn-outline btn-sm">Edit</a>
                                    <button onclick="deleteDoctor(${d.doctorId})" class="btn btn-danger btn-sm">Delete</button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>

    <jsp:include page="/includes/footer.jsp" />

    <script>
        function deleteDoctor(id) {
            if (confirm('Are you sure you want to delete this doctor? This action cannot be undone.')) {
                fetch('${pageContext.request.contextPath}/doctors/' + id, {
                    method: 'DELETE'
                }).then(res => {
                    if (res.ok) location.reload();
                    else alert('Delete failed');
                });
            }
        }
    </script>

</body>
</html>
