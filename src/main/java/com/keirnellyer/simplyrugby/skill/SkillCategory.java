package com.keirnellyer.simplyrugby.skill;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a category which contains {@link Skill} instances.
 */
public class SkillCategory {
    private final String name;
    private String comment;

    private final List<Skill> skills = new ArrayList<>();

    /**
     * Constructs a new instance by using another instance as a template.
     *
     * This constructor also clones all contained {@link Skill} instances.
     *
     * @param category the template
     */
    public SkillCategory(SkillCategory category) {
        this(category.name, category.comment);

        List<Skill> skillCopy = category.skills.stream()
                .map(Skill::new)
                .collect(Collectors.toList());
        skills.addAll(skillCopy);
    }

    /**
     * Constructs a new instance with no pre-defined comment.
     *
     * @param name the name of the category
     */
    public SkillCategory(String name) {
        this(name, null);
    }

    /**
     * Constructs a new instance.
     *
     * @param name the name of the category
     * @param comment the pre-defined comment
     */
    public SkillCategory(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    /**
     * Gets the name of the category,
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the category comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the category comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets all skills contained by this category.
     *
     * @return the skills
     */
    public List<Skill> getSkills() {
        return skills;
    }

    /**
     * Adds a new skill to the category.
     *
     * @param skill the skill
     */
    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    @Override
    public String toString() {
        return "SkillCategory{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", skills=" + skills +
                '}';
    }
}
