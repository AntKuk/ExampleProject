package com.exampleproject.gwt.startpoint.client.pages.main;

import com.exampleproject.gwt.startpoint.client.pages.account.AccountsPresenter;
import com.exampleproject.gwt.startpoint.client.pages.bank.BanksPresenter;
import com.exampleproject.gwt.startpoint.client.pages.company.CompaniesPresenter;
import com.exampleproject.gwt.startpoint.client.handler.MenuBtnHandler;
import com.exampleproject.gwt.startpoint.client.pages.transaction.TransactionsPresenter;
import com.exampleproject.gwt.startpoint.client.pages.user.UserPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

public class MainPage {
    interface MyUiBinder extends UiBinder<VerticalPanel, MainPage> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private VerticalPanel root;

    @UiField
    Button companiesBtn;
    @UiField
    Button banksBtn;
    @UiField
    Button accountsBtn;
    @UiField
    Button transactionsBtn;
    @UiField
    Button usersBtn;
    @UiField
    SimplePanel simplePanel;
    //@UiField
    //VerticalPanel vertical;

    private CompaniesPresenter companiesPresenter;
    private BanksPresenter banksPresenter;
    private AccountsPresenter accsPresenter;
    private TransactionsPresenter transactionsPresenter;
    private UserPresenter userPresenter;
    private boolean isAdmin = false;
    //private WorkerClient client;

    public MainPage() {
        root = uiBinder.createAndBindUi(this);
        //vertical.setWidth("128px");
        //simplePanel.setWidth("765px");
        companiesPresenter = GWT.create(CompaniesPresenter.class);
        banksPresenter = GWT.create(BanksPresenter.class);
        accsPresenter = GWT.create(AccountsPresenter.class);
        transactionsPresenter = GWT.create(TransactionsPresenter.class);
        userPresenter = GWT.create(UserPresenter.class);
        addButtonHandlers();

    }



    public void loadPagesForRole() {
        if(!isAdmin) {
            companiesPresenter.getDeleteButton().removeFromParent();
            banksPresenter.getDeleteButton().removeFromParent();
            transactionsPresenter.getDeleteButton().removeFromParent();
            accsPresenter.getDeleteButton().removeFromParent();
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

    public VerticalPanel getElement() {
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
        accountsBtn.addClickHandler(new MenuBtnHandler(this, accsPresenter));
        transactionsBtn.addClickHandler(new MenuBtnHandler(this, transactionsPresenter));
        companiesBtn.addClickHandler(new MenuBtnHandler(this, companiesPresenter));
        usersBtn.addClickHandler(new MenuBtnHandler(this, userPresenter));
    }
}
