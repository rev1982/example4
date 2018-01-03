package com.bft.spring.ui;

/**
 * Created by rev on 28.12.2017.
 */

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

public abstract class ViewInit extends VerticalLayout implements View {

    public ViewInit() {
        setSizeFull();
        addDetachListener(new DetachListener() {
            @Override
            public void detach(DetachEvent event) {
                onDetached();
            }
        });
    }

    @PostConstruct
    public void init() {
        addComponent(initContent());
    }

    public Component initContent(){
        return  new VerticalLayout();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {}

    protected void onDetached() {}

}
