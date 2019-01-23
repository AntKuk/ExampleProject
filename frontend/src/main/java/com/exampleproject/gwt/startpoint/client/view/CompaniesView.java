package com.exampleproject.gwt.startpoint.client.view;

import com.google.gwt.user.client.ui.IsWidget;

public interface CompaniesView extends IsWidget {
    public void setPresenter(CompaniesPresenter companiesPresenter);

    public interface CompaniesPresenter {}
}
