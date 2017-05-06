package com.keirnellyer.simplyrugby.servlet;

import com.keirnellyer.simplyrugby.user.JuniorMember;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/member/edit_profile")
public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/edit_profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String password = req.getParameter("password");
        Map<String, String> errors = new HashMap<>();

        if (firstName == null || firstName.isEmpty()) {
            errors.put("first_name", "Please enter your first name.");
        }

        if (lastName == null || lastName.isEmpty()) {
            errors.put("last_name", "Please enter your last name.");
        }

        if (errors.isEmpty()) {
            JuniorMember member = (JuniorMember) req.getSession(false).getAttribute("user");
            member.setFirstName(firstName);
            member.setLastName(lastName);

            if (password != null && !password.isEmpty()) {
                member.setPassword(password);
            }

            req.setAttribute("updated", true);
            req.getRequestDispatcher("/WEB-INF/edit_profile.jsp").forward(req, resp);
        }
    }
}
