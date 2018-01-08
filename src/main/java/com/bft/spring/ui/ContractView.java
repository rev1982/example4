package com.bft.spring.ui;

/**
 * Created by rev on 06.01.2018.
 */
import com.bft.spring.model.*;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

import java.sql.Date;

@org.springframework.stereotype.Component("contractView")
public class ContractView extends BaseView {

    private Contract contract;

    private ComboBox customerCompanyIdField;
    private DateField contractDateField;
    private TextField numberField;
    private TextField puNumberField;
    private TextField statusField;
    private TextField puProjectField;
    private ComboBox subdivisionPuIdField;
    private DateField validFromField;
    private DateField validUntilField;
    private ComboBox isValidField;
    private ComboBox contractTypeIdField;


    public VerticalSplitPanel initContent() {
        createEditFields();
        entityClass = Contract.class;

        VerticalLayout editLayout = new VerticalLayout(
                new HorizontalLayout(new Component[]{customerCompanyIdField, contractDateField, numberField, puNumberField, statusField, puProjectField}),
                new HorizontalLayout(new Component[]{subdivisionPuIdField, validFromField, validUntilField, isValidField, contractTypeIdField}));

        container = createContainer(Contract.class);

        table = createTable(getMessage("contract.Contract"), container, new
                Object[]{"id", "customerCompany", "contractDate", "number", "puNumber", "status", "puProject", "subdivisionPU",
                "validFrom", "validUntil", "isValid", "contractType"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }


    @Override
    public void updateEditPanelFields() {
        contract = (Contract) entity;
        customerCompanyIdField.setValue(getNotNullId(contract.getCustomerCompany()));
        contractDateField.setValue(contract.getContractDate());
        numberField.setValue(contract.getNumber());
        puNumberField.setValue(contract.getPuNumber());
        statusField.setValue(contract.getStatus());
        puProjectField.setValue(contract.getPuProject());
        subdivisionPuIdField.setValue(getNotNullId(contract.getSubdivisionPU()));
        validFromField.setValue(contract.getValidFrom());
        validUntilField.setValue(contract.getValidUntil());
        isValidField.setValue(contract.getIsValid() != null ? contract.getIsValid().toString() : "");
        contractTypeIdField.setValue(getNotNullId(contract.getContractType()));
    }

    @Override
    public void updateFields() {
        contract = (Contract) entity;
        contract.setCustomerCompany((Company) getEntityById(customerCompanyIdField.getValue(), Company.class));
        contract.setContractDate(contractDateField.getValue() != null ?
                new Date(contractDateField.getValue().getTime()) : null);
        contract.setNumber(numberField.getValue());
        contract.setPuNumber(puNumberField.getValue());
        contract.setStatus(statusField.getValue());
        contract.setPuProject(puProjectField.getValue());
        contract.setSubdivisionPU((SubdivisionPU) getEntityById(subdivisionPuIdField.getValue(), SubdivisionPU.class));
        contract.setValidFrom(validFromField.getValue() != null ? new Date(validFromField.getValue().getTime()) : null);
        contract.setValidUntil(validUntilField.getValue() != null ?
                new Date(validUntilField.getValue().getTime()) : null);
        contract.setIsValid(isValidField.getValue() != null ? Boolean.valueOf(isValidField.getValue().toString()) : null);
        contract.setContractType((ContractType) getEntityById(contractTypeIdField.getValue(), ContractType.class));
    }

    private void createEditFields() {
        customerCompanyIdField = createCombo("contract.customerCompany", createIdContainer(Company.class.getSimpleName()));
        contractDateField = new DateField("contract.date");
        numberField = new TextField(getMessage("contract.number"));
        puNumberField = new TextField(getMessage("contract.puNumber"));
        statusField = new TextField(getMessage("contract.status"));
        puProjectField = new TextField(getMessage("contract.puProject"));
        subdivisionPuIdField = createCombo("contract.subdivisionPu", createIdContainer(SubdivisionPU.class.getSimpleName()));
        validFromField = new DateField(getMessage("contract.validFrom"));
        validUntilField = new DateField(getMessage("contract.validUntil"));
        isValidField = createCombo("contract.isValid", createBooleanStringContainer());
        contractTypeIdField = createCombo("contract.contractType", createIdContainer(ContractType.class.getSimpleName()));
    }


}