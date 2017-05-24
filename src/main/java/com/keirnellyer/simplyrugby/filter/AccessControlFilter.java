package com.keirnellyer.simplyrugby.filter;

import com.keirnellyer.simplyrugby.user.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.keirnellyer.simplyrugby.util.SessionUtil.*;

/**
 * Prevents users accessing content they do not have permission to view.
 * Shows an 'access denied' message instead.
 */
@WebFilter({"/user/*", "/member/*", "/admin/*", "/guest/*"})
public class AccessControlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (isAuthenticated(session)) {
            User user = (User) session.getAttribute("user");
            String servletPath = httpRequest.getServletPath();

            if (servletPath.startsWith("/admin") && !isAdministrator(user)) {
                httpRequest.getRequestDispatcher("/WEB-INF/error/access-denied.jsp").forward(request, response);
            } else if (servletPath.startsWith("/member") && !isMember(user)) {
                httpRequest.setAttribute("customMessage", "Try using a member account instead.");
                httpRequest.getRequestDispatcher("/WEB-INF/error/access-denied.jsp").forward(request, response);
            } else if (servletPath.startsWith("/guest") && !isGuest(user)) {
                httpRequest.setAttribute("customMessage", "Guest users cannot do this.");
                httpRequest.getRequestDispatcher("/WEB-INF/error/access-denied.jsp").forward(request, response);
            } else {
                chain.doFilter(httpRequest, httpResponse);
            }
        } else {
            redirectLogin(httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
