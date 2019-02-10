package com.exampleproject.gwt.startpoint.client;

import com.exampleproject.gwt.startpoint.client.pages.main.LoginPage;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import org.fusesource.restygwt.client.Defaults;

public class StartPoint implements EntryPoint {

    private final WorkerClient client = GWT.create(WorkerClient.class);

    public void onModuleLoad() {
        Defaults.setServiceRoot(GWT.getHostPageBaseURL() + "backend");
        LoginPage loginPage = GWT.create(LoginPage.class);
    }
}
