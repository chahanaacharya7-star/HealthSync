<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Medical Records | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="dashboard-container">
        <div class="dashboard-header">
            <h1 class="dashboard-title">Medical Records</h1>
            <p class="dashboard-subtitle">A history of your past visits and prescriptions.</p>
        </div>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Visit Date</th>
                        <th>Doctor</th>
                        <th>Diagnosis</th>
                        <th>Prescription</th>
                        <th>Follow-up</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="record" items="${records}">
                        <tr>
                            <td>${record.visitDate}</td>
                            <td>
                                <div>Dr. ${record.doctorName}</div>
                                <div style="font-size: 12px; color: #777;">${record.doctorSpecialization}</div>
                            </td>
                            <td>${record.diagnosis}</td>
                            <td>${record.prescription}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty record.followUpDate}">
                                        ${record.followUpDate}
                                    </c:when>
                                    <c:otherwise>
                                        <em style="color: #999;">None</em>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty records}">
                        <tr>
                            <td colspan="5" style="text-align: center; padding: 30px; color: #777;">
                                You do not have any past medical records.
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
