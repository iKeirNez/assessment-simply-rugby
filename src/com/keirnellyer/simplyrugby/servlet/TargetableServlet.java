package com.keirnellyer.simplyrugby.servlet;

import com.keirnellyer.simplyrugby.exception.UserException;
import com.keirnellyer.simplyrugby.repository.UserRepository;
import com.keirnellyer.simplyrugby.user.Member;
import com.keirnellyer.simplyrugby.user.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Super-class for sharing common code between servlets which are target driven.
 *
 * Populates the "availableTargets" attribute with applicable (defined by
 * {@link TargetableServlet#isApplicableTarget(User)}) {@link User} instances.
 *
 * Fetches the "target" parameter from the request and attempts to match it to a {@link User} instance, if successful
 * the user is placed in the "targetUser" attribute.
 *
 * If the parameter is set and the user is found, then the "targetUser" attribute will be set to the {@link User}
 * instance. Otherwise, if the parameter is not set or the user is not found, then the "targetUser" will be null and
 * an appropriate error will be placed in the "errors" map.
 */
public abstract class TargetableServlet extends HttpServlet {
    protected UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();
        User targetUser = null;

        try {
            targetUser = getTargetUser(req);
        } catch (UserException e) {
            errors.put("target", e.getMessage());
        }

        if (targetUser != null) {
            req.setAttribute("targetUser", targetUser);
        }

        req.setAttribute("availableTargets", getAvailableTargets());
        req.setAttribute("errors", errors);
    }

    protected List<User> getAvailableTargets() {
        return userRepository.getAllUsers().stream()
                .filter(this::isApplicableTarget)
                .collect(Collectors.toList());
    }

    protected User getTargetUser(HttpServletRequest req) throws UserException {
        String targetStr = req.getParameter("target");

        if (targetStr != null) {
            Optional<User> userOptional = userRepository.getByUsername(targetStr);

            if (userOptional.isPresent()) {
                User user = userOptional.get();

                if (isApplicableTarget(user)) {
                    return user;
                } else {
                    throw new UserException("Target user is not a Member.");
                }
            } else {
                throw new UserException("Target user not found.");
            }
        }

        return null;
    }

    protected abstract boolean isApplicableTarget(User user);
}
