package com.keirnellyer.simplyrugby.servlet;

import com.keirnellyer.simplyrugby.repository.UserRepository;
import com.keirnellyer.simplyrugby.user.JuniorMember;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = ((UserRepository) getServletContext().getAttribute("user_repository"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errors", Collections.emptyMap());
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String password = req.getParameter("password");
        Map<String, String> errors = new HashMap<>();

        if (username == null || username.isEmpty()) {
            errors.put("username", "Please enter a username.");
        } else if (userRepository.exists(username)) {
            errors.put("username", "That username is already in use.");
        }

        if (firstName == null || firstName.isEmpty()) {
            errors.put("first_name", "Please enter your first name.");
        }

        if (lastName == null || lastName.isEmpty()) {
            errors.put("last_name", "Please enter your last name.");
        }

        if (password == null || password.isEmpty()) {
            errors.put("password", "Please enter a password.");
        }

        if (errors.isEmpty()) {
            JuniorMember member = new JuniorMember(username);
            member.setPassword(password);
            member.setFirstName(firstName);
            member.setLastName(lastName);
            userRepository.register(member);

            // TODO display new login message
            String redirectPath = req.getContextPath() + "/login";

            String queryString = req.getQueryString();
            if (queryString != null && !queryString.isEmpty()) {
                redirectPath += "?" + queryString;
            }

            resp.sendRedirect(redirectPath);
            return;
        }

        // TODO view error messages in JSP
        req.setAttribute("errors", errors);
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }
}
