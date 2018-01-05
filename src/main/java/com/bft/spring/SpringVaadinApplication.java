package com.bft.spring;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import javax.servlet.annotation.WebServlet;

/**
 * Created by rev on 22.12.2017.
 */


@WebServlet(urlPatterns = "/*", name = "SpringVaadinApplication", asyncSupported = true)
@VaadinServletConfiguration(ui = VaadinUI.class, productionMode = false)
public class SpringVaadinApplication extends VaadinServlet {

}