package com.keirnellyer.simplyrugby.servlet;

import com.keirnellyer.simplyrugby.exception.UserException;
import com.keirnellyer.simplyrugby.repository.UserRepository;
import com.keirnellyer.simplyrugby.skill.Skill;
import com.keirnellyer.simplyrugby.skill.SkillCategory;
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
import java.util.Optional;

/**
 * Handles editing of user skills.
 */
@WebServlet("/admin/edit_skills")
public class EditSkillsServlet extends HttpServlet {
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
        req.setAttribute("errors", errors);
        req.getRequestDispatcher("/WEB-INF/edit_skills.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();
        TargetableRequest<Member> targetable = new TargetableRequest<>(Member.class, userRepository, req);
        Optional<Member> targetOptional = Optional.empty();

        try {
            targetOptional = targetable.getTargetUser();
        } catch (UserException e) {
            errors.put("user", e.getMessage());
        }

        if (targetOptional.isPresent()) {
            Member target = targetOptional.get();

            for (SkillCategory category : target.getSkills()) {
                for (Skill skill : category.getSkills()) {
                    String skillValueKey = category.getName() + "_" + skill.getName();
                    String skillValueStr = req.getParameter(skillValueKey);

                    try {
                        int skillValue = Integer.parseInt(skillValueStr);
                        skill.setValue(skillValue);
                    } catch (NumberFormatException e) {
                        errors.put(skillValueKey, "Invalid skill value: " + skillValueStr);
                    }
                }

                String categoryComment = req.getParameter(category.getName() + "_cmnt");
                category.setComment(categoryComment);
            }

            req.setAttribute("targetUser", target);
        }

        req.setAttribute("availableTargets", targetable.getAvailableTargets());
        req.setAttribute("errors", errors);
        req.getRequestDispatcher("/WEB-INF/edit_skills.jsp").forward(req, resp);
    }
}
