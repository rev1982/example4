package com.bft.spring.ui;

/**
 * Created by rev on 07.01.2018.
 */
import com.bft.spring.model.Service;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

@org.springframework.stereotype.Component("serviceView")
public class ServiceView extends BaseView {

    private Service service;

    private TextField nameField;

    private TextField defaultValField;


    public VerticalSplitPanel initContent() {
        createEditFields();
        entityClass = Service.class;

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{nameField, defaultValField}));

        container = createContainer(Service.class);

        table = createTable(getMessage("service.service"), container, new
                Object[]{"id", "name", "defaultVal"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }

    @Override
    public void updateEditPanelFields() {
        service = (Service) entity;
        nameField.setValue(notNullVal(service.getName()));
        defaultValField.setValue(notNullVal(service.getDefaultVal()));
    }

    @Override
    public void updateFields() {
        service = (Service) entity;
        service.setName(nameField.getValue());
        service.setDefaultVal(defaultValField.getValue());
    }

    private void createEditFields() {
        nameField = createTextField(getMessage("service.name"));
        defaultValField = createTextField(getMessage("service.defaultVal"));
    }

}

