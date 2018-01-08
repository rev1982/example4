package com.bft.spring.ui;

/**
 * Created by rev on 06.01.2018.
 */
import com.bft.spring.model.*;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;


@org.springframework.stereotype.Component("customerCompanyUserView")
public class CustomerCompanyUserView extends BaseView {

    private CustomerCompanyUser customerCompanyUser;

    private ComboBox customerCompanyIdField;

    private ComboBox userIdField;


    public VerticalSplitPanel initContent() {
        createEditFields();
        entityClass = CustomerCompanyUser.class;

        VerticalLayout editLayout = new VerticalLayout(
                new HorizontalLayout(new Component[]{customerCompanyIdField,userIdField}));

        container = createContainer(CustomerCompanyUser.class);

        table = createTable(getMessage("CustomerCompanyUser.CustomerCompanyUser"), container, new
                Object[]{"id", "customerCompany", "user"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }


    @Override
    public void updateEditPanelFields() {
        customerCompanyUser = (CustomerCompanyUser) entity;
        customerCompanyIdField.setValue(getNotNullId(customerCompanyUser.getCustomerCompany()));
        userIdField.setValue(getNotNullId(customerCompanyUser.getUser()));
    }

    @Override
    public void updateFields() {
        customerCompanyUser = (CustomerCompanyUser) entity;
        customerCompanyUser.setUser((UserTable)getEntityById(userIdField.getValue(), UserTable.class));

        customerCompanyUser.setCustomerCompany((CustomerCompany)getEntityById(customerCompanyIdField.getValue(), CustomerCompany.class));
    }

    private void createEditFields() {
        userIdField = createCombo("CustomerCompanyUser.userId", createIdContainer(UserTable.class.getSimpleName()));
        customerCompanyIdField = createCombo("CustomerCompanyUser.customerCompanyId", createIdContainer(CustomerCompany.class.getSimpleName()));
    }


}