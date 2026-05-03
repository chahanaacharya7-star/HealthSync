<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Patients | Doctor | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="dashboard-container">
        <div class="dashboard-header">
            <h1 class="dashboard-title">My Patients</h1>
            <p class="dashboard-subtitle">List of your unique patients and their details.</p>
        </div>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Age/DOB</th>
                        <th>Phone</th>
                        <th>Blood Group</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${patients}">
                        <tr>
                            <td style="font-weight: 600;">${p.fullName}</td>
                            <td>${p.dateOfBirth}</td>
                            <td>${p.phone}</td>
                            <td>
                                <span class="badge" style="background:#f3f4f6; color:#374151;">${not empty p.bloodGroup ? p.bloodGroup : 'N/A'}</span>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/doctor/patient-records?patientId=${p.patientId}" class="btn btn-outline btn-sm">View Records</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty patients}">
                        <tr>
                            <td colspan="5" style="text-align: center; padding: 40px; color: #777;">
                                You don't have any patients assigned yet.
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
