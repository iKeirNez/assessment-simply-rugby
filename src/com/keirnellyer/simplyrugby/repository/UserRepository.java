package com.keirnellyer.simplyrugby.repository;

import com.keirnellyer.simplyrugby.skill.Skill;
import com.keirnellyer.simplyrugby.skill.SkillCategory;
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

        JuniorMember jSmith = new JuniorMember("jsmith");
        jSmith.setPassword("password");
        jSmith.setFirstName("John");
        jSmith.setLastName("Smith");
        getDefaultSkills().forEach(jSmith::addSkill);
        users.add(jSmith);

        SeniorMember gMullen = new SeniorMember("ged");
        gMullen.setPassword("password");
        gMullen.setFirstName("Ged");
        gMullen.setLastName("Mullen");
        getDefaultSkills().forEach(gMullen::addSkill);
        users.add(gMullen);
    }

    private List<SkillCategory> getDefaultSkills() {
        List<SkillCategory> defaultSkills = new ArrayList<>();
        SkillCategory passing = new SkillCategory("Passing");
        passing.addSkill(new Skill("Standard", 5));
        passing.addSkill(new Skill("Spin", 1));
        passing.addSkill(new Skill("Pop", 5));
        passing.setComment("Passing is excellent but could be better.");
        defaultSkills.add(passing);

        SkillCategory tackling = new SkillCategory("Tackling");
        tackling.addSkill(new Skill("Front", 3));
        tackling.addSkill(new Skill("Rear", 5));
        tackling.addSkill(new Skill("Side", 4));
        tackling.addSkill(new Skill("Scrabble", 5));
        tackling.setComment("Especially good at tackling from the rear.");
        defaultSkills.add(tackling);

        SkillCategory kicking = new SkillCategory("Kicking");
        kicking.addSkill(new Skill("Drop", 5));
        kicking.addSkill(new Skill("Punt", 5));
        kicking.addSkill(new Skill("Grubber", 5));
        kicking.addSkill(new Skill("Goal", 2));
        kicking.setComment("Rubbish at goals.");
        defaultSkills.add(kicking);
        return defaultSkills;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public void register(User user) {
        users.add(user);
    }

    public boolean exists(String username) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }

    public Optional<User> getByCredentials(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .filter(user -> user.getPassword().equals(password))
                .findFirst();
    }

    public Optional<User> getByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }
}
