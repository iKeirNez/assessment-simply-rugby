package com.keirnellyer.simplyrugby.navbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the navigational options a user may have.
 */
public class Navigation {
    private final List<NavigationElement> items = new ArrayList<>();

    /**
     * Constructs a new instance with the supplied {@link NavigationElement}'s pre-populated.
     * @param items the navigation elements to pre-populate this instance with
     */
    public Navigation(NavigationElement... items) {
        this.items.addAll(Arrays.asList(items));
    }

    /**
     * Gets the navigational elements.
     *
     * @return the navigational elements
     */
    public List<NavigationElement> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Adds a navigational element.
     *
     * @param item the navigational element
     */
    public void addItem(NavigationElement item) {
        items.add(item);
    }
}
