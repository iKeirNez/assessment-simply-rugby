package com.keirnellyer.simplyrugby.user;

import com.keirnellyer.simplyrugby.navbar.Navigation;
import com.keirnellyer.simplyrugby.navbar.NavigationElement;
import com.keirnellyer.simplyrugby.skill.SkillCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * A member type capable of having skills tracking.
 */
public abstract class Member extends User {
    private final List<SkillCategory> skills = new ArrayList<>();
    private String firstName = "";
    private String lastName = "";

    /**
     * Constructs a new instance.
     *
     * @param username the username
     */
    public Member(String username) {
        super(username);
        initializeNavBar();
    }

    private void initializeNavBar() {
        Navigation navigation = new Navigation();
        navigation.addItem(new NavigationElement("Skill Tracking", "/member/skills.jsp"));
        setNavigation(navigation);
    }

    /**
     * Gets the first name.
     *
     * @return the first na,e
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the skills.
     *
     * @return the skills
     */
    public List<SkillCategory> getSkills() {
        return skills;
    }

    /**
     * Adds a skill category.
     *
     * @param skill the skill category
     */
    public void addSkillCategory(SkillCategory skill) {
        skills.add(skill);
    }

    @Override
    public String toString() {
        return "Member{" +
                "skills=" + skills +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                "} " + super.toString();
    }
}
