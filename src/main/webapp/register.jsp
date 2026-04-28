<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - HealthSync</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<div class="auth-container">
    <div class="auth-box auth-box-wide">

        <div class="auth-header">
            <div class="auth-logo">🏥</div>
            <h2>Create Patient Account</h2>
            <p>Fill in your details to register</p>
        </div>

        <!-- Error Message -->
        <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error">
            ⚠ <%= request.getAttribute("error") %>
        </div>
        <% } %>

        <form action="${pageContext.request.contextPath}/register"
              method="post" id="registerForm">

            <!-- Personal Information -->
            <div class="form-section">
                <h3 class="form-section-title">Personal Information</h3>

                <div class="form-group">
                    <label>Full Name <span class="required">*</span></label>
                    <input type="text"
                           name="fullName"
                           placeholder="Enter full name (letters only)"
                           required>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>Email Address <span class="required">*</span></label>
                        <input type="email"
                               name="email"
                               placeholder="Enter your email"
                               required>
                    </div>
                    <div class="form-group">
                        <label>Phone Number <span class="required">*</span></label>
                        <input type="text"
                               name="phone"
                               placeholder="10 digit phone number"
                               maxlength="10"
                               required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>Password <span class="required">*</span></label>
                        <input type="password"
                               name="password"
                               placeholder="Minimum 6 characters"
                               required>
                    </div>
                    <div class="form-group">
                        <label>Confirm Password <span class="required">*</span></label>
                        <input type="password"
                               name="confirmPassword"
                               placeholder="Repeat your password"
                               required>
                    </div>
                </div>
            </div>

            <!-- Medical Information -->
            <div class="form-section">
                <h3 class="form-section-title">Medical Information</h3>

                <div class="form-row">
                    <div class="form-group">
                        <label>Date of Birth</label>
                        <input type="date" name="dateOfBirth">
                    </div>
                    <div class="form-group">
                        <label>Gender</label>
                        <select name="gender">
                            <option value="">Select Gender</option>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                            <option value="Other">Other</option>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>Blood Group</label>
                        <select name="bloodGroup">
                            <option value="">Select Blood Group</option>
                            <option value="A+">A+</option>
                            <option value="A-">A-</option>
                            <option value="B+">B+</option>
                            <option value="B-">B-</option>
                            <option value="O+">O+</option>
                            <option value="O-">O-</option>
                            <option value="AB+">AB+</option>
                            <option value="AB-">AB-</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Emergency Contact</label>
                        <input type="text"
                               name="emergencyContact"
                               placeholder="Emergency phone number">
                    </div>
                </div>

                <div class="form-group">
                    <label>Address</label>
                    <textarea name="address"
                              rows="2"
                              placeholder="Enter your full address"></textarea>
                </div>
            </div>

            <button type="submit" class="btn btn-primary btn-full">
                Create Account
            </button>
        </form>

        <div class="auth-footer">
            <p>Already have an account?
                <a href="${pageContext.request.contextPath}/login.jsp">
                    Login here
                </a>
            </p>
        </div>

    </div>
</div>

</body>
</html>