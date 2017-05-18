package com.keirnellyer.simplyrugby.servlet;

import com.keirnellyer.simplyrugby.exception.UserException;
import com.keirnellyer.simplyrugby.repository.UserRepository;
import com.keirnellyer.simplyrugby.skill.Skill;
import com.keirnellyer.simplyrugby.skill.SkillCategory;
import com.keirnellyer.simplyrugby.user.Member;
import com.keirnellyer.simplyrugby.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/admin/edit_skills")
public class EditSkillsServlet extends TargetableServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        req.getRequestDispatcher("/WEB-INF/edit_skills.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();
        Member targetUser = null;

        try {
            targetUser = getTargetUser(req);
        } catch (UserException e) {
            errors.put("target", e.getMessage());
        }

        if (targetUser != null) {
            for (SkillCategory category : targetUser.getSkills()) {
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

                req.setAttribute("targetUser", targetUser);
            }
        }

        req.setAttribute("availableTargets", getAvailableTargets());
        req.setAttribute("errors", errors);
        req.getRequestDispatcher("/WEB-INF/edit_skills.jsp").forward(req, resp);
    }
}
