package com.keirnellyer.simplyrugby.user;

import com.keirnellyer.simplyrugby.navbar.Navigation;
import com.keirnellyer.simplyrugby.navbar.NavigationElement;

/**
 * Top level user type with super-user privileges.
 */
public class Administrator extends User {
    public Administrator(String username) {
        super(username);

        initializeNavBar();
    }

    private void initializeNavBar() {
        Navigation navigation = new Navigation();

        NavigationElement first = new NavigationElement("Edit Skills", "/admin/edit_skills");
        navigation.addItem(first);

        setNavigation(navigation);
    }
}
