package com.keirnellyer.simplyrugby.util;

import com.keirnellyer.simplyrugby.exception.UserException;
import com.keirnellyer.simplyrugby.repository.UserRepository;
import com.keirnellyer.simplyrugby.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class for servlets which are target driven.
 *
 * Allows fetching of available targets and current target.
 */
public class TargetableRequest<T extends User> {
    private final Class<? extends T> clazz;
    private final UserRepository userRepository;
    private final HttpServletRequest request;

    /**
     * Constructs a new instance.
     *
     * @param clazz the class type
     * @param userRepository the user repository
     * @param request the request
     */
    public TargetableRequest(Class<? extends T> clazz, UserRepository userRepository, HttpServletRequest request) {
        this.clazz = clazz;
        this.userRepository = userRepository;
        this.request = request;
    }

    /**
     * Gets the target user.
     *
     * Returns {@link Optional#empty()} when target is not set.
     *
     * @return the target user
     * @throws UserException if the user is not of type {@link T} or is not found
     */
    public Optional<T> getTargetUser() throws UserException {
        String targetStr = request.getParameter("target");

        if (targetStr != null && !targetStr.isEmpty()) {
            Optional<User> userOptional = userRepository.getByUsername(targetStr);

            if (userOptional.isPresent()) {
                User user = userOptional.get();

                if (clazz.isAssignableFrom(user.getClass())) {
                    // un-safe cast is safe
                    return Optional.of((T) user);
                } else {
                    throw new UserException("User is not of type: " + clazz.getName());
                }
            } else {
                throw new UserException("User not found.");
            }
        }

        return Optional.empty();
    }

    /**
     * Gets all available targets, backed by the provided repository.
     *
     * @return the available targets
     */
    public List<T> getAvailableTargets() {
        return userRepository.getAllUsers().stream()
                .filter(u -> clazz.isAssignableFrom(u.getClass()))
                // un-safe cast is safe
                .map(u -> (T) u)
                .collect(Collectors.toList());
    }
}
