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

        container = uiUtils.createContainer(SoldUnit.class);

        table = createTable(getMessage("SoldUnit.soldUnit"), container, new
                Object[]{"id", "unit", "contractSubject"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }

    @Override
    public void updateEditPanelFields() {
        soldUnit = (SoldUnit) entity;
        contractSubjectIdField.setValue(uiUtils.getNotNullId(soldUnit.getContractSubject()));
        unitIdField.setValue(uiUtils.getNotNullId(soldUnit.getUnit()));
    }

    @Override
    public void updateFields() {
        soldUnit = (SoldUnit) entity;
        soldUnit.setContractSubject((ContractSubject) uiUtils.getEntityById(contractSubjectIdField.getValue(), ContractSubject.class));
        soldUnit.setUnit((com.bft.spring.model.Unit) uiUtils.getEntityById(unitIdField.getValue(), com.bft.spring.model.Unit.class));
    }

    private void createEditFields() {
        contractSubjectIdField = uiUtils.createCombo("SoldUnit.contractSubjectId", uiUtils.createIdContainer(ContractSubject.class.getSimpleName()));
        unitIdField = uiUtils.createCombo("SoldUnit.unitId", uiUtils.createIdContainer(Unit.class.getSimpleName()));
    }

}
