package com.bft.spring.ui;

/**
 * Created by rev on 06.01.2018.
 */

import com.bft.spring.model.ContractType;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

@org.springframework.stereotype.Component("contractTypeView")
public class ContractTypeView extends BaseView {

    private ContractType contractType;

    private TextField nameField;


    public VerticalSplitPanel initContent() {
        createEditFields();

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{nameField}));

        container = createContainer(ContractType.class);

        Table table = createTable(getMessage("contractType.ContractType"), container, new
                Object[]{"id", "name"});
        table.setSizeFull();
        table.setColumnWidth("id", 150);
        table.setColumnWidth("name", 300);

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    contractType = (ContractType) table.getValue();
                    if (contractType != null) {
                        updateEditPanelFields();
                    }
                });

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(table, editLayout);

        buttonUpdate.addClickListener(event -> {
            if (contractType == null)
                return;
            updateFields();
            getDataBaseService().saveOrUpdate(contractType);
            updateContainer(ContractType.class);
        });

        buttonCreate.addClickListener(event -> {
            contractType = new ContractType();
            updateFields();
            getDataBaseService().saveOrUpdate(contractType);
            updateContainer(ContractType.class);
        });

        buttonDelete.addClickListener(event -> {
            if (contractType == null)
                return;
            getDataBaseService().delete(contractType);
            updateContainer(ContractType.class);
        });

        return verticalSplitPanel;
    }

    private void updateEditPanelFields() {
        nameField.setValue(notNullVal(contractType.getName()));
    }

    private void updateFields() {
        contractType.setName(nameField.getValue());
    }

    private void createEditFields() {
        nameField = createTextField(getMessage("contractType.name"));
    }

}


