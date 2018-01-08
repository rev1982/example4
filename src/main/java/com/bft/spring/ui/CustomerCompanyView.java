package com.bft.spring.ui;

/**
 * Created by rev on 06.01.2018.
 */
import com.bft.spring.model.Contract;
import com.bft.spring.model.CustomerCompany;
import com.bft.spring.model.Subsidiary;
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
        entityClass = CustomerCompany.class;

        VerticalLayout editLayout = new VerticalLayout(
                new HorizontalLayout(new Component[]{subsidiaryIdField, contractIdField}));

        container = createContainer(CustomerCompany.class);

        table = createTable(getMessage("CustomerCompany.CustomerCompany"), container, new
                Object[]{"id", "subsidiary", "contract"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }


    @Override
    public void updateEditPanelFields() {
        customerCompany = (CustomerCompany) entity;
        subsidiaryIdField.setValue(getNotNullId(customerCompany.getSubsidiary()));
        contractIdField.setValue(getNotNullId(customerCompany.getContract()));
    }

    @Override
    public void updateFields() {
        customerCompany = (CustomerCompany) entity;
        customerCompany.setContract((Contract)getEntityById(contractIdField.getValue(), Contract.class));

        customerCompany.setSubsidiary((Subsidiary)getEntityById(subsidiaryIdField.getValue(), Subsidiary.class));
    }

    private void createEditFields() {
        subsidiaryIdField = createCombo("CustomerCompany.subsidiaryId", createIdContainer(Subsidiary.class.getSimpleName()));
        contractIdField = createCombo("CustomerCompany.contractId", createIdContainer(Contract.class.getSimpleName()));
    }


}
