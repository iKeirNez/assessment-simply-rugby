package com.keirnellyer.simplyrugby.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.keirnellyer.simplyrugby.util.SessionUtil.*;

@WebServlet("")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        resp.sendRedirect(req.getContextPath() + getDestination(session));
    }

    private String getDestination(HttpSession session) {
        if (isAuthenticated(session)) {
            return (String) session.getAttribute("userHome");
        } else {
            return "/login";
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
