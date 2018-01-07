package com.bft.spring.ui;

import com.bft.spring.model.*;
import com.vaadin.data.Property;
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

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{customerCompanyIdField, contractSubjectIdField, serviceIdField, priorityIdField, slaSecFild}));

        container = createContainer(Sla.class);

        Table table = createTable(getMessage("sla.sla"), container, new
                Object[]{"id", "customerCompany", "contractSubject", "service", "priority", "slaSec"});
        table.setSizeFull();

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    sla = (Sla) table.getValue();
                    if (sla != null) {
                        updateEditPanelFields();
                    }
                });

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(table, editLayout);

        buttonUpdate.addClickListener(event -> {
            if (sla == null)
                return;
            updateFields();
            getDataBaseService().saveOrUpdate(sla);
            updateContainer(Sla.class);
        });

        buttonCreate.addClickListener(event -> {
            sla = new Sla();
            updateFields();
            getDataBaseService().saveOrUpdate(sla);
            updateContainer(Sla.class);
        });

        buttonDelete.addClickListener(event -> {
            if (sla == null)
                return;
            getDataBaseService().delete(sla);
            updateContainer(Sla.class);
        });

        return verticalSplitPanel;
    }

    private void updateEditPanelFields() {
        contractSubjectIdField.setValue(getNotNullId(sla.getContractSubject()));
        serviceIdField.setValue(getNotNullId(sla.getService()));
        priorityIdField.setValue(getNotNullId(sla.getPriority()));
        contractSubjectIdField.setValue(getNotNullId(sla.getContractSubject()));
        slaSecFild.setValue(sla.getSlaSec() == null ? "" : sla.getSlaSec().toString());
    }

    private void updateFields() {
        sla.setContractSubject((ContractSubject) getValueById(contractSubjectIdField.getValue(), ContractSubject.class));
        sla.setService((Service) getValueById(serviceIdField.getValue(), Service.class));
        sla.setPriority((Priority) getValueById(priorityIdField.getValue(), Priority.class));
        sla.setCustomerCompany((CustomerCompany) getValueById(customerCompanyIdField.getValue(), CustomerCompany.class));
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
