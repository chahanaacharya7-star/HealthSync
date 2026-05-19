<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HealthSync - Health Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<!-- Hero Section -->
<section class="hero">
    <div class="hero-content">
        <h1>Welcome to <span class="highlight">HealthSync</span></h1>
        <p>A complete Health Management System for Patients, Doctors and Admins.</p>
        <p>Book appointments, manage records, and stay connected with your healthcare team.</p>
        <div class="hero-buttons">
            <a href="${pageContext.request.contextPath}/register.jsp"
               class="btn btn-primary">Register as Patient</a>
            <a href="${pageContext.request.contextPath}/login.jsp"
               class="btn btn-outline">Login</a>
        </div>
    </div>
    <div class="hero-image">
        <div class="hero-icon">🏥</div>
    </div>
</section>

<!-- Features Section -->
<section class="features">
    <h2>Why Choose HealthSync?</h2>
    <div class="features-grid">
        <div class="feature-card">
            <div class="feature-icon">📅</div>
            <h3>Easy Appointments</h3>
            <p>Book and manage appointments with doctors online without visiting the hospital.</p>
        </div>
        <div class="feature-card">
            <div class="feature-icon">📋</div>
            <h3>Medical Records</h3>
            <p>Access your complete medical history and records anytime, anywhere securely.</p>
        </div>
        <div class="feature-card">
            <div class="feature-icon">👨‍⚕️</div>
            <h3>Expert Doctors</h3>
            <p>Connect with qualified doctors across multiple specializations.</p>
        </div>
        <div class="feature-card">
            <div class="feature-icon">🔒</div>
            <h3>Secure & Private</h3>
            <p>Your health data is encrypted and protected with role-based access control.</p>
        </div>
        <div class="feature-card">
            <div class="feature-icon">📊</div>
            <h3>Admin Reports</h3>
            <p>Comprehensive reports and analytics for hospital administrators.</p>
        </div>
        <div class="feature-card">
            <div class="feature-icon">📱</div>
            <h3>Responsive Design</h3>
            <p>Access the system from any device — desktop, tablet, or mobile.</p>
        </div>
    </div>
</section>

<!-- How It Works Section -->
<section class="how-it-works">
    <h2>How It Works</h2>
    <div class="steps-grid">
        <div class="step">
            <div class="step-number">1</div>
            <h3>Register</h3>
            <p>Create your patient account with personal and medical details.</p>
        </div>
        <div class="step-arrow">→</div>
        <div class="step">
            <div class="step-number">2</div>
            <h3>Get Approved</h3>
            <p>Admin reviews and approves your registration request.</p>
        </div>
        <div class="step-arrow">→</div>
        <div class="step">
            <div class="step-number">3</div>
            <h3>Book Appointment</h3>
            <p>Search doctors and book your appointment online.</p>
        </div>
        <div class="step-arrow">→</div>
        <div class="step">
            <div class="step-number">4</div>
            <h3>Get Treatment</h3>
            <p>Visit doctor and access your records and reports anytime.</p>
        </div>
    </div>
</section>

<!-- Footer -->
<%@ include file="/includes/footer.jsp" %>

</body>
</html>