package com.keirnellyer.simplyrugby.servlet;

import com.keirnellyer.simplyrugby.exception.UserException;
import com.keirnellyer.simplyrugby.repository.UserRepository;
import com.keirnellyer.simplyrugby.user.Administrator;
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
import java.util.regex.Pattern;

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
        Administrator administrator = (Administrator) req.getSession(false).getAttribute("user");
        TargetableRequest<Member> targetable = new TargetableRequest<>(Member.class, userRepository, req);
        Optional<Member> targetOptional = Optional.empty();

        try {
            targetOptional = targetable.getTargetUser();
        } catch (UserException e) {
            errors.put("target", e.getMessage());
        }

        if (targetOptional.isPresent()) {
            Member target = targetOptional.get();

            for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
                String key = entry.getKey();
                String[] values = entry.getValue();

                if (key.startsWith("skill_")) {
                    String subKey = key.substring("skill_".length(), key.length());
                    String value = value(key, values);

                    handleSkillUpdate(administrator, target, subKey, value);
                } else if (key.startsWith("comment_")) {
                    String category = key.substring("comment_".length(), key.length());
                    String value = value(key, values);

                    administrator.updateMemberComment(target, category, value);
                }
            }

            req.setAttribute("targetUser", target);
        }

        req.setAttribute("availableTargets", targetable.getAvailableTargets());
        req.setAttribute("errors", errors);
        req.getRequestDispatcher("/WEB-INF/edit_skills.jsp").forward(req, resp);
    }

    private String value(String key, String[] values) {
        if (values.length > 1) {
            log("Multiple values for: " + key + "\nDefaulting to value index 0.");
        }

        return values[0];
    }

    private boolean handleSkillUpdate(Administrator administrator, Member target, String subKey, String value) throws
            NumberFormatException {
        String[] split = subKey.split(Pattern.quote("_"));

        if (split.length == 2) {
            String category = split[0];
            String skill = split[1];
            // TODO rethrow?
            int intValue = Integer.parseInt(value);

            administrator.updateMemberSkill(target, category, skill, intValue);
            return true;
        }

        return false;
    }
}
