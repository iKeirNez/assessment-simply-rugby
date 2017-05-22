package com.keirnellyer.simplyrugby.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles displaying of the skills tracking table used by {@link com.keirnellyer.simplyrugby.user.Guest} accounts.
 */
@WebServlet("/guest/skills")
public class ViewAllSkillsServlet extends TargetableServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        req.getRequestDispatcher("/WEB-INF/skills_guest.jsp").forward(req, resp);
    }
}
