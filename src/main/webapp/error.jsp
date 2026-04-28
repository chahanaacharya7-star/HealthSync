<%@ page language="java" contentType="text/html; charset=UTF-8"
         isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - HealthSync</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<div class="error-container">
    <div class="error-box">
        <div class="error-icon">⚠</div>
        <h1 class="error-code">Oops!</h1>
        <h2>Something went wrong</h2>

        <%
            Integer statusCode = (Integer) request.getAttribute(
                    "jakarta.servlet.error.status_code");
            String errorMessage = (String) request.getAttribute(
                    "jakarta.servlet.error.message");

            if (statusCode != null && statusCode == 404) {
        %>
        <p>The page you are looking for does not exist.</p>
        <div class="error-code-badge">404 - Page Not Found</div>
        <% } else if (statusCode != null && statusCode == 403) { %>
        <p>You do not have permission to access this page.</p>
        <div class="error-code-badge">403 - Access Denied</div>
        <% } else { %>
        <p>An unexpected error occurred. Please try again.</p>
        <% if (errorMessage != null) { %>
        <div class="error-code-badge"><%= errorMessage %></div>
        <% } %>
        <% } %>

        <div class="error-actions">
            <a href="${pageContext.request.contextPath}/index.jsp"
               class="btn btn-primary">Go to Home</a>
            <a href="javascript:history.back()"
               class="btn btn-outline">Go Back</a>
        </div>
    </div>
</div>

</body>
</html>