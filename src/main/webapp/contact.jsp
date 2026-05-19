<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us - HealthSync</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css?v=2.0">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<!-- Contact Hero Section -->
<section class="contact-hero">
    <div class="contact-hero-content">
        <h1>Contact Our Team</h1>
        <p>Have any questions? We'd love to hear from you. Get in touch with our medical, technical, or customer support teams.</p>
    </div>
</section>

<!-- Contact Main Container -->
<div class="contact-container">

    <!-- Contact Info Column -->
    <div class="contact-info-card">
        <h3>Reach Out Directly</h3>
        <p>Feel free to contact us via email, phone, or visit our central clinic. Our administration office is always ready to guide you.</p>

        <!-- Detail: Email -->
        <div class="contact-detail-item">
            <div class="detail-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"></path><polyline points="22,6 12,13 2,6"></polyline></svg>
            </div>
            <div class="detail-text">
                <h4>Email Addresses</h4>
                <p>Support: support@healthsync.com</p>
                <p>Partnerships: business@healthsync.com</p>
            </div>
        </div>

        <!-- Detail: Phone -->
        <div class="contact-detail-item">
            <div class="detail-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M22 16.92v3a2 2 0 0 1-2.18 2 19.79 19.79 0 0 1-8.63-3.07 19.5 19.5 0 0 1-6-6 19.79 19.79 0 0 1-3.07-8.67A2 2 0 0 1 4.11 2h3a2 2 0 0 1 2 1.72 12.84 12.84 0 0 0 .7 2.81 2 2 0 0 1-.45 2.11L8.09 9.91a16 16 0 0 0 6 6l1.27-1.27a2 2 0 0 1 2.11-.45 12.84 12.84 0 0 0 2.81.7A2 2 0 0 1 22 16.92z"></path></svg>
            </div>
            <div class="detail-text">
                <h4>Phone Center</h4>
                <p>Toll Free: +977 9800000000</p>
                <p>Office: +01 4455667</p>
            </div>
        </div>

        <!-- Detail: Address -->
        <div class="contact-detail-item">
            <div class="detail-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"></path><circle cx="12" cy="10" r="3"></circle></svg>
            </div>
            <div class="detail-text">
                <h4>Headquarters</h4>
                <p>HealthSync Center, Block B, Floor 4</p>
                <p>Kathmandu, Nepal</p>
            </div>
        </div>

        <!-- Detail: Hours -->
        <div class="contact-detail-item">
            <div class="detail-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"></circle><polyline points="12 6 12 12 16 14"></polyline></svg>
            </div>
            <div class="detail-text">
                <h4>Working Hours</h4>
                <p>Monday - Friday: 8:00 AM - 8:00 PM</p>
                <p>Saturday - Sunday: 9:00 AM - 5:00 PM</p>
            </div>
        </div>
    </div>

    <!-- Contact Form Column -->
    <div class="contact-form-card">
        <h3>Send Us a Message</h3>
        <p>Fill out the form below and our response coordinators will contact you in under 12 hours.</p>

        <!-- Success message alert container -->
        <div id="contactSuccessAlert" style="display: none;" class="contact-success-alert">
            <div class="success-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"><polyline points="20 6 9 17 4 12"></polyline></svg>
            </div>
            <div>
                <strong>Thank you!</strong> Your message has been sent successfully. We will get back to you shortly.
            </div>
        </div>

        <form id="contactForm" onsubmit="handleContactSubmit(event)">
            <div class="form-row">
                <div class="form-group">
                    <label for="contactName">Full Name <span class="required">*</span></label>
                    <input type="text" id="contactName" placeholder="John Doe" required>
                </div>
                <div class="form-group">
                    <label for="contactEmail">Email Address <span class="required">*</span></label>
                    <input type="email" id="contactEmail" placeholder="john@example.com" required>
                </div>
            </div>

            <div class="form-group">
                <label for="contactSubject">Subject <span class="required">*</span></label>
                <input type="text" id="contactSubject" placeholder="Inquiry about online appointments" required>
            </div>

            <div class="form-group">
                <label for="contactMessage">Your Message <span class="required">*</span></label>
                <textarea id="contactMessage" rows="6" placeholder="Write your message details here..." required></textarea>
            </div>

            <button type="submit" class="btn btn-primary btn-full">
                <span style="display: inline-flex; align-items: center; gap: 8px;">
                    Send Message
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"><line x1="22" y1="2" x2="11" y2="13"></line><polygon points="22 2 15 22 11 13 2 9 22 2"></polygon></svg>
                </span>
            </button>
        </form>
    </div>
</div>

<!-- Mock Map Section -->
<section class="mock-map-section">
    <div class="mock-map-container">
        <!-- SVG Grid/Tech Pattern to mimic background mapping -->
        <svg width="100%" height="100%" xmlns="http://www.w3.org/2000/svg">
            <defs>
                <pattern id="grid" width="40" height="40" patternUnits="userSpaceOnUse">
                    <path d="M 40 0 L 0 0 0 40" fill="none" stroke="rgba(59, 130, 246, 0.05)" stroke-width="1.5"/>
                </pattern>
                <radialGradient id="grad" cx="50%" cy="50%" r="50%">
                    <stop offset="0%" stop-color="#eff6ff" />
                    <stop offset="100%" stop-color="#dbeafe" stop-opacity="0.5" />
                </radialGradient>
            </defs>
            <rect width="100%" height="100%" fill="url(#grad)" />
            <rect width="100%" height="100%" fill="url(#grid)" />
            <!-- Rivers / Road simulations -->
            <path d="M -50 150 Q 300 120 600 250 T 1400 180" fill="none" stroke="rgba(59, 130, 246, 0.15)" stroke-width="12" stroke-linecap="round"/>
            <path d="M 200 -50 Q 320 200 450 350 T 700 600" fill="none" stroke="rgba(255, 255, 255, 0.6)" stroke-width="6" stroke-linecap="round"/>
        </svg>

        <!-- Bouncing Pointer Marker -->
        <div class="mock-map-overlay">
            <div class="mock-map-pin">
                <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="3" y="3" width="18" height="18" rx="2" ry="2"></rect><line x1="12" y1="8" x2="12" y2="16"></line><line x1="8" y1="12" x2="16" y2="12"></line></svg>
            </div>
            <div class="mock-map-info">
                <h4>HealthSync headquarters</h4>
                <p>Kathmandu, Nepal</p>
            </div>
        </div>
    </div>
</section>

<script>
    function handleContactSubmit(event) {
        event.preventDefault();

        // Hide standard form elements or simulate sending
        const form = document.getElementById('contactForm');
        const alertBox = document.getElementById('contactSuccessAlert');

        // Reset inputs smoothly and show custom sliding notification
        form.reset();
        alertBox.style.display = 'flex';

        // Scroll smoothly to success alert
        alertBox.scrollIntoView({ behavior: 'smooth', block: 'center' });

        // Auto-fade alert after 8 seconds
        setTimeout(() => {
            alertBox.style.animation = 'fadeOut 0.5s ease forwards';
            setTimeout(() => {
                alertBox.style.display = 'none';
                alertBox.style.animation = '';
            }, 500);
        }, 8000);
    }
</script>

<!-- Footer -->
<%@ include file="/includes/footer.jsp" %>

</body>
</html>