package com.keirnellyer.simplyrugby.repository;

import com.keirnellyer.simplyrugby.skill.Skill;
import com.keirnellyer.simplyrugby.skill.SkillCategory;
import com.keirnellyer.simplyrugby.user.*;

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

        Guest guest = new Guest("guest");
        users.add(guest);
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
                // check if user password equals input password, or if not set, check input password is ""
                .filter(user -> user.getPassword().map(p -> p.equals(password)).orElse(password.isEmpty()))
                .findFirst();
    }

    public Optional<User> getByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }

    public User getDefaultGuest() {
        return users.stream()
                .filter(user -> user instanceof Guest)
                .filter(user -> user.getUsername().equalsIgnoreCase("guest"))
                .findFirst().orElse(null);
    }
}
