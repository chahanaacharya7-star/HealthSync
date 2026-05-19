<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - HealthSync</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css?v=2.0">
</head>

<body>

<%@ include file="/includes/header.jsp" %>

<!-- About Hero Section -->
<section class="about-hero">
    <div class="about-hero-content">
        <h1>About HealthSync</h1>
        <p>Connecting patients, doctors, and technology to cultivate a healthier tomorrow through
            innovative, secure, and seamless healthcare management solutions.</p>
    </div>
</section>

<!-- About Intro Section -->
<section class="about-section">
    <div class="about-intro-grid">
        <div class="about-intro-text">
            <h2>Redefining Modern Healthcare</h2>
            <p>Founded in 2026, HealthSync was built on a simple yet revolutionary premise: healthcare
                should be accessible, efficient, and deeply human. Our platform serves as a modern bridge
                that unifies administrative workflows, clinical excellence, and patient engagement into a
                singular, cohesive ecosystem.</p>
            <p>Whether you are a patient booking an appointment on the go, a doctor coordinating care with
                comprehensive medical records, or an administrator tracking hospital operations, HealthSync
                provides the reliable foundation you need to thrive.</p>
        </div>
        <div class="about-intro-stats">
            <div class="about-stat-card">
                <div class="about-stat-number">10k+</div>
                <div class="about-stat-label">Happy Patients</div>
            </div>
            <div class="about-stat-card">
                <div class="about-stat-number">150+</div>
                <div class="about-stat-label">Expert Doctors</div>
            </div>
            <div class="about-stat-card">
                <div class="about-stat-number">99.9%</div>
                <div class="about-stat-label">Platform Uptime</div>
            </div>
            <div class="about-stat-card">
                <div class="about-stat-number">24/7</div>
                <div class="about-stat-label">Active Support</div>
            </div>
        </div>
    </div>
</section>

<!-- Vision & Mission Section -->
<section class="vision-mission-section">
    <div class="vision-mission-grid">
        <div class="vision-card">
            <div class="card-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24"
                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                     stroke-linejoin="round">
                    <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path>
                    <circle cx="12" cy="12" r="3"></circle>
                </svg>
            </div>
            <h3>Our Vision</h3>
            <p>To be the global benchmark for patient-centric digital healthcare solutions, empowering
                communities with accessible medical resources, transforming clinic management, and fostering
                collaborative relationships between doctors and their patients worldwide.</p>
        </div>

        <div class="mission-card">
            <div class="card-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24"
                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                     stroke-linejoin="round">
                    <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline>
                </svg>
            </div>
            <h3>Our Mission</h3>
            <p>To deliver an intuitive, state-of-the-art health management system that prioritizes data
                security, minimizes administrative delays, simplifies clinic scheduling, and enhances
                overall healthcare delivery for clinics, hospitals, and patients alike.</p>
        </div>
    </div>
</section>

<!-- Why Choose Us / Core Values Section -->
<section class="about-section">
    <div class="section-header">
        <h2>Why Choose HealthSync?</h2>
        <p>Our core values define who we are, driving every feature we design and every service we support.
        </p>
    </div>
    <div class="values-grid">
        <div class="value-card">
            <div class="value-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                     stroke-linejoin="round">
                    <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"></path>
                </svg>
            </div>
            <h4>Maximum Security</h4>
            <p>Your health records are fully encrypted and stored securely using role-based accessibility
                protocols.</p>
        </div>
        <div class="value-card blue-value">
            <div class="value-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                     stroke-linejoin="round">
                    <circle cx="12" cy="12" r="10"></circle>
                    <polyline points="12 6 12 12 16 14"></polyline>
                </svg>
            </div>
            <h4>Instant Scheduling</h4>
            <p>Book online appointments in under a minute without sitting in long reception queues.</p>
        </div>
        <div class="value-card purple-value">
            <div class="value-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                     stroke-linejoin="round">
                    <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
                    <circle cx="9" cy="7" r="4"></circle>
                    <path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
                    <path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
                </svg>
            </div>
            <h4>Collaborative Care</h4>
            <p>Keep your doctors on the same page with unified access to historical health records.</p>
        </div>
        <div class="value-card amber-value">
            <div class="value-icon">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                     stroke-linejoin="round">
                    <polygon
                            points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2">
                    </polygon>
                </svg>
            </div>
            <h4>Proven Excellence</h4>
            <p>Voted the #1 healthcare administrative software in Nepal for reliability and support.</p>
        </div>
    </div>
</section>

