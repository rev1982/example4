package com.bft.spring.ui.menu;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.bft.spring.ui.BaseView;

import java.util.HashMap;
import java.util.Map;

public class MenuNavigatorBuilder {

    private static Navigator navigator;

    private static final String DEFAULT_VIEW_KEY = "";

    private static Map<String, View> navigationMap = new HashMap<String, View>();
    public static void init(Navigator nv) {
        navigationMap.clear();
        navigator = nv;
        navigator.addView(DEFAULT_VIEW_KEY, new BaseView<>());
    }

    public static void addView(String code, BaseView v) throws IllegalAccessException, InstantiationException {
        navigationMap.put(code, v);
        navigator.addView(code, v);
    }

    public static boolean isNavigationExist(String key) {
        return navigationMap.get(key) != null;
    }

}
