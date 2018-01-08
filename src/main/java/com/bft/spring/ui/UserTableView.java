package com.bft.spring.ui;

import com.bft.spring.model.Company;
import com.bft.spring.model.UserTable;
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
        entityClass = UserTable.class;

        VerticalLayout editLayout = new VerticalLayout(
                new HorizontalLayout(new Component[]{userNameField, mobilePhoneField, workPhoneField, skypeField}),
                new HorizontalLayout(new Component[]{icqField, positionField, companyIdField, blockingCauseField}));

        container = createContainer(UserTable.class);

        table = createTable(getMessage("user.User"), container, new
                Object[]{"id", "userName", "mobilePhone", "workPhone", "skype", "icq",
                "position", "company",
                "blockingCause"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }


    @Override
    public void updateEditPanelFields() {
        userTable = (UserTable) entity;
        userNameField.setValue(notNullVal(userTable.getUserName()));
        mobilePhoneField.setValue(notNullVal(userTable.getMobilePhone()));
        workPhoneField.setValue(notNullVal(userTable.getWorkPhone()));
        skypeField.setValue(notNullVal(userTable.getSkype()));
        icqField.setValue(notNullVal(userTable.getIcq()));
        positionField.setValue(notNullVal(userTable.getPosition()));
        blockingCauseField.setValue(notNullVal(userTable.getBlockingCause()));
        companyIdField.setValue(getNotNullId(userTable.getCompany()));
    }

    @Override
    public void updateFields() {
        userTable = (UserTable) entity;
        userTable.setBlockingCause(blockingCauseField.getValue());
        userTable.setCompany((Company) getEntityById(companyIdField.getValue(), Company.class));
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
        companyIdField = createCombo("user.company", createIdContainer(Company.class.getSimpleName()));
    }

}
