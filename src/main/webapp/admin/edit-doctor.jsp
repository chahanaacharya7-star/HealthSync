<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Doctor | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="auth-container">
        <div class="auth-box auth-box-wide">
            <div class="auth-header">
                <h2>Edit Doctor Profile</h2>
                <p>Update information for ${doctor.fullName}</p>
            </div>

            <form action="${pageContext.request.contextPath}/admin/manage-doctors" method="POST">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="doctorId" value="${doctor.doctorId}">
                <input type="hidden" name="userId" value="${doctor.userId}">
                
                <div class="form-section">
                    <h3 class="form-section-title">Personal Information</h3>
                    <div class="form-group">
                        <label>Full Name <span class="required">*</span></label>
                        <input type="text" name="fullName" value="${doctor.fullName}" required>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label>Email Address <span class="required">*</span></label>
                            <input type="email" name="email" value="${doctor.email}" required>
                        </div>
                        <div class="form-group">
                            <label>Phone Number <span class="required">*</span></label>
                            <input type="tel" name="phone" value="${doctor.phone}" required>
                        </div>
                    </div>
                </div>

                <div class="form-section">
                    <h3 class="form-section-title">Professional Details</h3>
                    <div class="form-row">
                        <div class="form-group">
                            <label>Specialization <span class="required">*</span></label>
                            <input type="text" name="specialization" value="${doctor.specialization}" required>
                        </div>
                        <div class="form-group">
                            <label>Qualification <span class="required">*</span></label>
                            <input type="text" name="qualification" value="${doctor.qualification}" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label>Experience (Years) <span class="required">*</span></label>
                            <input type="number" name="experienceYears" value="${doctor.experienceYears}" required min="0">
                        </div>
                        <div class="form-group">
                            <label>Available Days <span class="required">*</span></label>
                            <input type="text" name="availableDays" value="${doctor.availableDays}" required>
                        </div>
                    </div>
                </div>

                <div style="display: flex; gap: 15px; margin-top: 20px;">
                    <button type="submit" class="btn btn-primary" style="flex: 2;">Update Doctor Profile</button>
                    <a href="${pageContext.request.contextPath}/admin/manage-doctors" class="btn btn-outline" style="flex: 1;">Cancel</a>
                </div>
            </form>
        </div>
    </main>

    <jsp:include page="/includes/footer.jsp" />

</body>
</html>
