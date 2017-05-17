package com.keirnellyer.simplyrugby.tag;

import com.keirnellyer.simplyrugby.user.Administrator;
import com.keirnellyer.simplyrugby.user.Guest;
import com.keirnellyer.simplyrugby.user.Member;
import com.keirnellyer.simplyrugby.user.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class DisplayNameTag extends SimpleTagSupport {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void doTag() throws JspException, IOException {
        String displayName;

        if (user != null) {
            if (user instanceof Member) {
                Member member = (Member) user;
                displayName = member.getFirstName();
            } else if (user instanceof Guest) {
                displayName = "Guest";
            } else if (user instanceof Administrator) {
                displayName = "Administrator";
            } else {
                displayName = user.getUsername();
            }
        } else {
            displayName = "null";
        }

        getJspContext().getOut().write(displayName);
    }
}
