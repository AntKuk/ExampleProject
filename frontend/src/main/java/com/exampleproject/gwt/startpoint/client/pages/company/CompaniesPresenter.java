package com.exampleproject.gwt.startpoint.client.pages.company;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CompaniesPresenter {
    interface MyUiBinder extends UiBinder<VerticalPanel, CompaniesPresenter> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    CellTable cellTable;

    private VerticalPanel root;

    public CompaniesPresenter() {
        root = uiBinder.createAndBindUi(this);
    }

    public VerticalPanel getElement() {
        return root;
    }

    public CellTable getCellTable() {
        return cellTable;
    }



}
