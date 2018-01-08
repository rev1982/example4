package com.bft.spring.ui;

import com.bft.spring.model.*;
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
        entityClass = SoldUnit.class;

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{unitIdField, contractSubjectIdField}));

        container = createContainer(SoldUnit.class);

        table = createTable(getMessage("SoldUnit.soldUnit"), container, new
                Object[]{"id", "unit", "contractSubject"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }

    @Override
    public void updateEditPanelFields() {
        soldUnit = (SoldUnit) entity;
        contractSubjectIdField.setValue(getNotNullId(soldUnit.getContractSubject()));
        unitIdField.setValue(getNotNullId(soldUnit.getUnit()));
    }

    @Override
    public void updateFields() {
        soldUnit = (SoldUnit) entity;
        soldUnit.setContractSubject((ContractSubject) getEntityById(contractSubjectIdField.getValue(), ContractSubject.class));
        soldUnit.setUnit((com.bft.spring.model.Unit) getEntityById(unitIdField.getValue(), com.bft.spring.model.Unit.class));
    }

    private void createEditFields() {
        contractSubjectIdField = createCombo("SoldUnit.contractSubjectId", createIdContainer(ContractSubject.class.getSimpleName()));
        unitIdField = createCombo("SoldUnit.unitId", createIdContainer(Unit.class.getSimpleName()));
    }

}
