package com.bft.spring.ui;

/**
 * Created by rev on 06.01.2018.
 */
import com.bft.spring.model.Contract;
import com.bft.spring.model.CustomerCompany;
import com.bft.spring.model.Subsidiary;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

import java.util.List;

@org.springframework.stereotype.Component("customerCompanyView")
public class CustomerCompanyView extends BaseView {

    private CustomerCompany customerCompany;

    private ComboBox subsidiaryIdField;

    private ComboBox contractIdField;


    public VerticalSplitPanel initContent() {
        createEditFields();

        VerticalLayout editLayout = new VerticalLayout(
                new HorizontalLayout(new Component[]{subsidiaryIdField, contractIdField}));

        container = createContainer(CustomerCompany.class);

        Table table = createTable(getMessage("CustomerCompany.CustomerCompany"), container, new
                Object[]{"id", "subsidiary", "contract"});
        table.setSizeFull();

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    customerCompany = (CustomerCompany) table.getValue();
                    if (customerCompany != null) {
                        updateEditPanelFields();
                    }
                });

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(table, editLayout);

        buttonUpdate.addClickListener(event -> {
            if (customerCompany == null)
                return;
            updateFields();
            getDataBaseService().saveOrUpdate(customerCompany);
            updateContainer(CustomerCompany.class);
        });

        buttonCreate.addClickListener(event -> {
            customerCompany = new CustomerCompany();
            updateFields();
            getDataBaseService().saveOrUpdate(customerCompany);
            updateContainer(CustomerCompany.class);
        });

        buttonDelete.addClickListener(event -> {
            if (customerCompany == null)
                return;
            getDataBaseService().delete(customerCompany);
            updateContainer(CustomerCompany.class);
        });

        return verticalSplitPanel;
    }


    private void updateEditPanelFields() {
        subsidiaryIdField.setValue(getNotNullId(customerCompany.getSubsidiary()));
        contractIdField.setValue(getNotNullId(customerCompany.getContract()));
    }

    private void updateFields() {
        customerCompany.setContract((Contract)getEntityById(contractIdField.getValue(), Contract.class));

        customerCompany.setSubsidiary((Subsidiary)getEntityById(subsidiaryIdField.getValue(), Subsidiary.class));
    }

    private void createEditFields() {
        subsidiaryIdField = createCombo("CustomerCompany.subsidiaryId", createIdContainer(Subsidiary.class.getSimpleName()));
        contractIdField = createCombo("CustomerCompany.contractId", createIdContainer(Contract.class.getSimpleName()));
    }


}
