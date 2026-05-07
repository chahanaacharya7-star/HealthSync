<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.healthsync.model.Patient" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile - HealthSync</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<div class="dashboard-container">
    <h2 class="dashboard-title">My Profile</h2>

    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-error">
        ⚠ <%= request.getAttribute("error") %>
    </div>
    <% } %>
    <% if (request.getAttribute("success") != null) { %>
    <div class="alert alert-success">
        ✔ <%= request.getAttribute("success") %>
    </div>
    <% } %>

    <%
        Patient patient = (Patient) request.getAttribute("patient");
    %>

    <div class="profile-grid">

        <!-- Update Profile Form -->
        <div class="form-card">
            <h3 class="form-section-title">Personal Information</h3>
            <form action="${pageContext.request.contextPath}/patient/profile"
                  method="post">
                <input type="hidden" name="action" value="updateProfile">

                <div class="form-group">
                    <label>Full Name <span class="required">*</span></label>
                    <input type="text" name="fullName"
                           value="<%= session.getAttribute("userName") %>"
                           required>
                </div>

                <div class="form-group">
                    <label>Email Address</label>
                    <input type="email"
                           value="<%= patient != null ?
                               patient.getEmail() : "" %>"
                           disabled>
                    <small style="color:#999;">
                        Email cannot be changed
                    </small>
                </div>

                <div class="form-group">
                    <label>Phone Number <span class="required">*</span></label>
                    <input type="text" name="phone"
                           value="<%= patient != null ?
                               patient.getPhone() : "" %>"
                           maxlength="10" required>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>Date of Birth</label>
                        <input type="date" name="dateOfBirth"
                               value="<%= patient != null &&
                                   patient.getDateOfBirth() != null ?
                                   patient.getDateOfBirth() : "" %>">
                    </div>
                    <div class="form-group">
                        <label>Gender</label>
                        <select name="gender">
                            <option value="">Select</option>
                            <option value="Male"
                                    <%= patient != null &&
                                            "Male".equals(patient.getGender()) ?
                                            "selected" : "" %>>Male</option>
                            <option value="Female"
                                    <%= patient != null &&
                                            "Female".equals(patient.getGender()) ?
                                            "selected" : "" %>>Female</option>
                            <option value="Other"
                                    <%= patient != null &&
                                            "Other".equals(patient.getGender()) ?
                                            "selected" : "" %>>Other</option>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>Blood Group</label>
                        <select name="bloodGroup">
                            <option value="">Select</option>
                            <% String[] groups =
                                    {"A+","A-","B+","B-","O+","O-","AB+","AB-"};
                                for (String g : groups) { %>
                            <option value="<%= g %>"
                                    <%= patient != null &&
                                            g.equals(patient.getBloodGroup()) ?
                                            "selected" : "" %>>
                                <%= g %>
                            </option>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Emergency Contact</label>
                        <input type="text" name="emergencyContact"
                               value="<%= patient != null &&
                                   patient.getEmergencyContact() != null ?
                                   patient.getEmergencyContact() : "" %>">
                    </div>
                </div>

                <div class="form-group">
                    <label>Address</label>
                    <textarea name="address" rows="2"><%=
                    patient != null && patient.getAddress() != null ?
                            patient.getAddress() : "" %></textarea>
                </div>

                <button type="submit" class="btn btn-primary">
                    Update Profile
                </button>
            </form>
        </div>

        <!-- Change Password Form -->
        <div class="form-card">
            <h3 class="form-section-title">Change Password</h3>
            <form action="${pageContext.request.contextPath}/patient/profile"
                  method="post">
                <input type="hidden" name="action" value="changePassword">

                <div class="form-group">
                    <label>Current Password <span class="required">*</span></label>
                    <input type="password" name="currentPassword"
                           placeholder="Enter current password" required>
                </div>

                <div class="form-group">
                    <label>New Password <span class="required">*</span></label>
                    <input type="password" name="newPassword"
                           placeholder="Minimum 6 characters" required>
                </div>

                <div class="form-group">
                    <label>Confirm New Password <span class="required">*</span>
                    </label>
                    <input type="password" name="confirmPassword"
                           placeholder="Repeat new password" required>
                </div>

                <button type="submit" class="btn btn-warning">
                    Change Password
                </button>
            </form>
        </div>

    </div>
</div>

<%@ include file="/includes/footer.jsp" %>

</body>
</html>