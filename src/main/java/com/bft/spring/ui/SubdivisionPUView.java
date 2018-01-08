package com.bft.spring.ui;

/**
 * Created by rev on 01.01.2018.
 */

import com.bft.spring.model.SubdivisionPU;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

@org.springframework.stereotype.Component("subdivisionPUView")
public class SubdivisionPUView extends BaseView {

    private SubdivisionPU subdivisionPU;

    private TextField nameField;


    public VerticalSplitPanel initContent() {
        createEditFields();
        entityClass = SubdivisionPU.class;

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{nameField}));

        container = createContainer(SubdivisionPU.class);

        table = createTable(getMessage("subdivisionPU.SubdivisionPU"), container, new
                Object[]{"id", "name"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }

    @Override
    public void updateEditPanelFields() {
        subdivisionPU = (SubdivisionPU) entity;
        nameField.setValue(notNullVal(subdivisionPU.getName()));
    }

    @Override
    public void updateFields() {
        subdivisionPU = (SubdivisionPU) entity;
        subdivisionPU.setName(nameField.getValue());
    }

    private void createEditFields() {
        nameField = createTextField(getMessage("subdivisionPU.name"));
    }

}

