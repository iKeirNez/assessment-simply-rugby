package com.keirnellyer.simplyrugby.repository;

import com.keirnellyer.simplyrugby.skill.Skill;
import com.keirnellyer.simplyrugby.skill.SkillCategory;
import com.keirnellyer.simplyrugby.user.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * A repository responsible for initializing, storing and retrieving {@link User} instances.
 */
public class UserRepository {
    private static final List<SkillCategory> DEFAULT_SKILLS = new ArrayList<SkillCategory>(){{
        String[][] skills = {
                {"Passing", "Standard", "Spin", "Pop"},
                {"Tackling", "Front", "Rear", "Side", "Scrabble"},
                {"Kicking", "Drop", "Punt", "Grubber", "Goal"}
        };

        for (String[] skillArray : skills) {
            SkillCategory category = new SkillCategory(skillArray[0]);

            for (int i = 1; i < skillArray.length; i++) {
                category.addSkill(new Skill(skillArray[i], 1));
            }

            add(category);
        }
    }};

    private final List<User> users = new ArrayList<>();

    /**
     * Constructs a new instance with default user accounts.
     */
    public UserRepository() {
        initializeUsers();
    }

    private void initializeUsers() {
        Administrator admin = new Administrator("admin");
        admin.setPassword("admin");
        users.add(admin);

        JuniorMember jSmith = new JuniorMember("jsmith");
        jSmith.setPassword("password");
        jSmith.setFirstName("John");
        jSmith.setLastName("Smith");
        attachDefaultSkills(jSmith);
        users.add(jSmith);

        SeniorMember gMullen = new SeniorMember("ged");
        gMullen.setPassword("password");
        gMullen.setFirstName("Ged");
        gMullen.setLastName("Mullen");
        attachDefaultSkills(gMullen);
        users.add(gMullen);

        SeniorMember kNellyer = new SeniorMember("iKeirNez");
        kNellyer.setPassword("password");
        kNellyer.setFirstName("Keir");
        kNellyer.setLastName("Nellyer");
        attachDefaultSkills(kNellyer);
        users.add(kNellyer);

        JuniorMember rDonaldson = new JuniorMember("rdonaldson");
        rDonaldson.setPassword("password");
        rDonaldson.setFirstName("Richard");
        rDonaldson.setLastName("Donaldson");
        attachDefaultSkills(rDonaldson);
        users.add(rDonaldson);

        Guest guest = new Guest("guest");
        users.add(guest);
    }

    private void attachDefaultSkills(Member member) {
        List<SkillCategory> skills2 = getDefaultSkills();
        writeDefaultComments(member, skills2);
        randomizeSkillValues(skills2);
        skills2.forEach(member::addSkillCategory);
    }

    private static List<SkillCategory> getDefaultSkills() {
        return DEFAULT_SKILLS.stream()
                .map(SkillCategory::new)
                .collect(Collectors.toList());
    }

    private static void writeDefaultComments(Member member, List<SkillCategory> skills) {
        skills.forEach(category -> category.setComment(member.getFirstName() + ", lorem ipsum dolor"));
    }

    private static void randomizeSkillValues(List<SkillCategory> categories) {
        categories.forEach(category ->
                category.getSkills().forEach(skill ->
                        skill.setValue(ThreadLocalRandom.current().nextInt(1, 6))));
    }

    /**
     * Gets all users.
     *
     * @return the users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Registers a user with the repository.
     *
     * @param user the user
     */
    public void register(User user) {
        users.add(user);
    }

    /**
     * Checks if a user with the supplied username exists.
     *
     * @param username the username
     * @return true if user exists, false otherwise
     */
    public boolean exists(String username) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }

    /**
     * Finds a user by their credentials.
     *
     * @param username the username
     * @param password the password
     * @return value present if found, empty otherwise
     */
    public Optional<User> getByCredentials(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                // check if user password equals input password, or if not set, check input password is ""
                .filter(user -> user.getPassword().map(p -> p.equals(password)).orElse(password.isEmpty()))
                .findFirst();
    }

    /**
     * Finds a user by their username.
     *
     * @param username the username
     * @return value present if found, empty otherwise
     */
    public Optional<User> getByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }

    /**
     * Gets the default guest user.
     *
     * @return the guest user
     */
    public User getDefaultGuest() {
        return users.stream()
                .filter(user -> user instanceof Guest)
                .filter(user -> user.getUsername().equalsIgnoreCase("guest"))
                .findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "users=" + users +
                '}';
    }
}
