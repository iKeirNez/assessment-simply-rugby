package com.keirnellyer.simplyrugby.listener;

import com.keirnellyer.simplyrugby.user.Administrator;
import com.keirnellyer.simplyrugby.user.Member;
import com.keirnellyer.simplyrugby.user.User;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class UserAttributeInjector implements HttpSessionAttributeListener {
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        attributeAdded(event);
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (event.getName().equals("user")) {
            HttpSession session = event.getSession();
            User user = (User) event.getValue();
            String displayName;
            String userHome;

            if (user instanceof Member) {
                Member member = (Member) user;
                displayName = member.getFirstName();
                userHome = "/member";
                session.setAttribute("canEditProfile", true);
            } else {
                Administrator administrator = (Administrator) user;
                userHome = "/admin";
                displayName = "Administrator";
            }

            session.setAttribute("userDisplayName", displayName);
            session.setAttribute("userHome", userHome);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }
}
