<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Appointment | HealthSync</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <main class="auth-container" style="background-color: #f0f4f8;">
        <div class="auth-box auth-box-wide">
            <div class="auth-header">
                <div class="auth-logo">📅</div>
                <h2>Book an Appointment</h2>
                <p>Choose a doctor and schedule your consultation.</p>
            </div>

            <form id="bookingForm" action="${pageContext.request.contextPath}/appointments" method="POST">
                <input type="hidden" name="patientId" value="${patient.patientId}">
                
                <div class="form-section">
                    <div class="form-section-title">Consultation Details</div>
                    
                    <div class="form-group">
                        <label for="doctorId">Select Doctor <span class="required">*</span></label>
                        <select name="doctorId" id="doctorId" required>
                            <option value="">-- Select a Doctor --</option>
                            <c:forEach var="doctor" items="${doctors}">
                                <option value="${doctor.doctorId}">Dr. ${doctor.fullName} (${doctor.specialization})</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="date">Appointment Date <span class="required">*</span></label>
                            <input type="date" name="date" id="date" required min="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
                        </div>
                        <div class="form-group">
                            <label for="time">Preferred Time <span class="required">*</span></label>
                            <input type="time" name="time" id="time" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="reason">Reason for Visit <span class="required">*</span></label>
                        <textarea name="reason" id="reason" rows="3" placeholder="Briefly describe your symptoms or reason for the visit..." required></textarea>
                    </div>

                    <div class="form-group">
                        <label for="notes">Additional Notes (Optional)</label>
                        <textarea name="notes" id="notes" rows="2" placeholder="Any other information for the doctor..."></textarea>
                    </div>
                </div>

                <div style="margin-top: 30px; display: flex; gap: 15px;">
                    <a href="${pageContext.request.contextPath}/patient/dashboard" class="btn btn-outline" style="flex: 1;">Cancel</a>
                    <button type="submit" class="btn btn-primary" style="flex: 2;">Confirm Booking</button>
                </div>
            </form>
        </div>
    </main>

    <jsp:include page="/includes/footer.jsp" />

    <script>
        document.getElementById('bookingForm').onsubmit = function(e) {
            e.preventDefault();
            
            const formData = new URLSearchParams(new FormData(this));
            
            fetch(this.action, {
                method: 'POST',
                body: formData,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            })
            .then(response => {
                if (response.ok) {
                    alert('Appointment booked successfully!');
                    window.location.href = '${pageContext.request.contextPath}/patient/my-appointments';
                } else {
                    alert('Failed to book appointment. Please check your details.');
                }
            });
        };
    </script>

</body>
</html>
