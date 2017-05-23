package com.keirnellyer.simplyrugby.navbar;

/**
 * Represents a navigation element.
 */
public class NavigationElement {
    private String display;
    private String location;

    /**
     * Constructs a new instance.
     *
     * @param display the display name
     * @param location the relative location
     */
    public NavigationElement(String display, String location) {
        this.display = display;
        this.location = location;
    }

    /**
     * Gets the display name.
     *
     * @return the display name
     */
    public String getDisplay() {
        return display;
    }

    /**
     * Gets the relative url location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "NavigationElement{" +
                "display='" + display + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
