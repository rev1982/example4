package com.bft.spring.ui.menu;

import com.bft.spring.VaadinUI;
import com.bft.spring.configuration.AppConfig;
import com.bft.spring.model.SubdivisionPU;
import com.bft.spring.ui.*;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rev on 27.12.2017.
 */

public class Menu implements Serializable {

    private Layout treeLayout;

    private Navigator navigator;

    private static List<MenuGroup> allGroups = new LinkedList<MenuGroup>();

    private static List<MenuItem> allForms = new LinkedList<MenuItem>();

    private MenuGroup menuSet;

    private AppConfig appConfig;

    public Menu(Layout treeLayout, Layout contentViewLayout, AppConfig appConfig) {
        this.treeLayout = treeLayout;
        this.appConfig = appConfig;
        navigator = new Navigator(VaadinUI.getCurrent(), contentViewLayout);
        VaadinUI.getCurrent().setNavigator(navigator);
    }

    public void build() {
        MenuNavigatorBuilder.init(navigator);
        Tree menuTree = buildTree();
        menuTree.setImmediate(true);
        treeLayout.addComponent(menuTree);
        menuTree.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                String menuItem = event.getItemId().toString();
                if (MenuNavigatorBuilder.isNavigationExist(menuItem)) {
                    navigator.navigateTo(menuItem);
                }
            }
        });
    }

    private Tree buildTree() {
        initMenuSet();

        Tree tree = new Tree();
        menuSet.putChildsIntoTree(tree);
        for (final Object id : tree.rootItemIds()) {
            tree.expandItemsRecursively(id);
        }
        return tree;
    }

    private void initMenuSet() {
        allGroups.clear();
        allForms.clear();

        menuSet = new MenuGroup("root");
        MenuGroup companyGroup = tryAddGroup(appConfig.messageSource().getMessage("menu.company", null, null), menuSet);


        tryAddForm(CompanyView.class, appConfig.companyView(), companyGroup);
        tryAddForm(SubdivisionPUView.class, appConfig.subdivisionPUView(), companyGroup);
        tryAddForm(UserTableView.class, appConfig.userTableView(), companyGroup);

        menuSet.addSubgroup(companyGroup);


        MenuGroup timezoneGroup = tryAddGroup(appConfig.messageSource().getMessage("menu.timezone", null, null), menuSet);

        tryAddForm(TimeZoneView.class, appConfig.timeZoneView(), timezoneGroup);
        menuSet.addSubgroup(timezoneGroup);

    }

    private void tryAddForm(Class<? extends BaseView> clzz, BaseView baseView, MenuGroup parent) {
        String code = appConfig.messageSource().getMessage(clzz.getSimpleName(), null, null);
        MenuItem item = new MenuItem(code);
        allForms.add(item);

        parent.addItem(item);

        try {
            MenuNavigatorBuilder.addView(code, baseView);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    private MenuGroup tryAddGroup(String code, MenuGroup parent) {
        MenuGroup group = new MenuGroup(code);
        allGroups.add(group);
        parent.addItem(group);
        return group;
    }

}

