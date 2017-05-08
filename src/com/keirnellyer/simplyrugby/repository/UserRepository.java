package com.keirnellyer.simplyrugby.repository;

import com.keirnellyer.simplyrugby.skill.Skill;
import com.keirnellyer.simplyrugby.user.Administrator;
import com.keirnellyer.simplyrugby.user.JuniorMember;
import com.keirnellyer.simplyrugby.user.SeniorMember;
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

        List<Skill> defaultSkills = new ArrayList<>();
        defaultSkills.add(new Skill("Standard", 5));
        defaultSkills.add(new Skill("Spin", 1));
        defaultSkills.add(new Skill("Pop", 5));
        defaultSkills.add(new Skill("Front", 3));
        defaultSkills.add(new Skill("Rear", 5));
        defaultSkills.add(new Skill("Side", 4));
        defaultSkills.add(new Skill("Scrabble", 5));
        defaultSkills.add(new Skill("Drop", 5));
        defaultSkills.add(new Skill("Punt", 5));
        defaultSkills.add(new Skill("Grubber", 5));
        defaultSkills.add(new Skill("Goal", 2));

        JuniorMember jSmith = new JuniorMember("jsmith");
        jSmith.setPassword("password");
        jSmith.setFirstName("John");
        jSmith.setLastName("Smith");
        defaultSkills.forEach(jSmith::addSkill);
        users.add(jSmith);

        SeniorMember jAppleSeed = new SeniorMember("jappleseed");
        jAppleSeed.setPassword("password");
        jAppleSeed.setFirstName("John");
        jAppleSeed.setLastName("Appleseed");
        defaultSkills.forEach(jAppleSeed::addSkill);
        users.add(jAppleSeed);
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
