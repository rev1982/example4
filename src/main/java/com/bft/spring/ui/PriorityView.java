package com.bft.spring.ui;

/**
 * Created by rev on 07.01.2018.
 */
import com.bft.spring.model.Priority;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

@org.springframework.stereotype.Component("priorityView")
public class PriorityView extends BaseView {

    private Priority priority;

    private TextField nameField;


    public VerticalSplitPanel initContent() {
        createEditFields();

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{nameField}));

        container = createContainer(Priority.class);

        Table table = createTable(getMessage("priority.priority"), container, new
                Object[]{"id", "name"});
        table.setSizeFull();

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    priority = (Priority) table.getValue();
                    if (priority != null) {
                        updateEditPanelFields();
                    }
                });

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(table, editLayout);

        buttonUpdate.addClickListener(event -> {
            if (priority == null)
                return;
            updateFields();
            getDataBaseService().saveOrUpdate(priority);
            updateContainer(Priority.class);
        });

        buttonCreate.addClickListener(event -> {
            priority = new Priority();
            updateFields();
            getDataBaseService().saveOrUpdate(priority);
            updateContainer(Priority.class);
        });

        buttonDelete.addClickListener(event -> {
            if (priority == null)
                return;
            getDataBaseService().delete(priority);
            updateContainer(Priority.class);
        });

        return verticalSplitPanel;
    }

    private void updateEditPanelFields() {
        nameField.setValue(notNullVal(priority.getName()));
    }

    private void updateFields() {
        priority.setName(nameField.getValue());
    }

    private void createEditFields() {
        nameField = createTextField(getMessage("priority.name"));
    }

}
