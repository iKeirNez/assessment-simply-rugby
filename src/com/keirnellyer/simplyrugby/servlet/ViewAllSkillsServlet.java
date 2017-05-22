package com.keirnellyer.simplyrugby.servlet;

import com.keirnellyer.simplyrugby.exception.UserException;
import com.keirnellyer.simplyrugby.repository.UserRepository;
import com.keirnellyer.simplyrugby.user.Member;
import com.keirnellyer.simplyrugby.util.TargetableRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles displaying of the skills tracking table used by {@link com.keirnellyer.simplyrugby.user.Guest} accounts.
 */
@WebServlet("/guest/skills")
public class ViewAllSkillsServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();
        TargetableRequest<Member> targetable = new TargetableRequest<>(Member.class, userRepository, req);

        try {
            targetable.getTargetUser().ifPresent(member -> req.setAttribute("targetUser", member));
        } catch (UserException e) {
            errors.put("user", e.getMessage());
        }

        req.setAttribute("availableTargets", targetable.getAvailableTargets());
        req.getRequestDispatcher("/WEB-INF/skills_guest.jsp").forward(req, resp);
    }
}
