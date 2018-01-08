package com.bft.spring.ui;

/**
 * Created by rev on 06.01.2018.
 */

import com.bft.spring.model.Product;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

@org.springframework.stereotype.Component("productView")
public class ProductView extends BaseView {

    private Product product;

    private TextField nameField;

    private TextField emailField;


    public VerticalSplitPanel initContent() {
        createEditFields();
        entityClass = Product.class;

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{nameField, emailField}));

        container = uiUtils.createContainer(Product.class);

        table = createTable(getMessage("product.product"), container, new
                Object[]{"id", "name", "email"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

        return verticalSplitPanel;
    }

    @Override
    public void updateEditPanelFields() {
        product = (Product) entity;
        nameField.setValue(notNullVal(product.getName()));
        emailField.setValue(notNullVal(product.getEmail()));
    }

    @Override
    public void updateFields() {
        product = (Product) entity;
        product.setName(nameField.getValue());
        product.setEmail(emailField.getValue());
    }

    private void createEditFields() {
        nameField = createTextField(getMessage("product.name"));
        emailField = createTextField(getMessage("product.email"));
    }

}
