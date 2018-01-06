package com.bft.spring.ui;

/**
 * Created by rev on 06.01.2018.
 */
import com.bft.spring.model.*;
import com.vaadin.data.Property;
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

        VerticalLayout editLayout = new VerticalLayout(
                new HorizontalLayout(new Component[]{customerCompanyIdField, contractDateField, numberField, puNumberField, statusField, puProjectField}),
                new HorizontalLayout(new Component[]{subdivisionPuIdField, validFromField, validUntilField, isValidField, contractTypeIdField}));

        container = createContainer(Contract.class);

        Table table = createTable(getMessage("contract.Contract"), container, new
                Object[]{"id", "customerCompany", "contractDate", "number", "puNumber", "status", "puProject", "subdivisionPU",
                "validFrom", "validUntil", "isValid", "contractType" });
        table.setSizeFull();

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    contract = (Contract) table.getValue();
                    if (contract != null) {
                        updateEditPanelFields();
                    }
                });

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(table, editLayout);

        buttonUpdate.addClickListener(event -> {
            if (contract == null)
                return;
            updateFields();
            getDataBaseService().saveOrUpdate(contract);
            updateContainer(Contract.class);
        });

        buttonCreate.addClickListener(event -> {
            contract = new Contract();
            updateFields();
            getDataBaseService().saveOrUpdate(contract);
            updateContainer(Contract.class);
        });

        buttonDelete.addClickListener(event -> {
            if (contract == null)
                return;
            getDataBaseService().delete(contract);
            updateContainer(Contract.class);
        });

        return verticalSplitPanel;
    }


    private void updateEditPanelFields() {
        customerCompanyIdField.setValue(contract.getCustomerCompany() != null ? contract.getCustomerCompany().getFullName() : "");
        contractDateField.setValue(contract.getContractDate());
        numberField.setValue(contract.getNumber());
        puNumberField.setValue(contract.getPuNumber());
        statusField.setValue(contract.getStatus());
        puProjectField.setValue(contract.getPuProject());
        subdivisionPuIdField.setValue(contract.getSubdivisionPU() != null ? contract.getSubdivisionPU().getName() : "");
        validFromField.setValue(contract.getValidFrom());
        validUntilField.setValue(contract.getValidUntil());
        isValidField.setValue(contract.getIsValid() != null ? contract.getIsValid() : "");
        contractTypeIdField.setValue(contract.getContractType() != null ? contract.getContractType().getName() : "");
    }

    private void updateFields() {
        contract.setCustomerCompany(customerCompanyIdField.getValue() != null ?
                (Company) getDataBaseService().findByFullName(customerCompanyIdField.getValue().toString(), Company.class) : null);
        contract.setContractDate(contractDateField.getValue() != null ?
                new Date(contractDateField.getValue().getTime()) : null);
        contract.setNumber(numberField.getValue());
        contract.setPuNumber(puNumberField.getValue());
        contract.setStatus(statusField.getValue());
        contract.setPuProject(puProjectField.getValue());
        contract.setSubdivisionPU(subdivisionPuIdField.getValue() == null ? null :
                (SubdivisionPU) getDataBaseService().findByName(subdivisionPuIdField.getValue().toString(), SubdivisionPU.class));
        contract.setValidFrom(validFromField.getValue() != null ? new Date(validFromField.getValue().getTime()) : null);
        contract.setValidUntil(validUntilField.getValue() != null ?
                new Date(validUntilField.getValue().getTime()) : null);
        contract.setIsValid(isValidField.getValue() != null ? Boolean.valueOf(isValidField.getValue().toString()) : null);
        contract.setContractType(contractTypeIdField.getValue() == null ? null :
                (ContractType) getDataBaseService().findByName(contractTypeIdField.getValue().toString(), ContractType.class));
    }

    private void createEditFields() {
        customerCompanyIdField = createCombo("contract.customerCompany", createStringContainer(Company.class.getSimpleName(), "fullName"));
        contractDateField = new DateField("contract.date");
        numberField = new TextField(getMessage("contract.number"));
        puNumberField = new TextField(getMessage("contract.puNumber"));
        statusField = new TextField(getMessage("contract.status"));
        puProjectField = new TextField(getMessage("contract.puProject"));
        subdivisionPuIdField = createCombo("contract.subdivisionPu", createStringContainer(SubdivisionPU.class.getSimpleName(), "name"));
        validFromField = new DateField(getMessage("contract.validFrom"));
        validUntilField = new DateField(getMessage("contract.validUntil"));
        isValidField = createCombo("contract.isValid", createBooleanStringContainer());
        contractTypeIdField = createCombo("contract.contractType", createStringContainer(ContractType.class.getSimpleName(), "name"));
    }


}