package com.exampleproject.gwt.startpoint.client.pages.main;

import com.exampleproject.gwt.startpoint.client.pages.bank.BanksPresenter;
import com.exampleproject.gwt.startpoint.client.pages.company.CompaniesPresenter;
import com.exampleproject.gwt.startpoint.client.pages.transaction.TransactionsPresenter;
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
    SimplePanel simplePanel;

    private CompaniesPresenter companiesPresenter;
    private BanksPresenter banksPresenter;
    private TransactionsPresenter transactionsPresenter;

    public MainPage() {
        root = uiBinder.createAndBindUi(this);
        companiesPresenter = GWT.create(CompaniesPresenter.class);
        banksPresenter = GWT.create(BanksPresenter.class);
        transactionsPresenter = GWT.create(TransactionsPresenter.class);
        addButtonHandlers();

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
        //Adding Buttons Handlers
        banksBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Widget widget = simplePanel.getWidget();
                if(widget != null) {
                    simplePanel.remove(widget);
                }
                simplePanel.add(banksPresenter.getElement());
            }
        });

        transactionsBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Widget widget = simplePanel.getWidget();
                if(widget != null) {
                    simplePanel.remove(widget);
                }
                simplePanel.add(transactionsPresenter.getElement());
            }
        });

        companiesBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Widget widget = simplePanel.getWidget();
                if(widget != null) {
                    simplePanel.remove(widget);
                }
                simplePanel.add(companiesPresenter.getElement());
            }
        });
    }
}
