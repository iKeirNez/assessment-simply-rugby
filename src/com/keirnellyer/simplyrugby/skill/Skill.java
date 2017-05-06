package com.keirnellyer.simplyrugby.skill;

public class Skill {
    private final String type;
    private int value = 0;
    private String comment = null;

    public Skill(String type) {
        this.type = type;
    }

    public Skill(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public Skill(String type, int value, String comment) {
        this.type = type;
        this.value = value;
        this.comment = comment;
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
