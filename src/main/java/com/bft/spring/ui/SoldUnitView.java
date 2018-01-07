package com.bft.spring.ui;

import com.bft.spring.model.*;
import com.vaadin.data.Property;
import com.vaadin.ui.*;

/**
 * Created by rev on 07.01.2018.
 */
@org.springframework.stereotype.Component("soldUnitView")
public class SoldUnitView extends BaseView {

    private SoldUnit soldUnit;

    private ComboBox unitIdField;

    private ComboBox contractSubjectIdField;


    public VerticalSplitPanel initContent() {
        createEditFields();

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{unitIdField, contractSubjectIdField}));

        container = createContainer(SoldUnit.class);

        Table table = createTable(getMessage("SoldUnit.soldUnit"), container, new
                Object[]{"id", "unit", "contractSubject"});
        table.setSizeFull();

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    soldUnit = (SoldUnit) table.getValue();
                    if (soldUnit != null) {
                        updateEditPanelFields();
                    }
                });

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(table, editLayout);

        buttonUpdate.addClickListener(event -> {
            if (soldUnit == null)
                return;
            updateFields();
            getDataBaseService().saveOrUpdate(soldUnit);
            updateContainer(SoldUnit.class);
        });

        buttonCreate.addClickListener(event -> {
            soldUnit = new SoldUnit();
            updateFields();
            getDataBaseService().saveOrUpdate(soldUnit);
            updateContainer(SoldUnit.class);
        });

        buttonDelete.addClickListener(event -> {
            if (soldUnit == null)
                return;
            getDataBaseService().delete(soldUnit);
            updateContainer(SoldUnit.class);
        });

        return verticalSplitPanel;
    }

    private void updateEditPanelFields() {
        contractSubjectIdField.setValue(getNotNullId(soldUnit.getContractSubject()));
        unitIdField.setValue(getNotNullId(soldUnit.getUnit()));
    }

    private void updateFields() {
        soldUnit.setContractSubject((ContractSubject) getValueById(contractSubjectIdField.getValue(), ContractSubject.class));
        soldUnit.setUnit((com.bft.spring.model.Unit) getValueById(unitIdField.getValue(), com.bft.spring.model.Unit.class));
    }

    private void createEditFields() {
        contractSubjectIdField = createCombo("SoldUnit.contractSubjectId", createIdContainer(ContractSubject.class.getSimpleName(),"id"));
        unitIdField = createCombo("SoldUnit.unitId", createIdContainer(Unit.class.getSimpleName(),"id"));
    }

}
