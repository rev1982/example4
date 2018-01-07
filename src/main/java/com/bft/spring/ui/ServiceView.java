package com.bft.spring.ui;

/**
 * Created by rev on 07.01.2018.
 */
import com.bft.spring.model.Service;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

@org.springframework.stereotype.Component("serviceView")
public class ServiceView extends BaseView {

    private Service service;

    private TextField nameField;

    private TextField defaultValField;


    public VerticalSplitPanel initContent() {
        createEditFields();

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{nameField, defaultValField}));

        container = createContainer(Service.class);

        Table table = createTable(getMessage("service.service"), container, new
                Object[]{"id", "name", "defaultVal"});
        table.setSizeFull();

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    service = (Service) table.getValue();
                    if (service != null) {
                        updateEditPanelFields();
                    }
                });

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(table, editLayout);

        buttonUpdate.addClickListener(event -> {
            if (service == null)
                return;
            updateFields();
            getDataBaseService().saveOrUpdate(service);
            updateContainer(Service.class);
        });

        buttonCreate.addClickListener(event -> {
            service = new Service();
            updateFields();
            getDataBaseService().saveOrUpdate(service);
            updateContainer(Service.class);
        });

        buttonDelete.addClickListener(event -> {
            if (service == null)
                return;
            getDataBaseService().delete(service);
            updateContainer(Service.class);
        });

        return verticalSplitPanel;
    }

    private void updateEditPanelFields() {
        nameField.setValue(notNullVal(service.getName()));
        defaultValField.setValue(notNullVal(service.getDefaultVal()));
    }

    private void updateFields() {
        service.setName(nameField.getValue());
        service.setDefaultVal(defaultValField.getValue());
    }

    private void createEditFields() {
        nameField = createTextField(getMessage("service.name"));
        defaultValField = createTextField(getMessage("service.defaultVal"));
    }

}

