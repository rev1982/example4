package com.bft.spring.ui;

/**
 * Created by rev on 06.01.2018.
 */



import com.bft.spring.model.Product;
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
        entityClass = com.bft.spring.model.Unit.class;

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{nameField, productIdField, removedFromBalanceField}));

        container = uiUtils.createContainer(com.bft.spring.model.Unit.class);

        table = createTable(getMessage("unit.unit"), container, new
                Object[]{"id", "name", "product", "removedFromBalance"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }

    @Override
    public void updateEditPanelFields() {
        unit = (com.bft.spring.model.Unit) entity;
        nameField.setValue(notNullVal(unit.getName()));
        removedFromBalanceField.setValue(unit.getRemovedFromBalance() == null ? "" :
        unit.getRemovedFromBalance().toString());
        productIdField.setValue(uiUtils.getNotNullId(unit.getProduct()));
    }

    @Override
    public void updateFields() {
        unit = (com.bft.spring.model.Unit) entity;
        unit.setName(nameField.getValue());
        unit.setRemovedFromBalance(removedFromBalanceField.getValue() == null ? null : Boolean.valueOf(removedFromBalanceField.getValue().toString()));
        unit.setProduct((Product)uiUtils.getEntityById(productIdField.getValue(), Product.class));
    }

    private void createEditFields() {
        nameField = createTextField(getMessage("unit.name"));
        removedFromBalanceField = uiUtils.createCombo("unit.removedFromBalance", uiUtils.createBooleanStringContainer());
        productIdField = uiUtils.createCombo("unit.productId", uiUtils.createIdContainer(Product.class.getSimpleName()));
    }

}

