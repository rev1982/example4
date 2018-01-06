package com.bft.spring.ui.menu;

import com.bft.spring.VaadinUI;
import com.bft.spring.messages.Messages;
import com.bft.spring.ui.*;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by rev on 27.12.2017.
 */

@org.springframework.stereotype.Component("menu")
public class Menu {

    private Navigator navigator;

    private static List<MenuGroup> allGroups = new LinkedList<MenuGroup>();

    private static List<MenuItem> allForms = new LinkedList<MenuItem>();

    private MenuGroup menuSet;

    @Autowired
    TimeZoneView timeZoneView;

    @Autowired
    CompanyView companyView;

    @Autowired
    UserTableView userTableView;

    @Autowired
    SubdivisionPUView subdivisionPUView;

    @Autowired
    Messages messageSource;
    @Autowired
    SubsidiaryView subsidiaryView;
    @Autowired
    ContractTypeView contractTypeView;
    @Autowired
    ContractView contractView;
    @Autowired
    CustomerCompanyView customerCompanyView;


    public void build(Layout treeLayout, Layout contentViewLayout) {
        navigator = new Navigator(VaadinUI.getCurrent(), contentViewLayout);
        VaadinUI.getCurrent().setNavigator(navigator);
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
        MenuGroup companyGroup = tryAddGroup(messageSource.getMessage("menu.company"), menuSet);

        tryAddForm(CompanyView.class, companyView, companyGroup);
        tryAddForm(SubdivisionPUView.class, subdivisionPUView, companyGroup);
        tryAddForm(UserTableView.class, userTableView, companyGroup);
        tryAddForm(SubsidiaryView.class, subsidiaryView, companyGroup);
        tryAddForm(TimeZoneView.class, timeZoneView, companyGroup);

        menuSet.addSubgroup(companyGroup);


        MenuGroup contractGroup = tryAddGroup(messageSource.getMessage("menu.Contract"), menuSet);

        tryAddForm(ContractTypeView.class, contractTypeView , contractGroup);
        tryAddForm(ContractView.class, contractView , contractGroup);
        tryAddForm(CustomerCompanyView.class, customerCompanyView , contractGroup);
        menuSet.addSubgroup(contractGroup);

    }

    private void tryAddForm(Class<? extends BaseView> clzz, BaseView baseView, MenuGroup parent) {
        String code = messageSource.getMessage(clzz.getSimpleName());
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

