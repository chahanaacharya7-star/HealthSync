<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patient Records | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="dashboard-container">
        <a href="${pageContext.request.contextPath}/doctor/patients" style="display:inline-block; margin-bottom: 20px; color: #1a73e8; text-decoration: none;">&larr; Back to Patients</a>

        <div class="dashboard-header">
            <h1 class="dashboard-title">Medical Records: ${patient.fullName}</h1>
            <p class="dashboard-subtitle">View and add medical records for this patient.</p>
        </div>

        <c:if test="${param.success == 'true'}">
            <div class="alert alert-success" style="padding: 15px; background: #d1fae5; color: #065f46; border-radius: 8px; margin-bottom: 20px;">
                Medical record added successfully!
            </div>
        </c:if>

        <div style="display: grid; grid-template-columns: 1fr 2fr; gap: 30px; align-items: start;">
            <!-- Add New Record Form -->
            <div class="form-container" style="background: white; padding: 25px; border-radius: 10px; box-shadow: 0 4px 6px rgba(0,0,0,0.05);">
                <h3 style="margin-bottom: 20px; border-bottom: 1px solid #eee; padding-bottom: 10px;">Add New Record</h3>
                <form action="${pageContext.request.contextPath}/doctor/patient-records" method="POST">
                    <input type="hidden" name="patientId" value="${patient.patientId}" />

                    <div class="form-group">
                        <label>Visit Date <span style="color:red">*</span></label>
                        <input type="date" name="visitDate" class="form-control" required />
                    </div>

                    <div class="form-group">
                        <label>Diagnosis <span style="color:red">*</span></label>
                        <textarea name="diagnosis" class="form-control" rows="3" required></textarea>
                    </div>

                    <div class="form-group">
                        <label>Prescription</label>
                        <textarea name="prescription" class="form-control" rows="3"></textarea>
                    </div>

                    <div class="form-group">
                        <label>Follow-up Date</label>
                        <input type="date" name="followUpDate" class="form-control" />
                    </div>

                    <button type="submit" class="btn btn-primary" style="width: 100%; margin-top: 10px;">Save Record</button>
                </form>
            </div>

            <!-- Existing Records List -->
            <div>
                <div class="table-container">
                    <div class="table-header">
                        <h3>Record History</h3>
                    </div>
                    <table>
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Doctor</th>
                                <th>Diagnosis & Prescription</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="record" items="${records}">
                                <tr>
                                    <td style="white-space: nowrap; vertical-align: top;">
                                        <strong>${record.visitDate}</strong><br/>
                                        <c:if test="${not empty record.followUpDate}">
                                            <span style="font-size: 11px; color: #666;">Fol. up: ${record.followUpDate}</span>
                                        </c:if>
                                    </td>
                                    <td style="vertical-align: top;">
                                        Dr. ${record.doctorName}<br/>
                                        <span style="font-size: 12px; color: #888;">${record.doctorSpecialization}</span>
                                    </td>
                                    <td style="vertical-align: top;">
                                        <div style="margin-bottom: 8px;"><strong>Diagnosis:</strong> ${record.diagnosis}</div>
                                        <c:if test="${not empty record.prescription}">
                                            <div style="background:#f9f9f9; padding: 8px; border-radius: 4px; border-left: 3px solid #1a73e8;">
                                                <strong>Rx:</strong> ${record.prescription}
                                            </div>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty records}">
                                <tr>
                                    <td colspan="3" style="text-align: center; padding: 40px; color: #777;">
                                        No past records found for this patient.
                                    </td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </main>

    <jsp:include page="/includes/footer.jsp" />

</body>
</html>
