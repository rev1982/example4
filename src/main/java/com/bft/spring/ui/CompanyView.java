package com.bft.spring.ui;

import com.bft.spring.AppMain;
import com.bft.spring.dao.DaoImpl;
import com.bft.spring.model.Company;
import com.bft.spring.model.DomainEntity;
import com.bft.spring.model.TimeZone;
import com.bft.spring.service.DataBaseService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rev on 24.12.2017.
 */
@Repository
@ComponentScan(basePackages = "com.bft.spring")
@SpringUI
@Theme("valo")
public class CompanyView extends BaseView {


    private Company company;
    private TextField fullNameField;
    private TextField phoneField;
    private TextField kppField;
    private TextField timeZoneField;
    private BeanItemContainer<Company> companies;

    public  VerticalLayout  init(){
        fullNameField = createTextField("fullName");
        timeZoneField = createTextField("timeZone");
        phoneField = createTextField("phone num");
        kppField = createTextField("kpp");
        HorizontalLayout editLayout = createEditHorizontalLayout(new TextField[]{fullNameField,timeZoneField,phoneField,kppField});

        companies =
                new BeanItemContainer<>(Company.class);

        companies.addAll(dataBaseService.findAll(Company.class));

        Table table = createTable("Company", companies, new
                Object[]{"id", "fullName", "timeZone",
                "phone", "kpp"});

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    company = (Company) table.getValue();
                    if (company != null) {
                        fullNameField.setValue(company.getFullName() != null ? company.getFullName() : fullNameField.getValue());
                        timeZoneField.setValue(company.getTimeZone() != null ? company.getTimeZone().getName() : timeZoneField.getValue());
                        phoneField.setValue(company.getPhone() != null ? company.getPhone() : phoneField.getValue());
                        kppField.setValue(company.getKpp() != null ? company.getKpp() : kppField.getValue());
                    }

                });


        Button button = new Button("update");

        VerticalLayout verticalLayout = new VerticalLayout( table, editLayout, button);
        verticalLayout.setMargin(true);
        verticalLayout.setSpacing(true);


        button.addClickListener(event -> {
            if (company == null)
                return;
            company.setFullName(fullNameField.getValue());
            company.setKpp(kppField.getValue());
            company.setPhone(phoneField.getValue());
            company.setTimeZone((TimeZone) dataBaseService.findByName(timeZoneField.getValue(), TimeZone.class));
            dataBaseService.saveOrUpdate(company);
            companies.removeAllItems();
            companies.addAll(dataBaseService.findAll(Company.class));
        });


        return  verticalLayout;
    }

}
