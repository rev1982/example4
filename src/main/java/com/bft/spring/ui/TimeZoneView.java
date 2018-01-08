package com.bft.spring.ui;

/**
 * Created by rev on 28.12.2017.
 */

import com.bft.spring.model.TimeZone;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

@org.springframework.stereotype.Component("timeZoneView")
public class TimeZoneView extends BaseView {

    private TimeZone timezone;

    private TextField nameField;


    public VerticalSplitPanel initContent() {
        createEditFields();
        entityClass = TimeZone.class;

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{nameField}));

        container = createContainer(TimeZone.class);

        table = createTable(getMessage("timezone.TimeZone"), container, new
                Object[]{"id", "name"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }

    @Override
    public void updateEditPanelFields() {
        timezone = (TimeZone) entity;
        nameField.setValue(notNullVal(timezone.getName()));
    }

    @Override
    public void updateFields() {
        timezone = (TimeZone) entity;
        timezone.setName(nameField.getValue());
    }

    private void createEditFields() {
        nameField = createTextField(getMessage("timezone.name"));
    }

}

