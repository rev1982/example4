package com.bft.spring;

import com.bft.spring.configuration.HibernateConfiguration;
import com.bft.spring.ui.MainLayout;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Created by rev on 21.12.2017.
 */
@Theme("valo")
public class VaadinUI extends UI {

    public  static ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfiguration.class);

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setSizeFull();
        MainLayout mainLayout = (MainLayout)context.getBean("mainLayout");
        mainLayout.init();
        setContent(mainLayout);
    }

    @Override
    public Component getContent() {
        return super.getContent();
    }
}
