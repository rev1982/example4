package com.bft.spring.ui;

import com.bft.spring.model.Company;
import com.bft.spring.model.SubdivisionPU;
import com.bft.spring.model.TimeZone;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

import java.util.ArrayList;
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
    private TextField emailField;
    private ComboBox actualCombo;
    private TextField vipField;
    private ComboBox timezoneCombo;
    private ComboBox workFromField;
    private ComboBox workUntilField;
    private ComboBox lunchFromField;
    private ComboBox lunchUntilField;
    private TextField subdivisionDeIdField;
    private ComboBox subdivisionPUIdField;
    private TextField noteField;
    private BeanItemContainer<String> timeContainer;


    public VerticalSplitPanel initContent() {
        initTimeContainer();
        createEditFields();

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{shortNameField, fullNameField, legalAdressField, actualAdressField}),
                new HorizontalLayout(new Component[]{innField, kppField, fiasField, phoneField, emailField, actualCombo, vipField}),
                new HorizontalLayout(new Component[]{timezoneCombo, workFromField, workUntilField, lunchFromField, lunchUntilField}),
                new HorizontalLayout(new Component[]{subdivisionDeIdField, subdivisionPUIdField, noteField}));

        container = createContainer(Company.class);

        Table table = createTable(getMessage("company.Company"), container, new
                Object[]{"id", "shortName", "fullName", "legalAdress", "actualAdress", "inn",
                //"kpp", "fias",
                "phone", "email", "actual",
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

    private ComboBox createTimeCombo(String name) {
        ComboBox timeCombo = new ComboBox(name);
        timeCombo.setContainerDataSource(timeContainer);
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
        emailField.setValue(notNullVal(company.getEmail()));
        actualCombo.setValue(company.getActual() != null ? company.getActual().toString() : null);
        vipField.setValue(notNullVal(company.getVip()));
        timezoneCombo.setValue(getNotNullId(company.getTimeZone()));
        workFromField.setValue(getTimeVal(company.getWorkFrom()));
        workUntilField.setValue(getTimeVal(company.getWorkUntil()));
        lunchFromField.setValue(getTimeVal(company.getLunchFrom()));
        lunchUntilField.setValue(getTimeVal(company.getLunchUntil()));
        subdivisionDeIdField.setValue(company.getSubdivisionDeId() != null ? company.getSubdivisionDeId().toString() : "");
        subdivisionPUIdField.setValue(getNotNullId(company.getSubdivisionPU()));
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
        company.setEmail(emailField.getValue());
        company.setActual(actualCombo.getValue() == null ? null :
                Boolean.parseBoolean(actualCombo.getValue().toString()));
        company.setVip(vipField.getValue());
        company.setTimeZone((TimeZone) getValueById(timezoneCombo.getValue(), TimeZone.class));
        company.setWorkFrom(getTimeFromCombo(workFromField.getValue()));
        company.setWorkUntil(getTimeFromCombo(workUntilField.getValue()));
        company.setLunchFrom(getTimeFromCombo(lunchFromField.getValue()));
        company.setLunchUntil(getTimeFromCombo(lunchUntilField.getValue()));
        company.setSubdivisionDeId(subdivisionDeIdField.getValue() == null || subdivisionDeIdField.getValue().length() == 0 ?
                null : Long.parseLong(subdivisionDeIdField.getValue()));
        company.setSubdivisionPU((SubdivisionPU)getValueById(subdivisionPUIdField.getValue(), SubdivisionPU.class));
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
        emailField = createTextField(getMessage("company.email"));
        actualCombo = createCombo("company.actual", createBooleanStringContainer());
        vipField = createTextField(getMessage("company.vip"));
        timezoneCombo = createCombo("company.timeZone",createIdContainer(TimeZone.class.getSimpleName(), "id"));
        workFromField = createTimeCombo(getMessage("company.workFrom"));
        workUntilField = createTimeCombo(getMessage("company.workUntil"));
        lunchFromField = createTimeCombo(getMessage("company.lunchFrom"));
        lunchUntilField = createTimeCombo(getMessage("company.lunchUntil"));
        subdivisionDeIdField = createTextField(getMessage("company.subdivisionDeId"));
        subdivisionPUIdField = createCombo("company.subdivisionPU", createIdContainer(SubdivisionPU.class.getSimpleName(),"id"));
        noteField = createTextField(getMessage("company.note"));
    }

    private void initTimeContainer() {
        List<String> times = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            times.add(i < 10 ? "0" + i + ":00" : i + ":00");
            times.add(i < 10 ? "0" + i + ":30" : i + ":30");
        }
        timeContainer = new BeanItemContainer<>(String.class);
        timeContainer.addAll(times);
    }

}
