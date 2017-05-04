package com.keirnellyer.simplyrugby.user;

import com.keirnellyer.simplyrugby.navbar.Navigation;
import com.keirnellyer.simplyrugby.navbar.NavigationElement;

public class Administrator extends User {
    public Administrator(String username) {
        super(username);

        initializeNavBar();
    }

    private void initializeNavBar() {
        Navigation navigation = new Navigation();

        NavigationElement first = new NavigationElement("Admin Thing 1", "/");
        navigation.addItem(first);

        NavigationElement second = new NavigationElement("Admin Thing 2", "/");
        navigation.addItem(second);

        setNavigation(navigation);
    }
}
