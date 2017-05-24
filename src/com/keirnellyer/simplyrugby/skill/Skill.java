package com.keirnellyer.simplyrugby.skill;

/**
 * Represents a skill which may have a name and a value (between 1 &amp; 5).
 */
public class Skill {
    private final String name;
    private int value;

    /**
     * Constructs a new instance by using another instance as a template.
     *
     * @param skill the template
     */
    public Skill(Skill skill) {
        this(skill.name, skill.value);
    }

    /**
     * Constructs a new instance using a default skill value.
     *
     * @param name the name of the skill
     */
    public Skill(String name) {
        this(name, 1);
    }

    /**
     * Constructs a new instance.
     *
     * @param name the name of the skill
     * @param value the value of the skill
     */
    public Skill(String name, int value) {
        if (value < 1 || value > 5) {
            throw new IllegalArgumentException("Value must be between 1 and 5.");
        }

        this.name = name;
        this.value = value;
    }

    /**
     * Gets the name of the skill.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the value of the skill.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the skill.
     *
     * @param value the value
     */
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
