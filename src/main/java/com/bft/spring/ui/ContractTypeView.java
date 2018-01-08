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
        entityClass = ContractType.class;

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{nameField}));

        container = createContainer(ContractType.class);

        table = createTable(getMessage("contractType.ContractType"), container, new
                Object[]{"id", "name"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }

    @Override
    public void updateEditPanelFields() {
        contractType = (ContractType) entity;
        nameField.setValue(notNullVal(contractType.getName()));
    }

    @Override
    public void updateFields() {
        contractType = (ContractType) entity;
        contractType.setName(nameField.getValue());
    }

    private void createEditFields() {
        nameField = createTextField(getMessage("contractType.name"));
    }

}