<!-- Our Team Section -->
<section class="team-section">
    <div class="section-header">
        <h2>Meet Our Leadership Team</h2>
        <p>Our multidisciplinary team of clinicians, technologists, and advocates is dedicated to changing
            how the world manages health.</p>
    </div>
    <div class="team-grid">
        <!-- CMO -->
        <div class="team-card">
            <div class="team-image-wrapper">
                <img src="${pageContext.request.contextPath}/images/Bimal.jpeg" alt="Bimal Subedi">
            </div>
            <div class="team-info">
                <h3>Bimal Subedi</h3>
                <div class="team-role">Team Leader and Project Manager</div>
                <details class="team-read-more">
                    <summary>Read More</summary>
                    <p class="team-bio">Managed and completed overall project documentation. Developed the
                        Admin Patient Management module. Implemented ManagePatientServlet.java. Created
                        admin/manage-patients.jsp, admin/add-patient.jsp, and admin/edit-patient.jsp.
                        Implemented full CRUD operations for patients. Designed custom UI pages for patient
                        management features.</p>
                    <div class="team-social">

                    </div>
                </details>
            </div>
        </div>


        <!-- Head of Operations -->
        <div class="team-card">
            <div class="team-image-wrapper">
                <img src="${pageContext.request.contextPath}/images/Chahana.jpeg" alt="Chahana">
            </div>
            <div class="team-info">
                <h3>Chahana Acharya</h3>
                <div class="team-role">Authentication and Project Features Handler</div>
                <details class="team-read-more">
                    <summary>Read More</summary>
                    <p class="team-bio">Managed the overall project structure and added database
                        integration. Developed Register, Login, and Logout system. Added MedicalRecord.java
                        model and MedicalRecordDAO.java. Added updatePassword() in UserDAO.java. Created
                        BookAppointmentServlet.java, MyAppointmentsServlet.java, MedicalRecordsServlet.java,
                        and PatientProfileServlet.java. Developed patient/dashboard.jsp,
                        patient/book-appointment.jsp, patient/my-appointments.jsp,
                        patient/medical-records.jsp, and patient/profile.jsp. Added new CSS styling in
                        style.css, executed SQL tables and database setup, tested overall functionality, and
                        designed custom UI pages.</p>
                    <div class="team-social">


                    </div>
                </details>
            </div>
        </div>

        <!-- Head of Innovation -->
        <div class="team-card">
            <div class="team-image-wrapper">
                <img src="${pageContext.request.contextPath}/images/Rizan.jpeg" alt="Rizan">
            </div>
            <div class="team-info">
                <h3>Rizan Rai</h3>
                <div class="team-role">Doctor Module Handler</div>
                <details class="team-read-more">
                    <summary>Read More</summary>
                    <p class="team-bio">Developed the Admin Doctor Management module. Implemented
                        ManageDoctorServlet.java. Created admin/manage-doctors.jsp, admin/add-doctor.jsp,
                        and admin/edit-doctor.jsp. Implemented full CRUD operations for doctors. Designed
                        custom UI pages for doctor management features.</p>
                    <div class="team-social">

                    </div>
                </details>
            </div>
        </div>

        <!-- Director of Patient Care -->
        <div class="team-card">
            <div class="team-image-wrapper">
                <img src="${pageContext.request.contextPath}/images/Siddhant.jpeg" alt="Siddhant">
            </div>
            <div class="team-info">
                <h3>Siddhant Baral</h3>
                <div class="team-role">Documentation Supporter</div>
                <details class="team-read-more">
                    <summary>Read More</summary>
                    <p class="team-bio">Assisted in project documentation. Supported documentation
                        preparation, report organization, and overall documentation management for the
                        healthcare management system project.</p>
                    <div class="team-social">

                    </div>
                </details>
            </div>
        </div>

        <!-- Chief Technical Officer -->
        <div class="team-card">
            <div class="team-image-wrapper">
                <img src="${pageContext.request.contextPath}/images/Samu.jpeg" alt="Samu">
            </div>
            <div class="team-info">
                <h3>Samu Sangroula</h3>
                <div class="team-role">Dashboard and Public Pages Developer</div>
                <details class="team-read-more">
                    <summary>Read More</summary>
                    <p class="team-bio">Developed dashboards and public pages. Implemented
                        PatientDashboardServlet.java and DoctorDashboardServlet.java. Created
                        patient/dashboard.jsp, doctor/dashboard.jsp, index.jsp, error.jsp, about.jsp, and
                        contact.jsp. Designed custom UI pages for dashboard and public modules.</p>
                    <div class="team-social">

                    </div>
                </details>
            </div>
        </div>

    </div>
</section>

<!-- Footer -->
<%@ include file="/includes/footer.jsp" %>

</body>
</html>