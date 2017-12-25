package com.bft.spring.ui;

import com.bft.spring.service.DataBaseService;
import com.vaadin.data.Container;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * Created by rev on 24.12.2017.
 */
public class BaseView {
    public static DataBaseService dataBaseService;

    public Table createTable(String tableName, Container container, Object[] visibleColumns) {
        Table table = new Table(tableName);
        table.setContainerDataSource(container);
        if (visibleColumns != null)
            table.setVisibleColumns(visibleColumns);
        table.setSelectable(true);
        return table;
    }

    public HorizontalLayout createHorizontalLayout() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setMargin(true);
        horizontalLayout.setSpacing(true);
        return horizontalLayout;
    }

    public TextField createTextField (String fieldName){
        TextField field = new TextField(fieldName);
        field.setImmediate(true);
        field.setRequired(true);
        return field;
    }

    public HorizontalLayout createEditHorizontalLayout(TextField[] textFields){
        HorizontalLayout horizontalLayout = createHorizontalLayout();
        for (TextField textField : textFields){
            horizontalLayout.addComponent(textField);
        }
        return  horizontalLayout;
    }

    public void setDataBaseService(DataBaseService dataBaseService){
        this.dataBaseService = dataBaseService;
    }
}
