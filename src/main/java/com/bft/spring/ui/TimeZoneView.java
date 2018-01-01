package com.bft.spring.ui;

/**
 * Created by rev on 28.12.2017.
 */

import com.bft.spring.model.TimeZone;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Configurable;


@Configurable
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
        table.setColumnWidth("id",150);
        table.setColumnWidth("name", 300);

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    timezone = (TimeZone) table.getValue();
                    if (timezone != null) {
                        updateEditPanelFields();
                    }
                });

        Button button = new Button(getMessage("button.update"));
        button.addClickListener(event -> {
            if (timezone == null)
                return;
            updateTimeZoneFields();
            getDataBaseService().saveOrUpdate(timezone);
            updateContainer(TimeZone.class);
        });

        Button button2 = new Button(getMessage("button.create"));
        button2.addClickListener(event -> {
            timezone = new TimeZone();
            updateTimeZoneFields();
            getDataBaseService().saveOrUpdate(timezone);
            updateContainer(TimeZone.class);
        });

        Button button3 = new Button(getMessage("button.delete"));
        button3.addClickListener(event -> {
            if (timezone == null)
                return;
            getDataBaseService().delete(timezone);
            updateContainer(TimeZone.class);
        });

        HorizontalLayout buttonLayout = new HorizontalLayout(button, button2, button3);
        buttonLayout.setMargin(true);
        buttonLayout.setSpacing(true);

        VerticalLayout verticalLayout = new VerticalLayout(editLayout, buttonLayout);
        verticalLayout.setSizeFull();
        verticalLayout.setMargin(true);
        verticalLayout.setSpacing(true);
        verticalLayout.setExpandRatio(editLayout, 0.85f);
        verticalLayout.setExpandRatio(buttonLayout, 0.15f);

        VerticalSplitPanel splitPanel = new VerticalSplitPanel();
        Panel upComponent = new Panel();
        Panel downComponent = new Panel();
        splitPanel.setFirstComponent(upComponent);
        splitPanel.setSecondComponent(downComponent);
        upComponent.setContent(new VerticalLayout(table));
        downComponent.setContent(verticalLayout);

        upComponent.setSizeFull();
        downComponent.setSizeFull();
        splitPanel.setSizeFull();

        return splitPanel;
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

