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

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{nameField}));

        container = createContainer(SubdivisionPU.class);

        Table table = createTable(getMessage("subdivisionPU.SubdivisionPU"), container, new
                Object[]{"id", "name"});
        table.setSizeFull();
        table.setColumnWidth("id", 150);
        table.setColumnWidth("name", 300);

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    subdivisionPU = (SubdivisionPU) table.getValue();
                    if (subdivisionPU != null) {
                        updateEditPanelFields();
                    }
                });

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(table, editLayout);

        buttonUpdate.addClickListener(event -> {
            if (subdivisionPU == null)
                return;
            updateSubdivisionPUFields();
            getDataBaseService().saveOrUpdate(subdivisionPU);
            updateContainer(SubdivisionPU.class);
        });

        buttonCreate.addClickListener(event -> {
            subdivisionPU = new SubdivisionPU();
            updateSubdivisionPUFields();
            getDataBaseService().saveOrUpdate(subdivisionPU);
            updateContainer(SubdivisionPU.class);
        });

        buttonDelete.addClickListener(event -> {
            if (subdivisionPU == null)
                return;
            getDataBaseService().delete(subdivisionPU);
            updateContainer(SubdivisionPU.class);
        });


        return verticalSplitPanel;
    }

    private void updateEditPanelFields() {
        nameField.setValue(notNullVal(subdivisionPU.getName()));
    }

    private void updateSubdivisionPUFields() {
        subdivisionPU.setName(nameField.getValue());
    }

    private void createEditFields() {
        nameField = createTextField(getMessage("subdivisionPU.name"));
    }

}

