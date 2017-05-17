package com.keirnellyer.simplyrugby.user;

import com.keirnellyer.simplyrugby.navbar.Navigation;

import java.util.Optional;

public abstract class User {
    private final String username;
    private String password = null;

    private Navigation navigation = null;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }
}
