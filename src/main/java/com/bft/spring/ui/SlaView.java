package com.bft.spring.ui;

import com.bft.spring.model.*;
import com.vaadin.ui.*;

/**
 * Created by rev on 07.01.2018.
 */
@org.springframework.stereotype.Component("slaView")
public class SlaView extends BaseView {

    private Sla sla;

    private ComboBox customerCompanyIdField;

    private ComboBox serviceIdField;

    private ComboBox priorityIdField;

    private ComboBox contractSubjectIdField;

    private TextField slaSecFild;


    public VerticalSplitPanel initContent() {
        createEditFields();
        entityClass = Sla.class;

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{customerCompanyIdField, contractSubjectIdField, serviceIdField, priorityIdField, slaSecFild}));

        container = createContainer(Sla.class);

        table = createTable(getMessage("sla.sla"), container, new
                Object[]{"id", "customerCompany", "contractSubject", "service", "priority", "slaSec"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }

    @Override
    public void updateEditPanelFields() {
        sla = (Sla) entity;
        contractSubjectIdField.setValue(getNotNullId(sla.getContractSubject()));
        serviceIdField.setValue(getNotNullId(sla.getService()));
        priorityIdField.setValue(getNotNullId(sla.getPriority()));
        contractSubjectIdField.setValue(getNotNullId(sla.getContractSubject()));
        slaSecFild.setValue(sla.getSlaSec() == null ? "" : sla.getSlaSec().toString());
    }

    @Override
    public void updateFields() {
        sla = (Sla) entity;
        sla.setContractSubject((ContractSubject) getEntityById(contractSubjectIdField.getValue(), ContractSubject.class));
        sla.setService((Service) getEntityById(serviceIdField.getValue(), Service.class));
        sla.setPriority((Priority) getEntityById(priorityIdField.getValue(), Priority.class));
        sla.setCustomerCompany((CustomerCompany) getEntityById(customerCompanyIdField.getValue(), CustomerCompany.class));
        sla.setSlaSec(slaSecFild.getValue() == null ? null : Long.parseLong(slaSecFild.getValue()));
    }

    private void createEditFields() {
        contractSubjectIdField = createCombo("sla.contractSubjectId", createIdContainer(ContractSubject.class.getSimpleName()));
        serviceIdField = createCombo("sla.serviceId", createIdContainer(Service.class.getSimpleName()));
        priorityIdField = createCombo("sla.priorityId", createIdContainer(Priority.class.getSimpleName()));
        customerCompanyIdField = createCombo("sla.customerCompanyId", createIdContainer(CustomerCompany.class.getSimpleName()));
        slaSecFild = createTextField("sla.slaSec");
    }

}
