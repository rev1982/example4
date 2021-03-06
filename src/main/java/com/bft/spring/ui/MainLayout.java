package com.bft.spring.ui;

import com.bft.spring.messages.Messages;
import com.bft.spring.ui.menu.Menu;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component("mainLayout")
public class MainLayout extends VerticalLayout {
    private VerticalLayout treeLayout = new VerticalLayout();
    private VerticalLayout contentViewLayout = new VerticalLayout();
    private boolean initialised;
    @Autowired
    Menu menu;
    @Autowired
    Messages messageSource;


    public void init() {
        if (initialised)
            return;
        setSizeFull();
        buildMainContent();
        menu.build(treeLayout, contentViewLayout);
        initialised = true;
    }


    // шапка
    private Layout buildHeader() {
        VerticalLayout headerLayout = new VerticalLayout();
        headerLayout.setHeight(100, Unit.PERCENTAGE);
        headerLayout.setWidth(100, Unit.PERCENTAGE);
        headerLayout.setSpacing(false);

        HorizontalLayout contentLayuot = new HorizontalLayout();
        contentLayuot.setWidth(100, Unit.PERCENTAGE);
        contentLayuot.setSpacing(false);

        Label header = new Label(messageSource.getMessage("AppName"), ContentMode.TEXT);
        contentLayuot.addComponent(header);
        contentLayuot.setComponentAlignment(header, Alignment.MIDDLE_RIGHT);
        contentLayuot.setSizeFull();

        headerLayout.addComponent(contentLayuot);

        return headerLayout;
    }

    // строим основную форму
    private void buildMainContent() {
        Layout headerLayout = buildHeader();
        addComponent(headerLayout);
        setExpandRatio(headerLayout, 0.07f);

        HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
        horizontalSplitPanel.setSplitPosition(13, Unit.PERCENTAGE);
        contentViewLayout.setSizeFull();
        horizontalSplitPanel.setFirstComponent(treeLayout);
        horizontalSplitPanel.setSecondComponent(contentViewLayout);
        addComponent(horizontalSplitPanel);
        setExpandRatio(horizontalSplitPanel, 1f);
    }

}