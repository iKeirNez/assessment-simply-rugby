package com.keirnellyer.simplyrugby.repository;

import com.keirnellyer.simplyrugby.user.Administrator;
import com.keirnellyer.simplyrugby.user.Member;
import com.keirnellyer.simplyrugby.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private final List<User> users = new ArrayList<>();

    {
        initializeUsers();
    }

    public void initializeUsers() {
        Administrator admin = new Administrator("admin");
        admin.setPassword("admin");
        users.add(admin);

        Member jSmith = new Member("jsmith");
        jSmith.setPassword("password");
        jSmith.setFirstName("John");
        jSmith.setLastName("Smith");
        users.add(jSmith);
    }

    public void register(User user) {
        users.add(user);
    }

    public boolean exists(String username) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }

    public Optional<User> findByCredentials(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .filter(user -> user.getPassword().equals(password))
                .findFirst();
    }
}
