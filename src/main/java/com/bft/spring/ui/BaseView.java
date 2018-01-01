package com.bft.spring.ui;

import com.bft.spring.model.IDomainEntity;
import com.bft.spring.service.DataBaseService;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by rev on 24.12.2017.
 */
@Configurable
public class BaseView<T extends IDomainEntity> extends ViewInit {
    @Autowired
    private DataBaseService dataBaseService;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    protected BeanItemContainer<T> container;
    private BeanItemContainer<String> timeContainer;

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

    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public DataBaseService getDataBaseService() {
        return dataBaseService;
    }

    public ResourceBundleMessageSource getMessageSource() {
        return messageSource;
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

    private BeanItemContainer<String> createTimeContainer() {
        List<String> times = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            times.add(i < 10 ? "0" + i + ":00" : i + ":00");
            times.add(i < 10 ? "0" + i + ":30" : i + ":30");
        }
        timeContainer = new BeanItemContainer<>(String.class);
        timeContainer.addAll(times);
        return timeContainer;
    }

    public BeanItemContainer<String> getTimeContainer() {
        return timeContainer == null ? createTimeContainer() : timeContainer;
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
        return messageSource.getMessage(s, null, null);
    }

    @Override
    public Component initContent() {
        return new VerticalLayout();
    }


}
