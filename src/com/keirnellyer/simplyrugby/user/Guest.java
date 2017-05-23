package com.keirnellyer.simplyrugby.user;

/**
 * A guest type of user with read-only permissions.
 */
public class Guest extends User {

    /**
     * Constructs a new instance.
     *
     * @param username the username
     */
    public Guest(String username) {
        super(username);
    }

    @Override
    public String toString() {
        return "Guest{} " + super.toString();
    }
}
