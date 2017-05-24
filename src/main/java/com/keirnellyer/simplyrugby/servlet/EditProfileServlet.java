package com.keirnellyer.simplyrugby.servlet;

import com.keirnellyer.simplyrugby.user.Member;
import com.keirnellyer.simplyrugby.user.SeniorMember;
import com.keirnellyer.simplyrugby.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A servlet which manages the editing of a member profile.
 */
@WebServlet("/user/edit_profile")
public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        req.setAttribute("canEditPersonalDetails", canEditPersonalDetails(user));
        req.getRequestDispatcher("/WEB-INF/edit_profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        boolean canEditProfile = canEditPersonalDetails(user);
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String password = req.getParameter("password");
        Map<String, String> errors = new HashMap<>();

        if (canEditProfile) {
            if (firstName == null || firstName.isEmpty()) {
                errors.put("first_name", "Please enter your first name.");
            }

            if (lastName == null || lastName.isEmpty()) {
                errors.put("last_name", "Please enter your last name.");
            }
        }

        if (errors.isEmpty()) {
            if (canEditProfile) {
                Member member = (Member) user;
                member.setFirstName(firstName);
                member.setLastName(lastName);
            }

            if (password != null && !password.isEmpty()) {
                user.setPassword(password);
            }

            req.setAttribute("updated", true);
        }

        req.setAttribute("canEditPersonalDetails", canEditProfile);
        req.setAttribute("errors", errors);
        req.getRequestDispatcher("/WEB-INF/edit_profile.jsp").forward(req, resp);
    }

    private boolean canEditPersonalDetails(User user) {
        return user instanceof SeniorMember;
    }
}
