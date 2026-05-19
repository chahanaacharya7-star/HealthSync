<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.healthsync.model.Appointment" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Appointments - HealthSync</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<div class="dashboard-container">
    <div class="table-header">
        <div>
            <h2 class="dashboard-title">My Appointments</h2>
            <p class="dashboard-subtitle">View and manage your appointments</p>
        </div>
        <a href="${pageContext.request.contextPath}/patient/book-appointment"
           class="btn btn-primary">+ Book New</a>
    </div>

    <% if (request.getParameter("success") != null) { %>
    <div class="alert alert-success">
        ✔ <%= request.getParameter("success").replace("+", " ") %>
    </div>
    <% } %>

    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>#</th>
                <th>Doctor</th>
                <th>Specialization</th>
                <th>Date</th>
                <th>Time</th>
                <th>Reason</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Appointment> appointments =
                        (List<Appointment>) request.getAttribute("appointments");
                if (appointments == null || appointments.isEmpty()) {
            %>
            <tr>
                <td colspan="8" style="text-align:center; padding:30px;">
                    No appointments found.
                    <a href="${pageContext.request.contextPath}
                               /patient/book-appointment">
                        Book one now
                    </a>
                </td>
            </tr>
            <%
            } else {
                int i = 1;
                for (Appointment a : appointments) {
            %>
            <tr>
                <td><%= i++ %></td>
                <td>Dr. <%= a.getDoctorName() %></td>
                <td><%= a.getDoctorSpecialization() %></td>
                <td><%= a.getAppointmentDate() %></td>
                <td><%= a.getAppointmentTime() %></td>
                <td><%= a.getReason() != null ?
                        a.getReason() : "-" %></td>
                <td>
                            <span class="badge
                                <%= "pending".equals(a.getStatus()) ?
                                    "badge-warning" :
                                    "confirmed".equals(a.getStatus()) ?
                                    "badge-success" :
                                    "cancelled".equals(a.getStatus()) ?
                                    "badge-danger" : "badge-info" %>">
                                <%= a.getStatus() %>
                            </span>
                </td>
                <td>
                    <% if ("pending".equals(a.getStatus())) { %>
                    <a href="${pageContext.request.contextPath}
                                   /patient/my-appointments?action=cancel&id=
                                   <%= a.getAppointmentId() %>"
                       class="btn btn-danger btn-sm"
                       onclick="return confirm(
                                       'Cancel this appointment?')">
                        Cancel
                    </a>
                    <% } else { %>
                    <span style="color:#999;">—</span>
                    <% } %>
                </td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/includes/footer.jsp" %>

</body>
</html>