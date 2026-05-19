<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.healthsync.model.Doctor" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Appointment - HealthSync</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<div class="dashboard-container">
    <h2 class="dashboard-title">Book Appointment</h2>
    <p class="dashboard-subtitle">
        Schedule an appointment with one of our doctors
    </p>

    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-error">
        ⚠ <%= request.getAttribute("error") %>
    </div>
    <% } %>

    <div class="form-card">
        <form action="${pageContext.request.contextPath}/patient/book-appointment"
              method="post">

            <div class="form-group">
                <label>Select Doctor <span class="required">*</span></label>
                <select name="doctorId" required onchange="loadDoctorInfo(this)">
                    <option value="">-- Select a Doctor --</option>
                    <%
                        List<Doctor> doctors =
                                (List<Doctor>) request.getAttribute("doctors");
                        if (doctors != null) {
                            for (Doctor d : doctors) {
                    %>
                    <option value="<%= d.getDoctorId() %>"
                            data-spec="<%= d.getSpecialization() %>"
                            data-days="<%= d.getAvailableDays() %>">
                        Dr. <%= d.getFullName() %>
                        — <%= d.getSpecialization() %>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <!-- Doctor Info Box -->
            <div id="doctorInfo" class="info-box" style="display:none;">
                <p><strong>Specialization:</strong>
                    <span id="docSpec"></span></p>
                <p><strong>Available Days:</strong>
                    <span id="docDays"></span></p>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Appointment Date <span class="required">*</span></label>
                    <input type="date"
                           name="appointmentDate"
                           min="<%= java.time.LocalDate.now().plusDays(1) %>"
                           required>
                </div>
                <div class="form-group">
                    <label>Appointment Time <span class="required">*</span></label>
                    <select name="appointmentTime" required>
                        <option value="">-- Select Time --</option>
                        <option value="09:00">09:00 AM</option>
                        <option value="09:30">09:30 AM</option>
                        <option value="10:00">10:00 AM</option>
                        <option value="10:30">10:30 AM</option>
                        <option value="11:00">11:00 AM</option>
                        <option value="11:30">11:30 AM</option>
                        <option value="14:00">02:00 PM</option>
                        <option value="14:30">02:30 PM</option>
                        <option value="15:00">03:00 PM</option>
                        <option value="15:30">03:30 PM</option>
                        <option value="16:00">04:00 PM</option>
                        <option value="16:30">04:30 PM</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label>Reason for Visit</label>
                <textarea name="reason"
                          rows="3"
                          placeholder="Describe your symptoms or reason for visit">
                </textarea>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">
                    Confirm Booking
                </button>
                <a href="${pageContext.request.contextPath}/patient/dashboard"
                   class="btn btn-outline">Cancel</a>
            </div>

        </form>
    </div>
</div>

<%@ include file="/includes/footer.jsp" %>

<script>
    function loadDoctorInfo(select) {
        var option = select.options[select.selectedIndex];
        var spec  = option.getAttribute('data-spec');
        var days  = option.getAttribute('data-days');
        var box   = document.getElementById('doctorInfo');
        if (spec) {
            document.getElementById('docSpec').textContent = spec;
            document.getElementById('docDays').textContent = days || 'Not specified';
            box.style.display = 'block';
        } else {
            box.style.display = 'none';
        }
    }
</script>

</body>
</html>