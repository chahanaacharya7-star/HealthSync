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
                <p>Modify details for #${d.doctorId}</p>
            </div>

            <form action="${pageContext.request.contextPath}/admin/update-doctor" method="POST">
                <input type="hidden" name="doctorId" value="${d.doctorId}">
                <input type="hidden" name="userId" value="${d.userId}">

                <div class="form-section">
                    <div class="form-section-title">Professional Information</div>
                    <div class="form-group">
                        <label for="fullName">Full Name</label>
                        <input type="text" name="fullName" value="${d.fullName}" required>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" name="email" value="${d.email}" required>
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input type="text" name="phone" value="${d.phone}" required>
                        </div>
                    </div>
                </div>

                <div class="form-section">
                    <div class="form-section-title">Specialization & Experience</div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="specialization">Specialization</label>
                            <input type="text" name="specialization" value="${d.specialization}" required>
                        </div>
                        <div class="form-group">
                            <label for="qualification">Qualification</label>
                            <input type="text" name="qualification" value="${d.qualification}" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="experience">Years of Experience</label>
                            <input type="number" name="experience" value="${d.experienceYears}" required>
                        </div>
                        <div class="form-group">
                            <label for="availableDays">Available Days</label>
                            <input type="text" name="availableDays" value="${d.availableDays}" placeholder="e.g. Mon, Wed, Fri">
                        </div>
                    </div>
                </div>

                <div style="display: flex; gap: 15px; margin-top: 20px;">
                    <a href="${pageContext.request.contextPath}/admin/manage-doctors" class="btn btn-outline" style="flex: 1;">Cancel</a>
                    <button type="submit" class="btn btn-primary" style="flex: 1;">Update Doctor</button>
                </div>
            </form>
        </div>
    </main>

    <jsp:include page="/includes/footer.jsp" />

</body>
</html>
