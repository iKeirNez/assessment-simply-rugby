package com.keirnellyer.simplyrugby.skill;

public class Skill {
    private final String name;
    private int value;

    public Skill(String type) {
        this(type, 1);
    }

    public Skill(String type, int value) {
        if (value < 1 || value > 5) {
            throw new IllegalArgumentException("Value must be between 1 and 5.");
        }

        this.name = type;
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
