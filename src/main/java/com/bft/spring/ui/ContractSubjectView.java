package com.bft.spring.ui;

import com.bft.spring.model.Contract;
import com.bft.spring.model.ContractSubject;
import com.bft.spring.model.Product;
import com.vaadin.data.Property;
import com.vaadin.ui.*;

/**
 * Created by rev on 07.01.2018.
 */
@org.springframework.stereotype.Component("contractSubjectView")
public class ContractSubjectView extends BaseView {

    private ContractSubject contractSubject;

    private ComboBox productIdField;

    private ComboBox contractIdField;


    public VerticalSplitPanel initContent() {
        createEditFields();

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{productIdField, contractIdField}));

        container = createContainer(ContractSubject.class);

        Table table = createTable(getMessage("ContractSubject.contractSubject"), container, new
                Object[]{"id", "contract", "product"});
        table.setSizeFull();

        table.addValueChangeListener(
                (Property.ValueChangeEvent event) -> {
                    contractSubject = (ContractSubject) table.getValue();
                    if (contractSubject != null) {
                        updateEditPanelFields();
                    }
                });

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(table, editLayout);

        buttonUpdate.addClickListener(event -> {
            if (contractSubject == null)
                return;
            updateFields();
            getDataBaseService().saveOrUpdate(contractSubject);
            updateContainer(ContractSubject.class);
        });

        buttonCreate.addClickListener(event -> {
            contractSubject = new ContractSubject();
            updateFields();
            getDataBaseService().saveOrUpdate(contractSubject);
            updateContainer(ContractSubject.class);
        });

        buttonDelete.addClickListener(event -> {
            if (contractSubject == null)
                return;
            getDataBaseService().delete(contractSubject);
            updateContainer(ContractSubject.class);
        });

        return verticalSplitPanel;
    }

    private void updateEditPanelFields() {
        productIdField.setValue(getNotNullId(contractSubject.getProduct()));
        contractIdField.setValue(getNotNullId(contractSubject.getContract()));
    }

    private void updateFields() {
        contractSubject.setProduct((Product)getEntityById(productIdField.getValue(),Product.class));
        contractSubject.setContract((Contract)getEntityById(contractIdField.getValue(),Contract.class));
    }

    private void createEditFields() {
        productIdField = createCombo("ContractSubject.productId", createIdContainer(Product.class.getSimpleName()));
        contractIdField = createCombo("ContractSubject.contractId", createIdContainer(Contract.class.getSimpleName()));
    }

}
