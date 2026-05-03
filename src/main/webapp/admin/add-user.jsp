<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New User | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="auth-container">
        <div class="auth-box auth-box-wide">
            <div class="auth-header">
                <h2>Add New User</h2>
                <p>Create a new account with a specific role.</p>
            </div>

            <c:if test="${not empty param.error}">
                <div class="alert alert-error" style="margin-bottom: 20px;">${param.error}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/admin/manage-users" method="POST">
                <input type="hidden" name="action" value="insert">
                
                <div class="form-section">
                    <h3 class="form-section-title">Account Information</h3>
                    <div class="form-group">
                        <label>Full Name <span class="required">*</span></label>
                        <input type="text" name="fullName" required placeholder="Enter full name">
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label>Email Address <span class="required">*</span></label>
                            <input type="email" name="email" required placeholder="email@example.com">
                        </div>
                        <div class="form-group">
                            <label>Phone Number <span class="required">*</span></label>
                            <input type="tel" name="phone" required placeholder="98XXXXXXXX">
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Password <span class="required">*</span></label>
                        <input type="password" name="password" required placeholder="Initial password">
                    </div>
                </div>

                <div class="form-section">
                    <h3 class="form-section-title">Role & Status</h3>
                    <div class="form-row">
                        <div class="form-group">
                            <label>Account Role <span class="required">*</span></label>
                            <select name="role" required>
                                <option value="patient">Patient</option>
                                <option value="doctor">Doctor</option>
                                <option value="admin">Administrator</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Account Status <span class="required">*</span></label>
                            <select name="status" required>
                                <option value="approved">Approved</option>
                                <option value="pending">Pending</option>
                                <option value="rejected">Rejected</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div style="display: flex; gap: 15px; margin-top: 20px;">
                    <button type="submit" class="btn btn-primary" style="flex: 2;">Create User Account</button>
                    <a href="${pageContext.request.contextPath}/admin/manage-users" class="btn btn-outline" style="flex: 1;">Cancel</a>
                </div>
            </form>
        </div>
    </main>

    <jsp:include page="/includes/footer.jsp" />

</body>
</html>
