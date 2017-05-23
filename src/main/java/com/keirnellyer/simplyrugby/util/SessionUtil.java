package com.keirnellyer.simplyrugby.util;

import com.keirnellyer.simplyrugby.user.Administrator;
import com.keirnellyer.simplyrugby.user.Guest;
import com.keirnellyer.simplyrugby.user.Member;
import com.keirnellyer.simplyrugby.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Some utility classes to aid in general session management.
 */
public class SessionUtil {
    /**
     * Checks if the client is authenticated.
     *
     * @param session the session
     * @return true if authenticated, false otherwise
     */
    public static boolean isAuthenticated(HttpSession session) {
        return session != null && session.getAttribute("user") != null;
    }

    /**
     * Checks if the user is an {@link Administrator}.
     *
     * @param user the user
     * @return true if admin, false otherwise
     */
    public static boolean isAdministrator(User user) {
        return user instanceof Administrator;
    }

    /**
     * Checks if the user is a {@link Member}.
     *
     * @param user the user
     * @return true if member, false otherwise
     */
    public static boolean isMember(User user) {
        return user instanceof Member;
    }

    /**
     * Checks if the user is a {@link Guest}.
     *
     * @param user the user
     * @return true if guest, false otherwise
     */
    public static boolean isGuest(User user) {
        return user instanceof Guest;
    }

    /**
     * Redirects the client to the login page with their current page passed as a parameter.
     *
     * This allows for the user to be redirected back to the original page they were viewing before their session
     * timed out.
     *
     * @param request the request
     * @param response the response
     * @throws IOException if an input or output exception occurs
     */
    public static void redirectLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // store current page in GET parameters to allow application to redirect back
        // once authenticated has completed
        String redirect = request.getContextPath() + "/login?redirect_to=" + getFromString(request);
        response.sendRedirect(redirect);
    }

    private static String getFromString(HttpServletRequest request) throws UnsupportedEncodingException {
        String from = request.getRequestURI();

        if (request.getQueryString() != null) {
            from += "?" + request.getQueryString();
        }

        return URLEncoder.encode(from, "UTF-8");
    }
}
