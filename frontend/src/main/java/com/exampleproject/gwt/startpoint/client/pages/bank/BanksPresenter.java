package com.exampleproject.gwt.startpoint.client.pages.bank;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BanksPresenter {
    interface MyUiBinder extends UiBinder<VerticalPanel, BanksPresenter> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    CellTable cellTable;

    @UiField
    Button addButton;

    private VerticalPanel root;

    public BanksPresenter() {
        root = uiBinder.createAndBindUi(this);
    }

    public VerticalPanel getElement() {
        return root;
    }

    public CellTable getCellTable() {
        return cellTable;
    }

    @UiHandler("addButton")
    void addBtn(ClickEvent event) {
        Window.alert("You clicked Add button");
    }

}
