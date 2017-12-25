package com.bft.spring;

import com.bft.spring.model.Company;
import com.bft.spring.model.DomainEntity;
import com.bft.spring.service.DataBaseService;
import com.bft.spring.ui.BaseView;
import com.bft.spring.ui.CompanyView;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by rev on 21.12.2017.
 */
@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    @Autowired
    private DataBaseService dataBaseService;

    private Company company;

    @Override
    protected  void  init(VaadinRequest vaadinRequest){
        //AppMain.main2(new String[0]);

        new BaseView().setDataBaseService(dataBaseService);

        VerticalLayout verticalLayout = new VerticalLayout(new CompanyView().init());
        verticalLayout.setMargin(true);
        verticalLayout.setSpacing(true);

        setContent(verticalLayout);
    }
}
