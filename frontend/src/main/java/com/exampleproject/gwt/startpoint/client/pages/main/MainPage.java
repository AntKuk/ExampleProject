package com.exampleproject.gwt.startpoint.client.pages.main;

import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.gwt.startpoint.client.pages.bank.BanksPresenter;
import com.exampleproject.gwt.startpoint.client.pages.company.CompaniesPresenter;
import com.exampleproject.gwt.startpoint.client.pages.handler.MenuBtnHandler;
import com.exampleproject.gwt.startpoint.client.pages.transaction.TransactionsPresenter;
import com.exampleproject.gwt.startpoint.client.pages.user.UserPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;

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
    Button usersBtn;
    @UiField
    SimplePanel simplePanel;

    private CompaniesPresenter companiesPresenter;
    private BanksPresenter banksPresenter;
    private TransactionsPresenter transactionsPresenter;
    private UserPresenter userPresenter;
    private boolean isAdmin = false;
    //private WorkerClient client;

    public MainPage() {
        root = uiBinder.createAndBindUi(this);
        companiesPresenter = GWT.create(CompaniesPresenter.class);
        banksPresenter = GWT.create(BanksPresenter.class);
        transactionsPresenter = GWT.create(TransactionsPresenter.class);
        userPresenter = GWT.create(UserPresenter.class);
        addButtonHandlers();

    }



    public void loadPagesForRole() {
        if(!isAdmin) {
            companiesPresenter.getDeleteButton().removeFromParent();
            banksPresenter.getDeleteButton().removeFromParent();
            transactionsPresenter.getDeleteButton().removeFromParent();
            usersBtn.removeFromParent();
        }
    }

    public void setAdmin(boolean role) {
        this.isAdmin = role;
    }

    public boolean isAdmin() {
        return this.isAdmin;
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

    private void addButtonHandlers() {
        banksBtn.addClickHandler(new MenuBtnHandler(this, banksPresenter));
        transactionsBtn.addClickHandler(new MenuBtnHandler(this, transactionsPresenter));
        companiesBtn.addClickHandler(new MenuBtnHandler(this, companiesPresenter));
        usersBtn.addClickHandler(new MenuBtnHandler(this, userPresenter));
    }
}
