package com.bft.spring;

import com.bft.spring.model.Company;
import com.bft.spring.model.SubdivisionPU;
import com.bft.spring.model.TimeZone;
import com.bft.spring.service.DataBaseService;
import com.bft.spring.ui.BaseView;
import com.bft.spring.ui.CompanyView;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.sql.Time;
import java.time.LocalTime;


/**
 * Created by rev on 21.12.2017.
 */
@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    public static boolean created;

    @Autowired
    private DataBaseService dataBaseService;

    @Autowired
    ResourceBundleMessageSource messageSource;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        if (!created) {
            addCompany("tz3", "sPU3", "company3");
            addCompany("tz4", "sPU4", "company4");
            created = true;
        }

        BaseView baseView = new BaseView();
        baseView.setDataBaseService(dataBaseService);
        baseView.setMessageSource(messageSource);

        VerticalLayout verticalLayout = new VerticalLayout(new CompanyView().init());
        verticalLayout.setSizeFull();

        setContent(verticalLayout);
    }

    private void addCompany(String timeZoneName, String subdivisionPUName, String companyName) {
        TimeZone timeZone = (TimeZone)dataBaseService.findByName(timeZoneName, TimeZone.class);
        if (timeZone == null){
            timeZone = new TimeZone();
            timeZone.setName(timeZoneName);
            dataBaseService.save(timeZone);
        }

        SubdivisionPU subdivisionPU = (SubdivisionPU)dataBaseService.findByName(subdivisionPUName,SubdivisionPU.class);
        if (subdivisionPU == null) {
            subdivisionPU = new SubdivisionPU();
            subdivisionPU.setName(subdivisionPUName);
            dataBaseService.save(subdivisionPU);
        }

        Company company = new Company();
        company.setShortName(companyName);
        company.setActual(true);
        company.setTimeZone(timeZone);
        company.setSubdivisionPU(subdivisionPU);
        company.setPhone("1234567");
        company.setActual(true);
        company.setWorkFrom(Time.valueOf(LocalTime.of(8, 30, 00)));
        dataBaseService.saveOrUpdate(company);
    }
}
