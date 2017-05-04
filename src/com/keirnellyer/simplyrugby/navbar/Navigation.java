package com.keirnellyer.simplyrugby.navbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Navigation {
    private final List<NavigationElement> items = new ArrayList<>();

    public Navigation(NavigationElement... items) {
        this.items.addAll(Arrays.asList(items));
    }

    public List<NavigationElement> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(NavigationElement item) {
        items.add(item);
    }
}
