package com.bft.spring.ui;

/**
 * Created by rev on 06.01.2018.
 */
import com.bft.spring.model.Company;
import com.bft.spring.model.Subsidiary;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

import java.util.List;

@org.springframework.stereotype.Component("subsidiaryView")
public class SubsidiaryView extends BaseView {

    private Subsidiary subsidiary;

    private ComboBox subsidiaryCompanyIdField;

    private ComboBox parentCompanyIdField;


    public VerticalSplitPanel initContent() {
        createEditFields();
        entityClass = Subsidiary.class;

        VerticalLayout editLayout = new VerticalLayout(
                new HorizontalLayout(new Component[]{subsidiaryCompanyIdField, parentCompanyIdField}));

        container = uiUtils.createContainer(Subsidiary.class);

        table = createTable(getMessage("subsidiary.Subsidiary"), container, new
                Object[]{"id", "subsidiaryCompany", "parentCompany"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }


    @Override
    public void updateEditPanelFields() {
        subsidiary = (Subsidiary)entity;
        subsidiaryCompanyIdField.setValue(uiUtils.getNotNullId(subsidiary.getSubsidiaryCompany()));
        parentCompanyIdField.setValue(uiUtils.getNotNullId(subsidiary.getParentCompany()));
    }

    @Override
    public void updateFields() {
        subsidiary = (Subsidiary) entity;
        subsidiary.setSubsidiaryCompany((Company) uiUtils.getEntityById(subsidiaryCompanyIdField.getValue(), Company.class));
        subsidiary.setParentCompany((Company) uiUtils.getEntityById(parentCompanyIdField.getValue(), Company.class));
    }

    private void createEditFields() {
        subsidiaryCompanyIdField = uiUtils.createCombo("subsidiary.subsidiaryCompany", uiUtils.createIdContainer(Company.class.getSimpleName()));
        parentCompanyIdField = uiUtils.createCombo("subsidiary.parentCompany", uiUtils.createIdContainer(Company.class.getSimpleName()));
    }


}