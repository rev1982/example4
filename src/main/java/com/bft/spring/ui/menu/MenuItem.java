package com.bft.spring.ui.menu;

import com.vaadin.ui.Tree;


public class MenuItem {

    protected String name;

    protected MenuItem(String name) {
        this.name = name;
    }

    public void putIntoTree(Tree tree) {
        tree.addItem(name);
        tree.setChildrenAllowed(name, false);
        tree.setItemCaption(name, name);
    }

    public String getName() {
        return name;
    }

}
