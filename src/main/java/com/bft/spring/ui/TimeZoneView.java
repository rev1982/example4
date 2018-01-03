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

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{nameField}));

        container = createContainer(TimeZone.class);

        Table table = createTable(getMessage("timezone.TimeZone"), container, new
                Object[]{"id", "name"});
        table.setSizeFull();
        table.setColumnWidth("id", 150);
        table.setColumnWidth("name", 300);

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    timezone = (TimeZone) table.getValue();
                    if (timezone != null) {
                        updateEditPanelFields();
                    }
                });

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(table, editLayout);

        buttonUpdate.addClickListener(event -> {
            if (timezone == null)
                return;
            updateTimeZoneFields();
            getDataBaseService().saveOrUpdate(timezone);
            updateContainer(TimeZone.class);
        });

        buttonCreate.addClickListener(event -> {
            timezone = new TimeZone();
            updateTimeZoneFields();
            getDataBaseService().saveOrUpdate(timezone);
            updateContainer(TimeZone.class);
        });

        buttonDelete.addClickListener(event -> {
            if (timezone == null)
                return;
            getDataBaseService().delete(timezone);
            updateContainer(TimeZone.class);
        });

        return verticalSplitPanel;
    }

    private void updateEditPanelFields() {
        nameField.setValue(notNullVal(timezone.getName()));
    }

    private void updateTimeZoneFields() {
        timezone.setName(nameField.getValue());
    }

    private void createEditFields() {
        nameField = createTextField(getMessage("timezone.name"));
    }

}

