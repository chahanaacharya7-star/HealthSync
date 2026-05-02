<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
    String currentRole = "";
    String currentUser = "";
    HttpSession currentSession = request.getSession(false);
    if (currentSession != null) {
        currentRole = (String) currentSession.getAttribute("role");
        currentUser = (String) currentSession.getAttribute("userName");
        if (currentRole == null) currentRole = "";
        if (currentUser == null) currentUser = "";
    }
    String path = request.getContextPath();
%>

<header class="header">
    <nav class="navbar">

        <!-- Brand Logo -->
        <a href="<%= path %>/index.jsp" class="nav-brand">
            🏥 HealthSync
        </a>

        <!-- Nav Links -->
        <ul class="nav-links">

            <% if (currentRole.isEmpty()) { %>
            <!-- Public Links (not logged in) -->
            <li><a href="<%= path %>/index.jsp">Home</a></li>
            <li><a href="<%= path %>/about.jsp">About</a></li>
            <li><a href="<%= path %>/contact.jsp">Contact</a></li>
            <li>
                <a href="<%= path %>/login.jsp" class="nav-btn">Login</a>
            </li>
            <li>
                <a href="<%= path %>/register.jsp"
                   class="nav-btn nav-btn-outline">Register</a>
            </li>

            <% } else if ("admin".equals(currentRole)) { %>
            <!-- Admin Links -->
            <li>
                <a href="<%= path %>/admin/dashboard">Dashboard</a>
            </li>
            <li>
                <a href="<%= path %>/admin/manage-patients">Patients</a>
            </li>
            <li>
                <a href="<%= path %>/admin/manage-doctors">Doctors</a>
            </li>
            <li>
                <a href="<%= path %>/admin/manage-appointments">Appointments</a>
            </li>
            <li class="nav-user">👤 <%= currentUser %></li>
            <li>
                <a href="<%= path %>/logout" class="nav-btn nav-btn-danger">
                    Logout
                </a>
            </li>

            <% } else if ("doctor".equals(currentRole)) { %>
            <!-- Doctor Links -->
            <li>
                <a href="<%= path %>/doctor/dashboard">Dashboard</a>
            </li>
            <li>
                <a href="<%= path %>/doctor/appointments">Appointments</a>
            </li>
            <li>
                <a href="<%= path %>/doctor/patients">My Patients</a>
            </li>
            <li class="nav-user">👨‍⚕️ <%= currentUser %></li>
            <li>
                <a href="<%= path %>/logout" class="nav-btn nav-btn-danger">
                    Logout
                </a>
            </li>

            <% } else if ("patient".equals(currentRole)) { %>
            <!-- Patient Links -->
            <li>
                <a href="<%= path %>/patient/dashboard">Dashboard</a>
            </li>
            <li>
                <a href="<%= path %>/patient/book-appointment">
                    Book Appointment
                </a>
            </li>
            <li>
                <a href="<%= path %>/patient/my-appointments">
                    My Appointments
                </a>
            </li>
            <li class="nav-user">🧑 <%= currentUser %></li>
            <li>
                <a href="<%= path %>/logout" class="nav-btn nav-btn-danger">
                    Logout
                </a>
            </li>
            <% } %>

        </ul>

        <!-- Mobile Hamburger -->
        <div class="hamburger" onclick="toggleMenu()">
            <span></span>
            <span></span>
            <span></span>
        </div>

    </nav>
</header>

<script>
    function toggleMenu() {
        document.querySelector('.nav-links').classList.toggle('nav-open');
    }
</script>