package com.bft.spring.ui;

import com.bft.spring.messages.Messages;
import com.bft.spring.model.*;
import com.bft.spring.service.DataBaseService;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by rev on 24.12.2017.
 */
@org.springframework.stereotype.Component("baseView")
public class BaseView<T extends IDomainEntity> extends ViewInit {
    @Autowired
    private DataBaseService dataBaseService;

    @Autowired
    private Messages messageSource;

    protected BeanItemContainer<T> container;
    protected Button buttonUpdate;
    protected Button buttonCreate;
    protected Button buttonDelete;

    public DomainEntity<Long> entity;
    public Class entityClass;
    public Table table;


    public Table createTable(String tableName, Container container, Object[] visibleColumns) {
        Table table = new Table(tableName);
        table.setContainerDataSource(container);
        if (visibleColumns != null)
            table.setVisibleColumns(visibleColumns);
        table.setSelectable(true);
        return table;
    }

    public TextField createTextField(String fieldName) {
        TextField field = new TextField(fieldName);
        field.setImmediate(true);
        field.setRequired(true);
        return field;
    }


    public DataBaseService getDataBaseService() {
        return dataBaseService;
    }


    public void updateContainer(Class clazz) {
        container.removeAllItems();
        container.addAll(dataBaseService.findAll(clazz));
        container.sort(new String[]{"id"}, new boolean[]{false});
    }

    public BeanItemContainer<T> createContainer(Class clazz) {
        BeanItemContainer<T> container =
                new BeanItemContainer<>(clazz);

        container.addAll(dataBaseService.findAll(clazz));
        container.sort(new String[]{"id"}, new boolean[]{false});
        return container;
    }

    public String notNullVal(String s) {
        return s == null ? "" : s;
    }

    public String getTimeVal(Time time) {
        return time != null ? LocalTime.of(time.getHours(), time.getMinutes()).toString() : null;
    }

    public Time getTimeFromCombo(Object ob) {
        return ob == null ? null : Time.valueOf(LocalTime.parse(ob.toString()));
    }

    public String getMessage(String s) {
        return messageSource.getMessage(s);
    }

    @Override
    public Component initContent() {
        return new VerticalLayout();
    }

    public VerticalSplitPanel createVerticalSplitPanel(VerticalLayout editLayout) {
        table.setSizeFull();
        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    entity = (DomainEntity<Long>) table.getValue();
                    if (entity != null) {
                        updateEditPanelFields();
                    }
                });

        buttonUpdate = new Button(getMessage("button.update"));

        buttonCreate = new Button(getMessage("button.create"));

        buttonDelete = new Button(getMessage("button.delete"));

        buttonUpdate.addClickListener(event -> {
            if (entity == null)
                return;
            updateFields();
            getDataBaseService().saveOrUpdate(entity);
            updateContainer(entityClass);
        });

        buttonCreate.addClickListener(event -> {
            try {
                entity = (DomainEntity<Long>)entityClass.newInstance();
            } catch (InstantiationException e) {
                throw  new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw  new RuntimeException(e);
            }
            updateFields();
            getDataBaseService().saveOrUpdate(entity);
            updateContainer(entityClass);
        });

        buttonDelete.addClickListener(event -> {
            if (entity == null)
                return;
            getDataBaseService().delete(entity);
            updateContainer(entityClass);
        });

        HorizontalLayout buttonLayout = new HorizontalLayout(buttonUpdate, buttonCreate, buttonDelete);
        buttonLayout.setMargin(true);
        buttonLayout.setSpacing(true);

        VerticalLayout verticalLayout = new VerticalLayout(editLayout, buttonLayout);
        verticalLayout.setSizeFull();
        verticalLayout.setMargin(true);
        verticalLayout.setSpacing(true);
        verticalLayout.setExpandRatio(editLayout, 0.70f);
        verticalLayout.setExpandRatio(buttonLayout, 0.30f);

        VerticalSplitPanel splitPanel = new VerticalSplitPanel();
        Panel upComponent = new Panel();
        Panel downComponent = new Panel();
        splitPanel.setFirstComponent(upComponent);
        splitPanel.setSecondComponent(downComponent);
        upComponent.setContent(new VerticalLayout(table));
        downComponent.setContent(verticalLayout);

        upComponent.setSizeFull();
        downComponent.setSizeFull();
        splitPanel.setSizeFull();

        return splitPanel;
    }

    public BeanItemContainer<String> createBooleanStringContainer() {
        BeanItemContainer<String> container = new BeanItemContainer<>(String.class);
        container.addItem("false");
        container.addItem("true");
        return container;
    }

    public ComboBox createCombo(String s, BeanItemContainer<String> container) {
        ComboBox combo = new ComboBox(getMessage(s));
        combo.setContainerDataSource(container);
        return combo;
    }

    public BeanItemContainer<Long> createIdContainer(String tableName) {
        BeanItemContainer<Long> container = new BeanItemContainer<>(Long.class);
        container.addAll(dataBaseService.getAllLongColumnValues(tableName, "id"));
        return container;
    }

    public DomainEntity<Long> getEntityById(Object obj, Class classs){
        return obj == null ? null : getDataBaseService().findById((Long) obj, classs);
    }

    public Object getNotNullId(DomainEntity<Long> domainEntity){
        return  domainEntity == null ? "" : domainEntity.getId();
    }

    public void updateFields() {

    }

    public void updateEditPanelFields(){

    }

}
