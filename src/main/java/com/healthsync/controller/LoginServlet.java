package com.healthsync.controller;

import com.healthsync.dao.UserDAO;
import com.healthsync.model.User;
import com.healthsync.util.PasswordUtil;
import com.healthsync.util.ValidationUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validation
        if (ValidationUtil.isEmpty(email) || ValidationUtil.isEmpty(password)) {
            request.setAttribute("error", "Email and password are required.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if (!ValidationUtil.isValidEmail(email)) {
            request.setAttribute("error", "Please enter a valid email address.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // Check user in database
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByEmail(email);

        if (user == null) {
            request.setAttribute("error", "No account found with this email.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // Verify password
        if (!PasswordUtil.verify(password, user.getPassword())) {
            request.setAttribute("error", "Incorrect password. Please try again.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // Check account status
        if ("pending".equals(user.getStatus())) {
            request.setAttribute("error",
                    "Your account is pending approval. Please wait for admin approval.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if ("rejected".equals(user.getStatus())) {
            request.setAttribute("error",
                    "Your account has been rejected. Contact admin for support.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // Login success — create session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("userName", user.getFullName());
        session.setAttribute("role", user.getRole());
        session.setMaxInactiveInterval(30 * 60); // 30 minutes

        // Cookie for remember me
        Cookie cookie = new Cookie("userEmail", email);
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
        response.addCookie(cookie);

        // Redirect based on role
        String role = user.getRole();
        if ("admin".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        } else if ("doctor".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/doctor/dashboard");
        } else {
            response.sendRedirect(request.getContextPath() + "/patient/dashboard");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // If already logged in redirect away from login page
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            String role = (String) session.getAttribute("role");
            if ("admin".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            } else if ("doctor".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/doctor/dashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/patient/dashboard");
            }
            return;
        }
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}