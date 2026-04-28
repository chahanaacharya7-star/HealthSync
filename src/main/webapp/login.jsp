<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - HealthSync</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<div class="auth-container">
    <div class="auth-box">

        <div class="auth-header">
            <div class="auth-logo">🏥</div>
            <h2>Welcome Back</h2>
            <p>Login to your HealthSync account</p>
        </div>

        <!-- Error Message -->
        <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error">
            ⚠ <%= request.getAttribute("error") %>
        </div>
        <% } %>

        <!-- Success Message -->
        <% if (request.getAttribute("success") != null) { %>
        <div class="alert alert-success">
            ✔ <%= request.getAttribute("success") %>
        </div>
        <% } %>

        <form action="${pageContext.request.contextPath}/login"
              method="post" id="loginForm">

            <div class="form-group">
                <label for="email">Email Address <span class="required">*</span></label>
                <input type="email"
                       id="email"
                       name="email"
                       placeholder="Enter your email address"
                       required>
            </div>

            <div class="form-group">
                <label for="password">Password <span class="required">*</span></label>
                <input type="password"
                       id="password"
                       name="password"
                       placeholder="Enter your password"
                       required>
            </div>

            <button type="submit" class="btn btn-primary btn-full">
                Login to Account
            </button>
        </form>

        <div class="auth-footer">
            <p>Don't have an account?
                <a href="${pageContext.request.contextPath}/register.jsp">
                    Register here
                </a>
            </p>
            <p>
                <a href="${pageContext.request.contextPath}/index.jsp">
                    ← Back to Home
                </a>
            </p>
        </div>

    </div>
</div>

</body>
</html>