package com.keirnellyer.simplyrugby.servlet;

import com.keirnellyer.simplyrugby.skill.Skill;
import com.keirnellyer.simplyrugby.skill.SkillCategory;
import com.keirnellyer.simplyrugby.user.Member;
import com.keirnellyer.simplyrugby.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Handles editing of user skills.
 */
@WebServlet("/admin/edit_skills")
public class EditSkillsServlet extends TargetableServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        req.getRequestDispatcher("/WEB-INF/edit_skills.jsp").forward(req, resp);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        Map<String, String> errors = (Map<String, String>) req.getAttribute("errors");
        User targetUser = (User) req.getAttribute("targetUser");

        if (targetUser != null && targetUser instanceof Member) {
            Member targetMember = (Member) targetUser;

            for (SkillCategory category : targetMember.getSkills()) {
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
        }

        req.setAttribute("errors", errors);
        req.getRequestDispatcher("/WEB-INF/edit_skills.jsp").forward(req, resp);
    }

    @Override
    protected boolean isApplicableTarget(User user) {
        return user instanceof Member;
    }
}
