package com.keirnellyer.simplyrugby.user;

import com.keirnellyer.simplyrugby.navbar.Navigation;
import com.keirnellyer.simplyrugby.navbar.NavigationElement;

/**
 * Top level user type with super-user privileges.
 */
public class Administrator extends User {
    public Administrator(String username) {
        super(username);

        initializeNavBar();
    }

    private void initializeNavBar() {
        Navigation navigation = new Navigation();

        NavigationElement first = new NavigationElement("Edit Skills", "/admin/edit_skills");
        navigation.addItem(first);

        setNavigation(navigation);
    }

    public void updateSkillValue(Member member, String category, String skill, int value) {
        member.getSkills().stream()
                .filter(c -> c.getName().equalsIgnoreCase(category))
                .flatMap(c -> c.getSkills().stream()
                        .filter(s -> s.getName().equalsIgnoreCase(skill)))
                .forEach(s -> s.setValue(value));
    }

    public void updateCategoryComment(Member member, String category, String comment) {
        member.getSkills().stream()
                .filter(c -> c.getName().equalsIgnoreCase(category))
                .forEach(c -> c.setComment(comment));
    }

    @Override
    public String toString() {
        return "Administrator{} " + super.toString();
    }
}
