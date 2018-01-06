package com.bft.spring.ui;

import com.bft.spring.model.Company;
import com.bft.spring.model.UserTable;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

import java.util.List;


/**
 * Created by rev on 24.12.2017.
 */
@org.springframework.stereotype.Component("userTableView")
public class UserTableView extends BaseView {


    private UserTable userTable;

    private TextField userNameField;
    private TextField mobilePhoneField;
    private TextField workPhoneField;
    private TextField skypeField;
    private TextField icqField;
    private TextField positionField;
    private ComboBox companyIdField;
    private TextField blockingCauseField;


    public VerticalSplitPanel initContent() {
        createEditFields();

        VerticalLayout editLayout = new VerticalLayout(
                new HorizontalLayout(new Component[]{userNameField, mobilePhoneField, workPhoneField, skypeField}),
                new HorizontalLayout(new Component[]{icqField, positionField, companyIdField, blockingCauseField}));

        container = createContainer(UserTable.class);

        Table table = createTable(getMessage("user.User"), container, new
                Object[]{"id", "userName", "mobilePhone", "workPhone", "skype", "icq",
                "position", "company",
                "blockingCause"});
        table.setSizeFull();

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    userTable = (UserTable) table.getValue();
                    if (userTable != null) {
                        updateEditPanelFields();
                    }
                });

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(table, editLayout);

        buttonUpdate.addClickListener(event -> {
            if (userTable == null)
                return;
            updateUserTableFields();
            getDataBaseService().saveOrUpdate(userTable);
            updateContainer(UserTable.class);
        });

        buttonCreate.addClickListener(event -> {
            userTable = new UserTable();
            updateUserTableFields();
            getDataBaseService().saveOrUpdate(userTable);
            updateContainer(UserTable.class);
        });

        buttonDelete.addClickListener(event -> {
            if (userTable == null)
                return;
            getDataBaseService().delete(userTable);
            updateContainer(UserTable.class);
        });

        return verticalSplitPanel;
    }


    private void updateEditPanelFields() {
        userNameField.setValue(notNullVal(userTable.getUserName()));
        mobilePhoneField.setValue(notNullVal(userTable.getMobilePhone()));
        workPhoneField.setValue(notNullVal(userTable.getWorkPhone()));
        skypeField.setValue(notNullVal(userTable.getSkype()));
        icqField.setValue(notNullVal(userTable.getIcq()));
        positionField.setValue(notNullVal(userTable.getPosition()));
        blockingCauseField.setValue(notNullVal(userTable.getBlockingCause()));
        companyIdField.setValue(userTable.getCompany() != null ? userTable.getCompany().getFullName() : "");
    }

    private void updateUserTableFields() {
        userTable.setBlockingCause(blockingCauseField.getValue());
        userTable.setCompany(companyIdField.getValue() == null ? null :
                (Company) getDataBaseService().findByFullName(companyIdField.getValue().toString(), Company.class));
        userTable.setIcq(icqField.getValue());
        userTable.setMobilePhone(mobilePhoneField.getValue());
        userTable.setWorkPhone(workPhoneField.getValue());
        userTable.setPosition(positionField.getValue());
        userTable.setSkype(skypeField.getValue());
        userTable.setUserName(userNameField.getValue());
    }

    private void createEditFields() {
        userNameField = createTextField(getMessage("user.name"));
        mobilePhoneField = createTextField(getMessage("user.mobilePhone"));
        workPhoneField = createTextField(getMessage("user.workPhone"));
        skypeField = createTextField(getMessage("user.skype"));
        icqField = createTextField(getMessage("user.icq"));
        positionField = createTextField(getMessage("user.position"));
        blockingCauseField = createTextField(getMessage("user.blockingCause"));
        companyIdField = createCombo("user.company", createStringContainer(Company.class.getSimpleName(), "fullName"));
    }

}
