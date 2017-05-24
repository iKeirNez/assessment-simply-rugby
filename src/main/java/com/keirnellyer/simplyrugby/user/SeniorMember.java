package com.keirnellyer.simplyrugby.user;

/**
 * A senior member.
 */
public class SeniorMember extends Member {

    /**
     * Constructs a new instance.
     *
     * @param username the username
     */
    public SeniorMember(String username) {
        super(username);
    }

    @Override
    public String toString() {
        return "SeniorMember{} " + super.toString();
    }
}
