package com.exampleproject.gwt.startpoint.client.pages.main;

import com.exampleproject.gwt.startpoint.client.pages.bank.BanksPresenter;
import com.exampleproject.gwt.startpoint.client.pages.company.CompaniesPresenter;
import com.exampleproject.gwt.startpoint.client.pages.transaction.TransactionsPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainPage {
    interface MyUiBinder extends UiBinder<HorizontalPanel, MainPage> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private HorizontalPanel root;

    @UiField
    Button companiesBtn;
    @UiField
    Button banksBtn;
    @UiField
    Button transactionsBtn;

    @UiField
    SimplePanel simplePanel;

    public MainPage() {
        root = uiBinder.createAndBindUi(this);
    }

    public SimplePanel getSimplePanel() {
        return simplePanel;
    }

    public HorizontalPanel getElement() {
        return root;
    }

    public Button getCompaniesBtn() {
        return companiesBtn;
    }

    public Button getBanksBtn() {
        return banksBtn;
    }

    public Button getTransactionsBtn() {
        return transactionsBtn;
    }
}
