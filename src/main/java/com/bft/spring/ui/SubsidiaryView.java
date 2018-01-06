package com.bft.spring.ui;

/**
 * Created by rev on 06.01.2018.
 */
import com.bft.spring.model.Company;
import com.bft.spring.model.Subsidiary;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
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

        VerticalLayout editLayout = new VerticalLayout(
                new HorizontalLayout(new Component[]{subsidiaryCompanyIdField, parentCompanyIdField}));

        container = createContainer(Subsidiary.class);

        Table table = createTable(getMessage("subsidiary.Subsidiary"), container, new
                Object[]{"id", "subsidiaryCompany", "parentCompany"});
        table.setSizeFull();

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    subsidiary = (Subsidiary) table.getValue();
                    if (subsidiary != null) {
                        updateEditPanelFields();
                    }
                });

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(table, editLayout);

        buttonUpdate.addClickListener(event -> {
            if (subsidiary == null)
                return;
            updateFields();
            getDataBaseService().saveOrUpdate(subsidiary);
            updateContainer(Subsidiary.class);
        });

        buttonCreate.addClickListener(event -> {
            subsidiary = new Subsidiary();
            updateFields();
            getDataBaseService().saveOrUpdate(subsidiary);
            updateContainer(Subsidiary.class);
        });

        buttonDelete.addClickListener(event -> {
            if (subsidiary == null)
                return;
            getDataBaseService().delete(subsidiary);
            updateContainer(Subsidiary.class);
        });

        return verticalSplitPanel;
    }


    private void updateEditPanelFields() {
        subsidiaryCompanyIdField.setValue(subsidiary.getSubsidiaryCompany() != null ? subsidiary.getSubsidiaryCompany().getFullName() : "");
        parentCompanyIdField.setValue(subsidiary.getParentCompany() != null ? subsidiary.getParentCompany().getFullName() : "");
    }

    private void updateFields() {
        subsidiary.setSubsidiaryCompany(subsidiaryCompanyIdField.getValue() == null ? null :
                (Company) getDataBaseService().findByFullName(subsidiaryCompanyIdField.getValue().toString(), Company.class));

        subsidiary.setParentCompany(parentCompanyIdField.getValue() == null ? null :
                (Company) getDataBaseService().findByFullName(parentCompanyIdField.getValue().toString(), Company.class));
    }

    private void createEditFields() {
        subsidiaryCompanyIdField = createCombo("subsidiary.subsidiaryCompany", createStringContainer("Company", "fullName"));
        parentCompanyIdField = createCombo("subsidiary.parentCompany", createStringContainer("Company", "fullName"));
    }


}