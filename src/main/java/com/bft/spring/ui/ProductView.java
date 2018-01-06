package com.bft.spring.ui;

/**
 * Created by rev on 06.01.2018.
 */

import com.bft.spring.model.Product;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;

@org.springframework.stereotype.Component("productView")
public class ProductView extends BaseView {

    private Product product;

    private TextField nameField;

    private TextField emailField;


    public VerticalSplitPanel initContent() {
        createEditFields();

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{nameField, emailField}));

        container = createContainer(Product.class);

        Table table = createTable(getMessage("product.product"), container, new
                Object[]{"id", "name", "email"});
        table.setSizeFull();

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    product = (Product) table.getValue();
                    if (product != null) {
                        updateEditPanelFields();
                    }
                });

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(table, editLayout);

        buttonUpdate.addClickListener(event -> {
            if (product == null)
                return;
            updateFields();
            getDataBaseService().saveOrUpdate(product);
            updateContainer(Product.class);
        });

        buttonCreate.addClickListener(event -> {
            product = new Product();
            updateFields();
            getDataBaseService().saveOrUpdate(product);
            updateContainer(Product.class);
        });

        buttonDelete.addClickListener(event -> {
            if (product == null)
                return;
            getDataBaseService().delete(product);
            updateContainer(Product.class);
        });

        return verticalSplitPanel;
    }

    private void updateEditPanelFields() {
        nameField.setValue(notNullVal(product.getName()));
        emailField.setValue(notNullVal(product.getEmail()));
    }

    private void updateFields() {
        product.setName(nameField.getValue());
        product.setEmail(emailField.getValue());
    }

    private void createEditFields() {
        nameField = createTextField(getMessage("product.name"));
        emailField = createTextField(getMessage("product.email"));
    }

}
