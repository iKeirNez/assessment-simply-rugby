package com.keirnellyer.simplyrugby.servlet;

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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/admin/edit_skills")
public class EditSkillsServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member targetUser = getTargetUser(req);

        if (targetUser != null) {
            req.setAttribute("targetUser", targetUser);
        }

        req.setAttribute("availableTargets", getAvailableTargets());
        req.getRequestDispatcher("/WEB-INF/edit_skills.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();
        Member targetUser = getTargetUser(req);

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

    private List<Member> getAvailableTargets() {
        return userRepository.getAllUsers().stream()
                .filter(u -> u instanceof Member)
                .map(u -> (Member) u)
                .collect(Collectors.toList());
    }

    private Member getTargetUser(HttpServletRequest req) {
        String targetStr = req.getParameter("target");

        // TODO throw exceptions
        if (targetStr != null) {
            Optional<User> userOptional = userRepository.getByUsername(targetStr);

            if (userOptional.isPresent()) {
                User user = userOptional.get();

                if (user instanceof Member) {
                    return (Member) user;
                }
            }
        }

        return null;
    }
}
