package com.bft.spring.ui;

import com.bft.spring.model.Company;
import com.bft.spring.model.SubdivisionPU;
import com.bft.spring.model.TimeZone;
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
        entityClass = Company.class;
        initTimeContainer();
        createEditFields();

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{shortNameField, fullNameField, legalAdressField, actualAdressField}),
                new HorizontalLayout(new Component[]{innField, kppField, fiasField, phoneField, emailField, actualCombo, vipField}),
                new HorizontalLayout(new Component[]{timezoneCombo, workFromField, workUntilField, lunchFromField, lunchUntilField}),
                new HorizontalLayout(new Component[]{subdivisionDeIdField, subdivisionPUIdField, noteField}));

        container = uiUtils.createContainer(Company.class);

        table = createTable(getMessage("company.Company"), container, new
                Object[]{"id", "shortName", "fullName", "legalAdress", "actualAdress", "inn",
                //"kpp", "fias",
                "phone", "email", "actual",
                //"vip", "timeZone", "workFrom", "workUntil", "lunchFrom", "lunchUntil", "subdivisionDeId", "subdivisionPU",
                "note"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }

    private ComboBox createTimeCombo(String name) {
        ComboBox timeCombo = new ComboBox(name);
        timeCombo.setContainerDataSource(timeContainer);
        return timeCombo;
    }

    @Override
    public void updateEditPanelFields() {
        company = (Company) entity;
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
        timezoneCombo.setValue(uiUtils.getNotNullId(company.getTimeZone()));
        workFromField.setValue(uiUtils.getTimeVal(company.getWorkFrom()));
        workUntilField.setValue(uiUtils.getTimeVal(company.getWorkUntil()));
        lunchFromField.setValue(uiUtils.getTimeVal(company.getLunchFrom()));
        lunchUntilField.setValue(uiUtils.getTimeVal(company.getLunchUntil()));
        subdivisionDeIdField.setValue(company.getSubdivisionDeId() != null ? company.getSubdivisionDeId().toString() : "");
        subdivisionPUIdField.setValue(uiUtils.getNotNullId(company.getSubdivisionPU()));
        noteField.setValue(notNullVal(company.getNote()));
    }

    @Override
    public void updateFields() {
        company = (Company) entity;
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
        company.setTimeZone((TimeZone) uiUtils.getEntityById(timezoneCombo.getValue(), TimeZone.class));
        company.setWorkFrom(uiUtils.getTimeFromCombo(workFromField.getValue()));
        company.setWorkUntil(uiUtils.getTimeFromCombo(workUntilField.getValue()));
        company.setLunchFrom(uiUtils.getTimeFromCombo(lunchFromField.getValue()));
        company.setLunchUntil(uiUtils.getTimeFromCombo(lunchUntilField.getValue()));
        company.setSubdivisionDeId(subdivisionDeIdField.getValue() == null || subdivisionDeIdField.getValue().length() == 0 ?
                null : Long.parseLong(subdivisionDeIdField.getValue()));
        company.setSubdivisionPU((SubdivisionPU) uiUtils.getEntityById(subdivisionPUIdField.getValue(), SubdivisionPU.class));
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
        actualCombo = uiUtils.createCombo("company.actual", uiUtils.createBooleanStringContainer());
        vipField = createTextField(getMessage("company.vip"));
        timezoneCombo = uiUtils.createCombo("company.timeZone", uiUtils.createIdContainer(TimeZone.class.getSimpleName()));
        workFromField = createTimeCombo(getMessage("company.workFrom"));
        workUntilField = createTimeCombo(getMessage("company.workUntil"));
        lunchFromField = createTimeCombo(getMessage("company.lunchFrom"));
        lunchUntilField = createTimeCombo(getMessage("company.lunchUntil"));
        subdivisionDeIdField = createTextField(getMessage("company.subdivisionDeId"));
        subdivisionPUIdField = uiUtils.createCombo("company.subdivisionPU", uiUtils.createIdContainer(SubdivisionPU.class.getSimpleName()));
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
