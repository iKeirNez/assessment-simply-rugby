package com.keirnellyer.simplyrugby.navbar;

public class NavigationElement {
    private String display;
    private String location;

    public NavigationElement(String display, String location) {
        this.display = display;
        this.location = location;
    }

    public String getDisplay() {
        return display;
    }

    public String getLocation() {
        return location;
    }
}
