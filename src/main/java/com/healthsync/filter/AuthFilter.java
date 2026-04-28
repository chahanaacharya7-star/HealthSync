package com.healthsync.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        String requestURI = request.getRequestURI();
        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        if (!loggedIn) {
            // Not logged in — redirect to login
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // Role-based access check
        String role = (String) session.getAttribute("role");

        if (requestURI.contains("/admin/") && !"admin".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        if (requestURI.contains("/doctor/") && !"doctor".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        if (requestURI.contains("/patient/") && !"patient".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        chain.doFilter(req, res);
    }
}