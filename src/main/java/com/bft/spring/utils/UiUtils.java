package com.bft.spring.utils;

import com.bft.spring.messages.Messages;
import com.bft.spring.model.DomainEntity;
import com.bft.spring.model.IDomainEntity;
import com.bft.spring.service.DataBaseService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.time.LocalTime;

/**
 * Created by rev on 08.01.2018.
 */
@org.springframework.stereotype.Component("uiUtils")
public class UiUtils<T extends IDomainEntity> {

    @Autowired
    private DataBaseService dataBaseService;
    @Autowired
    private Messages messageSource;

    public Object getNotNullId(DomainEntity<Long> domainEntity){
        return  domainEntity == null ? "" : domainEntity.getId();
    }

    public DomainEntity<Long> getEntityById(Object obj, Class classs){
        return obj == null ? null : dataBaseService.findById((Long) obj, classs);
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

    public String getMessage(String s) {
        return messageSource.getMessage(s);
    }

    public String getTimeVal(Time time) {
        return time != null ? LocalTime.of(time.getHours(), time.getMinutes()).toString() : null;
    }

    public Time getTimeFromCombo(Object ob) {
        return ob == null ? null : Time.valueOf(LocalTime.parse(ob.toString()));
    }

    public BeanItemContainer<T> createContainer(Class clazz) {
        BeanItemContainer<T> container =
                new BeanItemContainer<>(clazz);

        container.addAll(dataBaseService.findAll(clazz));
        container.sort(new String[]{"id"}, new boolean[]{false});
        return container;
    }

}
