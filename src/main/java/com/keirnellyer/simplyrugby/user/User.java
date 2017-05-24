package com.keirnellyer.simplyrugby.user;

import com.keirnellyer.simplyrugby.navbar.Navigation;

import java.util.Optional;

/**
 * A user which can log in and use the site in some form.
 */
public abstract class User {
    private final String username;
    private String password = null;

    private Navigation navigation = null;

    /**
     * Constructs a new instance.
     *
     * @param username the username
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password, which may not be present.
     *
     * Some accounts (such as the default guest account) do not have a password.
     *
     * @return the password
     */
    public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    }

    /**
     * Sets the password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the navigation.
     *
     * @return the navigation
     */
    public Navigation getNavigation() {
        return navigation;
    }

    /**
     * Sets the navigation.
     *
     * @param navigation the navigation
     */
    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", navigation=" + navigation +
                '}';
    }
}
