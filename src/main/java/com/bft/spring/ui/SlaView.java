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

        container = uiUtils.createContainer(Sla.class);

        table = createTable(getMessage("sla.sla"), container, new
                Object[]{"id", "customerCompany", "contractSubject", "service", "priority", "slaSec"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }

    @Override
    public void updateEditPanelFields() {
        sla = (Sla) entity;
        contractSubjectIdField.setValue(uiUtils.getNotNullId(sla.getContractSubject()));
        serviceIdField.setValue(uiUtils.getNotNullId(sla.getService()));
        priorityIdField.setValue(uiUtils.getNotNullId(sla.getPriority()));
        contractSubjectIdField.setValue(uiUtils.getNotNullId(sla.getContractSubject()));
        slaSecFild.setValue(sla.getSlaSec() == null ? "" : sla.getSlaSec().toString());
    }

    @Override
    public void updateFields() {
        sla = (Sla) entity;
        sla.setContractSubject((ContractSubject) uiUtils.getEntityById(contractSubjectIdField.getValue(), ContractSubject.class));
        sla.setService((Service) uiUtils.getEntityById(serviceIdField.getValue(), Service.class));
        sla.setPriority((Priority) uiUtils.getEntityById(priorityIdField.getValue(), Priority.class));
        sla.setCustomerCompany((CustomerCompany) uiUtils.getEntityById(customerCompanyIdField.getValue(), CustomerCompany.class));
        sla.setSlaSec(slaSecFild.getValue() == null ? null : Long.parseLong(slaSecFild.getValue()));
    }

    private void createEditFields() {
        contractSubjectIdField = uiUtils.createCombo("sla.contractSubjectId", uiUtils.createIdContainer(ContractSubject.class.getSimpleName()));
        serviceIdField = uiUtils.createCombo("sla.serviceId", uiUtils.createIdContainer(Service.class.getSimpleName()));
        priorityIdField = uiUtils.createCombo("sla.priorityId", uiUtils.createIdContainer(Priority.class.getSimpleName()));
        customerCompanyIdField = uiUtils.createCombo("sla.customerCompanyId", uiUtils.createIdContainer(CustomerCompany.class.getSimpleName()));
        slaSecFild = createTextField("sla.slaSec");
    }

}
