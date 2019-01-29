package com.exampleproject.gwt.startpoint.client.pages.handler;

import com.exampleproject.gwt.startpoint.client.pages.main.MainPage;
import com.exampleproject.gwt.startpoint.client.presenter.TabPresenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;

public class MenuBtnHandler implements ClickHandler {

    private TabPresenter tabPresenter;
    private MainPage mainPage;

    public MenuBtnHandler(MainPage mainPage, TabPresenter tabPresenter) {
        this.tabPresenter = tabPresenter;
        this.mainPage = mainPage;
    }

    @Override
    public void onClick(ClickEvent clickEvent) {
        Widget widget =  mainPage.getSimplePanel().getWidget();
        if(widget != null) {
            mainPage.getSimplePanel().remove(widget);
        }
        mainPage.getSimplePanel().add(tabPresenter.getElement());
    }
}
