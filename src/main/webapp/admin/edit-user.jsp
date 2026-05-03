<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit User | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="auth-container">
        <div class="auth-box auth-box-wide">
            <div class="auth-header">
                <h2>Edit User Account</h2>
                <p>Update account details for ${userToEdit.fullName}</p>
            </div>

            <form action="${pageContext.request.contextPath}/admin/manage-users" method="POST">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="userId" value="${userToEdit.userId}">
                
                <div class="form-section">
                    <h3 class="form-section-title">Account Information</h3>
                    <div class="form-group">
                        <label>Full Name <span class="required">*</span></label>
                        <input type="text" name="fullName" value="${userToEdit.fullName}" required>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label>Email Address <span class="required">*</span></label>
                            <input type="email" name="email" value="${userToEdit.email}" required>
                        </div>
                        <div class="form-group">
                            <label>Phone Number <span class="required">*</span></label>
                            <input type="tel" name="phone" value="${userToEdit.phone}" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>New Password (leave blank to keep current)</label>
                        <input type="password" name="password" placeholder="Enter new password if you want to change it">
                    </div>
                </div>

                <div class="form-section">
                    <h3 class="form-section-title">Role & Status</h3>
                    <div class="form-row">
                        <div class="form-group">
                            <label>Account Role <span class="required">*</span></label>
                            <select name="role" required>
                                <option value="patient" ${userToEdit.role == 'patient' ? 'selected' : ''}>Patient</option>
                                <option value="doctor" ${userToEdit.role == 'doctor' ? 'selected' : ''}>Doctor</option>
                                <option value="admin" ${userToEdit.role == 'admin' ? 'selected' : ''}>Administrator</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Account Status <span class="required">*</span></label>
                            <select name="status" required>
                                <option value="approved" ${userToEdit.status == 'approved' ? 'selected' : ''}>Approved</option>
                                <option value="pending" ${userToEdit.status == 'pending' ? 'selected' : ''}>Pending</option>
                                <option value="rejected" ${userToEdit.status == 'rejected' ? 'selected' : ''}>Rejected</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div style="display: flex; gap: 15px; margin-top: 20px;">
                    <button type="submit" class="btn btn-primary" style="flex: 2;">Update User Account</button>
                    <a href="${pageContext.request.contextPath}/admin/manage-users" class="btn btn-outline" style="flex: 1;">Cancel</a>
                </div>
            </form>
        </div>
    </main>

    <jsp:include page="/includes/footer.jsp" />

</body>
</html>
