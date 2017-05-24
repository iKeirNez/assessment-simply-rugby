package com.keirnellyer.simplyrugby.user;

/**
 * A junior member.
 */
public class JuniorMember extends Member {

    /**
     * Constructs a new instance.
     *
     * @param username the username
     */
    public JuniorMember(String username) {
        super(username);
    }

    @Override
    public String toString() {
        return "JuniorMember{} " + super.toString();
    }
}
