package com.bft.spring.ui.menu;

import com.vaadin.ui.Tree;

import java.util.LinkedList;
import java.util.List;

public class MenuGroup extends MenuItem {

    private List<MenuItem> items = new LinkedList<>();

    private List<MenuGroup> subgroups = new LinkedList<>();

    protected MenuGroup(String name) {
        super(name);
    }

    @Override
    public void putIntoTree(Tree tree) {
        super.putIntoTree(tree);
        tree.setChildrenAllowed(name, true);
        putChildsIntoTree(tree, true);
    }

    private void putChildsIntoTree(Tree tree, boolean bindParent) {
        for (MenuGroup group : subgroups) {
            group.putIntoTree(tree);
            if (bindParent)
                tree.setParent(group.getName(), name);
        }

        for (MenuItem item : items) {
            item.putIntoTree(tree);
            if (bindParent)
                tree.setParent(item.getName(), name);
        }
    }

    public void putChildsIntoTree(Tree tree) {
        putChildsIntoTree(tree, false);
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void addSubgroup(MenuGroup group) {
        subgroups.add(group);
    }
}
