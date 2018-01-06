package com.bft.spring.ui;

/**
 * Created by rev on 06.01.2018.
 */
import com.bft.spring.model.Company;
import com.bft.spring.model.Subsidiary;
import com.bft.spring.model.TimeZone;
import com.bft.spring.model.UserTable;
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

    private ComboBox companyIdField;



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
            updateUserTableFields();
            getDataBaseService().saveOrUpdate(subsidiary);
            updateContainer(UserTable.class);
        });

        buttonCreate.addClickListener(event -> {
            subsidiary = new Subsidiary();
            updateUserTableFields();
            getDataBaseService().saveOrUpdate(subsidiary);
            updateContainer(UserTable.class);
        });

        buttonDelete.addClickListener(event -> {
            if (subsidiary == null)
                return;
            getDataBaseService().delete(subsidiary);
            updateContainer(UserTable.class);
        });

        return verticalSplitPanel;
    }

    private BeanItemContainer<String> createCompanyFullNameContainer() {
        List<Company> result = getDataBaseService().findAll(Company.class);
        BeanItemContainer<String> container = new BeanItemContainer<>(String.class);
        for (Company s : result) {
            container.addItem(s.getFullName());
        }
        return container;
    }

    private ComboBox createCompanyCombo(String s) {
        ComboBox companyCombo = new ComboBox(getMessage(s));
        companyCombo.setContainerDataSource(createCompanyFullNameContainer());
        return companyCombo;
    }


    private void updateEditPanelFields() {
        subsidiaryCompanyIdField.setValue(subsidiary.getSubsidiaryCompany() != null ? subsidiary.getSubsidiaryCompany().getFullName() : "");
        parentCompanyIdField.setValue(subsidiary.getParentCompany() != null ? subsidiary.getParentCompany().getFullName() : "");
    }

    private void updateUserTableFields() {
        subsidiary.setSubsidiaryCompany(subsidiaryCompanyIdField.getValue() == null ? null :
                (Company) getDataBaseService().findByFullName(subsidiaryCompanyIdField.getValue().toString(), Company.class));

        subsidiary.setParentCompany(parentCompanyIdField.getValue() == null ? null :
                (Company) getDataBaseService().findByFullName(parentCompanyIdField.getValue().toString(), Company.class));
    }

    private void createEditFields() {
        subsidiaryCompanyIdField = createCompanyCombo("subsidiaryCompany.subsidiary");
        parentCompanyIdField = createCompanyCombo("parentCompany.subsidiary");
    }


}