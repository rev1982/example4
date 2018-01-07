package com.bft.spring.ui;

/**
 * Created by rev on 06.01.2018.
 */



import com.bft.spring.model.Product;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

@org.springframework.stereotype.Component("unitView")
public class UnitView extends BaseView {

    private com.bft.spring.model.Unit unit;

    private TextField nameField;

    private ComboBox productIdField;

    private ComboBox removedFromBalanceField;


    public VerticalSplitPanel initContent() {
        createEditFields();

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{nameField, productIdField, removedFromBalanceField}));

        container = createContainer(com.bft.spring.model.Unit.class);

        Table table = createTable(getMessage("unit.unit"), container, new
                Object[]{"id", "name", "product", "removedFromBalance"});
        table.setSizeFull();

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    unit = (com.bft.spring.model.Unit) table.getValue();
                    if (unit != null) {
                        updateEditPanelFields();
                    }
                });

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(table, editLayout);

        buttonUpdate.addClickListener(event -> {
            if (unit == null)
                return;
            updateFields();
            getDataBaseService().saveOrUpdate(unit);
            updateContainer(com.bft.spring.model.Unit.class);
        });

        buttonCreate.addClickListener(event -> {
            unit = new com.bft.spring.model.Unit();
            updateFields();
            getDataBaseService().saveOrUpdate(unit);
            updateContainer(com.bft.spring.model.Unit.class);
        });

        buttonDelete.addClickListener(event -> {
            if (unit == null)
                return;
            getDataBaseService().delete(unit);
            updateContainer(com.bft.spring.model.Unit.class);
        });

        return verticalSplitPanel;
    }

    private void updateEditPanelFields() {
        nameField.setValue(notNullVal(unit.getName()));
        removedFromBalanceField.setValue(unit.getRemovedFromBalance() == null ? "" :
        unit.getRemovedFromBalance().toString());
        productIdField.setValue(getNotNullId(unit.getProduct()));
    }

    private void updateFields() {
        unit.setName(nameField.getValue());
        unit.setRemovedFromBalance(removedFromBalanceField.getValue() == null ? null : Boolean.valueOf(removedFromBalanceField.getValue().toString()));
        unit.setProduct((Product)getValueById(productIdField.getValue(), Product.class));
    }

    private void createEditFields() {
        nameField = createTextField(getMessage("unit.name"));
        removedFromBalanceField = createCombo("unit.removedFromBalance", createBooleanStringContainer());
        productIdField = createCombo("unit.productId", createIdContainer(Product.class.getSimpleName()));
    }

}

