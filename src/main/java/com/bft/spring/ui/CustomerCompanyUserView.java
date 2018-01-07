package com.bft.spring.ui;

/**
 * Created by rev on 06.01.2018.
 */
import com.bft.spring.model.*;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;


@org.springframework.stereotype.Component("customerCompanyUserView")
public class CustomerCompanyUserView extends BaseView {

    private CustomerCompanyUser customerCompanyUser;

    private ComboBox customerCompanyIdField;

    private ComboBox userIdField;


    public VerticalSplitPanel initContent() {
        createEditFields();

        VerticalLayout editLayout = new VerticalLayout(
                new HorizontalLayout(new Component[]{customerCompanyIdField,userIdField}));

        container = createContainer(CustomerCompanyUser.class);

        Table table = createTable(getMessage("CustomerCompanyUser.CustomerCompanyUser"), container, new
                Object[]{"id", "customerCompany", "user"});
        table.setSizeFull();

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    customerCompanyUser = (CustomerCompanyUser) table.getValue();
                    if (customerCompanyUser != null) {
                        updateEditPanelFields();
                    }
                });

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(table, editLayout);

        buttonUpdate.addClickListener(event -> {
            if (customerCompanyUser == null)
                return;
            updateFields();
            getDataBaseService().saveOrUpdate(customerCompanyUser);
            updateContainer(CustomerCompanyUser.class);
        });

        buttonCreate.addClickListener(event -> {
            customerCompanyUser = new CustomerCompanyUser();
            updateFields();
            getDataBaseService().saveOrUpdate(customerCompanyUser);
            updateContainer(CustomerCompanyUser.class);
        });

        buttonDelete.addClickListener(event -> {
            if (customerCompanyUser == null)
                return;
            getDataBaseService().delete(customerCompanyUser);
            updateContainer(CustomerCompanyUser.class);
        });

        return verticalSplitPanel;
    }


    private void updateEditPanelFields() {
        customerCompanyIdField.setValue(getNotNullId(customerCompanyUser.getCustomerCompany()));
        userIdField.setValue(getNotNullId(customerCompanyUser.getUser()));
    }

    private void updateFields() {
        customerCompanyUser.setUser((UserTable)getEntityById(userIdField.getValue(), UserTable.class));

        customerCompanyUser.setCustomerCompany((CustomerCompany)getEntityById(customerCompanyIdField.getValue(), CustomerCompany.class));
    }

    private void createEditFields() {
        userIdField = createCombo("CustomerCompanyUser.userId", createIdContainer(UserTable.class.getSimpleName()));
        customerCompanyIdField = createCombo("CustomerCompanyUser.customerCompanyId", createIdContainer(CustomerCompany.class.getSimpleName()));
    }


}