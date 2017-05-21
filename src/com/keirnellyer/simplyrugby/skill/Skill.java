package com.keirnellyer.simplyrugby.skill;

public class Skill {
    private final String name;
    private int value;

    public Skill(Skill skill) {
        this(skill.name, skill.value);
    }

    public Skill(String name) {
        this(name, 1);
    }

    public Skill(String name, int value) {
        if (value < 1 || value > 5) {
            throw new IllegalArgumentException("Value must be between 1 and 5.");
        }

        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
