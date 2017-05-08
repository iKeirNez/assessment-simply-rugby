package com.keirnellyer.simplyrugby.listener;

import com.keirnellyer.simplyrugby.user.*;

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

            if (user instanceof JuniorMember) {
                Member member = (Member) user;
                displayName = member.getFirstName();
                userHome = "/member";

                // only senior members can edit profile
                session.setAttribute("canEditProfile", user instanceof SeniorMember);
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
