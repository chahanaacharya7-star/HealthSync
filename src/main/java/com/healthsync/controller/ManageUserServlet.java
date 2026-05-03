package com.healthsync.controller;

import com.healthsync.dao.UserDAO;
import com.healthsync.model.User;
import com.healthsync.util.PasswordUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ManageUserServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "add":
                    showAddForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                default:
                    listUsers(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "insert":
                    insertUser(request, response);
                    break;
                case "update":
                    updateUser(request, response);
                    break;
                default:
                    response.sendRedirect("manage-users");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = userDAO.findAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/admin/manage-users.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/admin/add-user.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.findById(id);
        request.setAttribute("userToEdit", existingUser);
        request.getRequestDispatcher("/admin/edit-user.jsp").forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String status = request.getParameter("status");

        // Duplicate checks
        if (userDAO.emailExists(email)) {
            response.sendRedirect("manage-users?action=add&error=Email already in use");
            return;
        }
        if (userDAO.phoneExists(phone)) {
            response.sendRedirect("manage-users?action=add&error=Phone number already in use");
            return;
        }

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(PasswordUtil.encrypt(password));
        user.setRole(role);
        user.setStatus(status);

        if (userDAO.save(user)) {
            response.sendRedirect("manage-users?success=User added");
        } else {
            response.sendRedirect("manage-users?error=Failed to add user");
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        
        User user = userDAO.findById(userId);
        if (user != null) {
            user.setFullName(request.getParameter("fullName"));
            user.setEmail(request.getParameter("email"));
            user.setPhone(request.getParameter("phone"));
            user.setRole(request.getParameter("role"));
            user.setStatus(request.getParameter("status"));

            // Update password only if provided
            String newPassword = request.getParameter("password");
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                user.setPassword(PasswordUtil.encrypt(newPassword));
            }

            // Note: UserDAO.update only updates name, email, phone. 
            // I should update it to support role and status as well if needed.
            // For now, I'll update it to be more comprehensive.
            
            if (userDAO.update(user)) {
                response.sendRedirect("manage-users?success=User updated");
            } else {
                response.sendRedirect("manage-users?error=Failed to update user");
            }
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        // Prevent self-deletion
        HttpSession session = request.getSession();
        int currentAdminId = (int) session.getAttribute("userId");
        if (id == currentAdminId) {
            response.sendRedirect("manage-users?error=You cannot delete yourself");
            return;
        }

        if (userDAO.delete(id)) {
            response.sendRedirect("manage-users?success=User deleted");
        } else {
            response.sendRedirect("manage-users?error=Failed to delete user");
        }
    }
}
