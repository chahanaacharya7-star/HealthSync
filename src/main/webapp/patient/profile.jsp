<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="dashboard-container">
        <div class="dashboard-header">
            <h1 class="dashboard-title">My Profile</h1>
            <p class="dashboard-subtitle">Update your personal and medical details.</p>
        </div>

        <c:if test="${param.success == 'true'}">
            <div class="alert alert-success" style="padding: 15px; background: #d1fae5; color: #065f46; border-radius: 8px; margin-bottom: 20px;">
                Profile updated successfully!
            </div>
        </c:if>

        <div class="form-container" style="max-width: 800px; margin: 0 auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 4px 6px rgba(0,0,0,0.05);">
            <form action="${pageContext.request.contextPath}/patient/profile" method="POST">
                <input type="hidden" name="action" value="updateProfile" />
                
                <h3 style="margin-bottom: 20px; border-bottom: 1px solid #eee; padding-bottom: 10px;">Account Information</h3>
                
                <div class="form-group">
                    <label>Full Name</label>
                    <input type="text" name="fullName" value="${patient.fullName}" class="form-control" required />
                </div>
                
                <div class="form-group" style="display: flex; gap: 20px;">
                    <div style="flex: 1;">
                        <label>Email</label>
                        <input type="email" name="email" value="${patient.email}" class="form-control" required />
                    </div>
                    <div style="flex: 1;">
                        <label>Phone Number</label>
                        <input type="text" name="phone" value="${patient.phone}" class="form-control" required />
                    </div>
                </div>

                <h3 style="margin-top: 30px; margin-bottom: 20px; border-bottom: 1px solid #eee; padding-bottom: 10px;">Medical Information</h3>
                
                <div class="form-group" style="display: flex; gap: 20px;">
                    <div style="flex: 1;">
                        <label>Date of Birth</label>
                        <input type="date" name="dateOfBirth" value="${patient.dateOfBirth}" class="form-control" />
                    </div>
                    <div style="flex: 1;">
                        <label>Gender</label>
                        <select name="gender" class="form-control">
                            <option value="Male" ${patient.gender == 'Male' ? 'selected' : ''}>Male</option>
                            <option value="Female" ${patient.gender == 'Female' ? 'selected' : ''}>Female</option>
                            <option value="Other" ${patient.gender == 'Other' ? 'selected' : ''}>Other</option>
                        </select>
                    </div>
                    <div style="flex: 1;">
                        <label>Blood Group</label>
                        <input type="text" name="bloodGroup" value="${patient.bloodGroup}" class="form-control" />
                    </div>
                </div>

                <div class="form-group">
                    <label>Address</label>
                    <input type="text" name="address" value="${patient.address}" class="form-control" />
                </div>
                
                <div class="form-group">
                    <label>Emergency Contact</label>
                    <input type="text" name="emergencyContact" value="${patient.emergencyContact}" class="form-control" />
                </div>

                <div style="margin-top: 30px;">
                    <button type="submit" class="btn btn-primary" style="width: 100%;">Save Changes</button>
                </div>
            </form>
        </div>
    </main>

    <jsp:include page="/includes/footer.jsp" />

</body>
</html>
