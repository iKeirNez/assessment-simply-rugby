package com.keirnellyer.simplyrugby.util;

import com.keirnellyer.simplyrugby.user.Administrator;
import com.keirnellyer.simplyrugby.user.Member;
import com.keirnellyer.simplyrugby.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SessionUtil {
    public static boolean isAuthenticated(HttpSession session) {
        return session != null && session.getAttribute("user") != null;
    }

    public static boolean isAdministrator(User user) {
        return user instanceof Administrator;
    }

    public static boolean isMember(User user) {
        return user instanceof Member;
    }

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
