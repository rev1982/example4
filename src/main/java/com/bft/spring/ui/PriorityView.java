package com.bft.spring.ui;

/**
 * Created by rev on 07.01.2018.
 */
import com.bft.spring.model.Priority;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

@org.springframework.stereotype.Component("priorityView")
public class PriorityView extends BaseView {

    private Priority priority;

    private TextField nameField;


    public VerticalSplitPanel initContent() {
        entityClass = Priority.class;
        createEditFields();

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{nameField}));

        container = createContainer(entityClass);

        table = createTable(getMessage("priority.priority"), container, new
                Object[]{"id", "name"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }

    @Override
    public void updateEditPanelFields() {
        priority = (Priority) entity;
        nameField.setValue(notNullVal(priority.getName()));
    }

    @Override
    public void updateFields() {
        priority = (Priority)entity;
        priority.setName(nameField.getValue());
    }

    private void createEditFields() {
        nameField = createTextField(getMessage("priority.name"));
    }

}
