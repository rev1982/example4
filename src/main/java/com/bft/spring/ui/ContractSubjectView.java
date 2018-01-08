package com.bft.spring.ui;

import com.bft.spring.model.Contract;
import com.bft.spring.model.ContractSubject;
import com.bft.spring.model.Product;
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
        entityClass = ContractSubject.class;

        VerticalLayout editLayout = new VerticalLayout(new HorizontalLayout(
                new Component[]{productIdField, contractIdField}));

        container = uiUtils.createContainer(ContractSubject.class);

        table = createTable(getMessage("ContractSubject.contractSubject"), container, new
                Object[]{"id", "contract", "product"});

        VerticalSplitPanel verticalSplitPanel = createVerticalSplitPanel(editLayout);

//        buttonUpdate.addClickListener(event -> {
//            if (contractSubject == null)
//                return;
//            updateFields();
//            getDataBaseService().saveOrUpdate(contractSubject);
//            updateContainer(ContractSubject.class);
//        });
//
//        buttonCreate.addClickListener(event -> {
//            contractSubject = new ContractSubject();
//            updateFields();
//            getDataBaseService().saveOrUpdate(contractSubject);
//            updateContainer(ContractSubject.class);
//        });
//
//        buttonDelete.addClickListener(event -> {
//            if (contractSubject == null)
//                return;
//            getDataBaseService().delete(contractSubject);
//            updateContainer(ContractSubject.class);
//        });

        return verticalSplitPanel;
    }

    @Override
    public void updateEditPanelFields() {
        contractSubject = (ContractSubject) entity;
        productIdField.setValue(uiUtils.getNotNullId(contractSubject.getProduct()));
        contractIdField.setValue(uiUtils.getNotNullId(contractSubject.getContract()));
    }

    @Override
    public void updateFields() {
        contractSubject = (ContractSubject) entity;
        contractSubject.setProduct((Product)uiUtils.getEntityById(productIdField.getValue(), Product.class));
        contractSubject.setContract((Contract)uiUtils.getEntityById(contractIdField.getValue(), Contract.class));
    }

    private void createEditFields() {
        productIdField = uiUtils.createCombo("ContractSubject.productId", uiUtils.createIdContainer(Product.class.getSimpleName()));
        contractIdField = uiUtils.createCombo("ContractSubject.contractId", uiUtils.createIdContainer(Contract.class.getSimpleName()));
    }

}
