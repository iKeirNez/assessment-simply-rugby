package com.keirnellyer.simplyrugby.skill;

public class Skill {
    private final SkillCategory category;
    private final String type;
    private int value = 0;
    private String comment = null;

    public Skill(SkillCategory category, String type) {
        this.category = category;
        this.type = type;
    }

    public Skill(SkillCategory category, String type, int value) {
        this.category = category;
        this.type = type;
        this.value = value;
    }

    public Skill(SkillCategory category, String type, int value, String comment) {
        this.category = category;
        this.type = type;
        this.value = value;
        this.comment = comment;
    }

    public SkillCategory getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
