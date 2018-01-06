package com.bft.spring.ui;

import com.bft.spring.model.Company;
import com.bft.spring.model.SubdivisionPU;
import com.bft.spring.model.TimeZone;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

import java.util.List;


/**
 * Created by rev on 24.12.2017.
 */
@org.springframework.stereotype.Component("companyView")
public class CompanyView extends BaseView {


    private Company company;

    private TextField shortNameField;
    private TextField fullNameField;
    private TextField legalAdressField;
    private TextField actualAdressField;
    private TextField innField;
    private TextField kppField;
    private TextField fiasField;
    private TextField phoneField;
    private ComboBox actualCombo;
    private TextField vipField;
    private ComboBox timezoneCombo;
    private ComboBox workFromField;
    private ComboBox workUntilField;
    private ComboBox lunchFromField;
    private ComboBox lunchUntilField;
    private TextField subdivisionDeIdField;
    private ComboBox subdivisionPUField;
    private TextField noteField;


    public VerticalSplitPanel initContent() {
        createEditFields();

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{shortNameField, fullNameField, legalAdressField, actualAdressField}),
                new HorizontalLayout(new Component[]{innField, kppField, fiasField, phoneField, actualCombo, vipField}),
                new HorizontalLayout(new Component[]{timezoneCombo, workFromField, workUntilField, lunchFromField, lunchUntilField}),
                new HorizontalLayout(new Component[]{subdivisionDeIdField, subdivisionPUField, noteField}));

        container = createContainer(Company.class);

        Table table = createTable(getMessage("company.Company"), container, new
                Object[]{"id", "shortName", "fullName", "legalAdress", "actualAdress", "inn",
                //"kpp", "fias",
                "phone", "actual",
                //"vip", "timeZone", "workFrom", "workUntil", "lunchFrom", "lunchUntil", "subdivisionDeId", "subdivisionPU",
                "note"});
        table.setSizeFull();

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    company = (Company) table.getValue();
                    if (company != null) {
                        updateEditPanelFields();
                    }
                });

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(table, editLayout);

        buttonUpdate.addClickListener(event -> {
            if (company == null)
                return;
            updateCompanyFields();
            getDataBaseService().saveOrUpdate(company);
            updateContainer(Company.class);
        });

        buttonCreate.addClickListener(event -> {
            company = new Company();
            updateCompanyFields();
            getDataBaseService().saveOrUpdate(company);
            updateContainer(Company.class);
        });

        buttonDelete.addClickListener(event -> {
            if (company == null)
                return;
            getDataBaseService().delete(company);
            updateContainer(Company.class);
        });

        return verticalSplitPanel;
    }

    private BeanItemContainer<String> createTimeZoneStringContainer() {
        List<TimeZone> result = getDataBaseService().findAll(TimeZone.class);
        BeanItemContainer<String> container1 = new BeanItemContainer<>(String.class);
        for (TimeZone t : result) {
            container1.addItem(t.getName());
        }
        return container1;
    }

    private ComboBox createTimeZoneCombo() {
        ComboBox timezoneCombo = new ComboBox(getMessage("company.timeZone"));
        timezoneCombo.setContainerDataSource(createTimeZoneStringContainer());
        return timezoneCombo;
    }

    private ComboBox createTimeCombo(String name) {
        ComboBox timeCombo = new ComboBox(name);
        timeCombo.setContainerDataSource(getTimeContainer());
        return timeCombo;
    }

    private void updateEditPanelFields() {
        shortNameField.setValue(notNullVal(company.getShortName()));
        fullNameField.setValue(notNullVal(company.getFullName()));
        legalAdressField.setValue(notNullVal(company.getLegalAdress()));
        actualAdressField.setValue(notNullVal(company.getActualAdress()));
        innField.setValue(notNullVal(company.getInn()));
        kppField.setValue(notNullVal(company.getKpp()));
        fiasField.setValue(notNullVal(company.getFias()));
        phoneField.setValue(notNullVal(company.getPhone()));
        actualCombo.setValue(company.getActual() != null ? company.getActual().toString() : null);
        vipField.setValue(notNullVal(company.getVip()));
        timezoneCombo.setValue(company.getTimeZone() != null ? company.getTimeZone().getName() : "");
        workFromField.setValue(getTimeVal(company.getWorkFrom()));
        workUntilField.setValue(getTimeVal(company.getWorkUntil()));
        lunchFromField.setValue(getTimeVal(company.getLunchFrom()));
        lunchUntilField.setValue(getTimeVal(company.getLunchUntil()));
        subdivisionDeIdField.setValue(company.getSubdivisionDeId() != null ? company.getSubdivisionDeId().toString() : "");
        subdivisionPUField.setValue(company.getSubdivisionPU() != null ? company.getSubdivisionPU().getName() : "");
        noteField.setValue(notNullVal(company.getNote()));
    }

    private void updateCompanyFields() {
        company.setShortName(shortNameField.getValue());
        company.setFullName(fullNameField.getValue());
        company.setLegalAdress(legalAdressField.getValue());
        company.setActualAdress(legalAdressField.getValue());
        company.setInn(innField.getValue());
        company.setKpp(kppField.getValue());
        company.setFias(fiasField.getValue());
        company.setPhone(phoneField.getValue());
        company.setActual(actualCombo.getValue() == null ? null :
                Boolean.parseBoolean(actualCombo.getValue().toString()));
        company.setVip(vipField.getValue());
        company.setTimeZone(timezoneCombo.getValue() == null ? null :
                (TimeZone) getDataBaseService().findByName(timezoneCombo.getValue().toString(), TimeZone.class));
        company.setWorkFrom(getTimeFromCombo(workFromField.getValue()));
        company.setWorkUntil(getTimeFromCombo(workUntilField.getValue()));
        company.setLunchFrom(getTimeFromCombo(lunchFromField.getValue()));
        company.setLunchUntil(getTimeFromCombo(lunchUntilField.getValue()));
        company.setSubdivisionDeId(subdivisionDeIdField.getValue() == null || subdivisionDeIdField.getValue().length() == 0 ?
                null : Long.parseLong(subdivisionDeIdField.getValue()));
        company.setSubdivisionPU(subdivisionPUField.getValue() == null ? null :
                (SubdivisionPU) getDataBaseService().findByName(subdivisionPUField.getValue().toString(), SubdivisionPU.class));
        company.setNote(noteField.getValue());
    }

    private void createEditFields() {
        shortNameField = createTextField(getMessage("company.shortName"));
        fullNameField = createTextField(getMessage("company.fullName"));
        legalAdressField = createTextField(getMessage("company.legalAdress"));
        actualAdressField = createTextField(getMessage("company.actualAdress"));
        innField = createTextField(getMessage("company.inn"));
        kppField = createTextField(getMessage("company.kpp"));
        fiasField = createTextField(getMessage("company.fias"));
        phoneField = createTextField(getMessage("company.phone"));
        actualCombo = createCombo("company.actual", createBooleanStringContainer());
        vipField = createTextField(getMessage("company.vip"));
        timezoneCombo = createTimeZoneCombo();
        workFromField = createTimeCombo(getMessage("company.workFrom"));
        workUntilField = createTimeCombo(getMessage("company.workUntil"));
        lunchFromField = createTimeCombo(getMessage("company.lunchFrom"));
        lunchUntilField = createTimeCombo(getMessage("company.lunchUntil"));
        subdivisionDeIdField = createTextField(getMessage("company.subdivisionDeId"));
        subdivisionPUField = createCombo("company.subdivisionPU", createStringContainer("SubdivisionPU", "name"));
        noteField = createTextField(getMessage("company.note"));
    }

}
