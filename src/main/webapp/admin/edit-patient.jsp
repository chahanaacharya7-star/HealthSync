<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Patient | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="auth-container">
        <div class="auth-box auth-box-wide">
            <div class="auth-header">
                <h2>Edit Patient Profile</h2>
                <p>Modify details for #${p.patientId}</p>
            </div>

            <form action="${pageContext.request.contextPath}/admin/update-patient" method="POST">
                <input type="hidden" name="patientId" value="${p.patientId}">
                <input type="hidden" name="userId" value="${p.userId}">

                <div class="form-section">
                    <div class="form-section-title">Personal Information</div>
                    <div class="form-group">
                        <label for="fullName">Full Name</label>
                        <input type="text" name="fullName" value="${p.fullName}" required>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" name="email" value="${p.email}" required>
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input type="text" name="phone" value="${p.phone}" required>
                        </div>
                    </div>
                </div>

                <div class="form-section">
                    <div class="form-section-title">Medical Information</div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="dob">Date of Birth</label>
                            <input type="date" name="dob" value="${p.dateOfBirth}">
                        </div>
                        <div class="form-group">
                            <label for="gender">Gender</label>
                            <select name="gender">
                                <option value="Male" ${p.gender == 'Male' ? 'selected' : ''}>Male</option>
                                <option value="Female" ${p.gender == 'Female' ? 'selected' : ''}>Female</option>
                                <option value="Other" ${p.gender == 'Other' ? 'selected' : ''}>Other</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="bloodGroup">Blood Group</label>
                            <input type="text" name="bloodGroup" value="${p.bloodGroup}">
                        </div>
                        <div class="form-group">
                            <label for="emergency">Emergency Contact</label>
                            <input type="text" name="emergency" value="${p.emergencyContact}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address">Address</label>
                        <textarea name="address" rows="2">${p.address}</textarea>
                    </div>
                </div>

                <div style="display: flex; gap: 15px; margin-top: 20px;">
                    <a href="${pageContext.request.contextPath}/admin/manage-patients" class="btn btn-outline" style="flex: 1;">Cancel</a>
                    <button type="submit" class="btn btn-primary" style="flex: 1;">Update Patient</button>
                </div>
            </form>
        </div>
    </main>

    <jsp:include page="/includes/footer.jsp" />

</body>
</html>
