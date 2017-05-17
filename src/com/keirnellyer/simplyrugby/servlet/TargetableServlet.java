package com.keirnellyer.simplyrugby.servlet;

import com.keirnellyer.simplyrugby.repository.UserRepository;
import com.keirnellyer.simplyrugby.user.Member;
import com.keirnellyer.simplyrugby.user.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TargetableServlet extends HttpServlet {
    protected UserRepository userRepository;

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
    }

    protected List<Member> getAvailableTargets() {
        return userRepository.getAllUsers().stream()
                .filter(u -> u instanceof Member)
                .map(u -> (Member) u)
                .collect(Collectors.toList());
    }

    protected Member getTargetUser(HttpServletRequest req) {
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
