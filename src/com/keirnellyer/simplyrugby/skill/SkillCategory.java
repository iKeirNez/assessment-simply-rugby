package com.keirnellyer.simplyrugby.skill;

import java.util.ArrayList;
import java.util.List;

public class SkillCategory {
    private final String name;
    private String comment;

    private final List<Skill> skills = new ArrayList<>();

    public SkillCategory(String name) {
        this(name, null);
    }

    public SkillCategory(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }
}
