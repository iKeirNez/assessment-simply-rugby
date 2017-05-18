package com.keirnellyer.simplyrugby.servlet;

import com.keirnellyer.simplyrugby.repository.UserRepository;
import com.keirnellyer.simplyrugby.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = ((UserRepository) getServletContext().getAttribute("userRepository"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String guestStr = req.getParameter("guest");

        if (guestStr != null && !guestStr.isEmpty()) {
            boolean guest = Boolean.parseBoolean(guestStr);

            if (guest) {
                User defaultGuest = userRepository.getDefaultGuest();
                login(req, resp, defaultGuest);
                return;
            }
        }

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Map<String, String> errors = new HashMap<>();

        if (username == null || username.isEmpty()) {
            errors.put("username", "Please enter a username.");
        }

        if (errors.isEmpty()) {
            Optional<User> userOptional = userRepository.getByCredentials(username, password);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                login(req, resp, user);
                return;
            } else {
                errors.put("overall", "Incorrect username or password.");
            }
        }

        // as some users do not require a password, this check must be done last
        if (password == null || password.isEmpty()) {
            errors.put("password", "Please enter a password.");
        }

        req.setAttribute("errors", errors);
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp, User user) throws IOException {
        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        String homeLocation = (String) session.getAttribute("userHome");
        postLoginRedirect(req, resp, homeLocation, req.getParameter("redirect_to"));
    }

    private void postLoginRedirect(HttpServletRequest req, HttpServletResponse resp, String userHome, String redirectParam)
            throws IOException {
        if (redirectParam != null && !redirectParam.isEmpty()) {
            String location = URLDecoder.decode(redirectParam, "UTF-8");
            resp.sendRedirect(location);
        } else {
            resp.sendRedirect(req.getContextPath() + userHome);
        }
    }
}
