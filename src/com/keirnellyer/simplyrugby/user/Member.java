package com.keirnellyer.simplyrugby.user;

import com.keirnellyer.simplyrugby.navbar.Navigation;
import com.keirnellyer.simplyrugby.navbar.NavigationElement;
import com.keirnellyer.simplyrugby.skill.SkillCategory;

import java.util.ArrayList;
import java.util.List;

public abstract class Member extends User {
    private final List<SkillCategory> skills = new ArrayList<>();
    private String firstName = "";
    private String lastName = "";

    public Member(String username) {
        super(username);
        initializeNavBar();
    }

    private void initializeNavBar() {
        Navigation navigation = new Navigation();

        NavigationElement first = new NavigationElement("Member Thing 1", "/");
        navigation.addItem(first);

        NavigationElement second = new NavigationElement("Member Thing 2", "/");
        navigation.addItem(second);

        setNavigation(navigation);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SkillCategory> getSkills() {
        return skills;
    }

    public void addSkill(SkillCategory skill) {
        skills.add(skill);
    }
}
