<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Patients | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="dashboard-container">
        <div class="table-header" style="margin-bottom: 30px;">
            <div>
                <h1 class="dashboard-title">Manage Patients</h1>
                <p class="dashboard-subtitle">View and manage all registered patients.</p>
            </div>
            <a href="${pageContext.request.contextPath}/admin/add-patient.jsp" class="btn btn-primary">Add New Patient</a>
        </div>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Blood Group</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${patients}">
                        <tr>
                            <td>#${p.patientId}</td>
                            <td>${p.fullName}</td>
                            <td>${p.email}</td>
                            <td>${p.phone}</td>
                            <td>${p.bloodGroup}</td>
                            <td>
                                <span class="badge ${p.status == 'approved' ? 'badge-success' : 'badge-warning'}">
                                    ${p.status}
                                </span>
                            </td>
                            <td>
                                <div style="display: flex; gap: 8px;">
                                    <a href="${pageContext.request.contextPath}/admin/edit-patient?id=${p.patientId}" class="btn btn-outline btn-sm">Edit</a>
                                    <button onclick="deletePatient(${p.patientId})" class="btn btn-danger btn-sm">Delete</button>
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
        function deletePatient(id) {
            if (confirm('Are you sure you want to delete this patient? This action cannot be undone.')) {
                fetch('${pageContext.request.contextPath}/patients/' + id, {
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
