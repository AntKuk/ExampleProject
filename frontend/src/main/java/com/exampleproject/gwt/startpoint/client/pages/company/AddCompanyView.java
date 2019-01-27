package com.exampleproject.gwt.startpoint.client.pages.company;


import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.model.shared.CompanyDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class AddCompanyView {
    interface MyUiBinder extends UiBinder<DialogBox, AddCompanyView> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    Button ok;

    @UiField
    Button cancel;

    @UiField
    TextBox companyName;
    @UiField
    TextBox companyAddress;
    @UiField
    TextBox iec;
    @UiField
    TextBox tin;
    @UiField
    TextBox tel;
    @UiField
    TextBox email;


    private DialogBox dialogBox;
    private CompaniesPresenter companiesPresenter;


    public AddCompanyView(CompaniesPresenter companiesPresenter) {
        dialogBox = uiBinder.createAndBindUi(this);
        this.companiesPresenter = companiesPresenter;

        dialogBox.setText("Adding company");
        dialogBox.setAnimationEnabled(true);
        dialogBox.setGlassEnabled(true);
        dialogBox.center();

        dialogBox.show();
    }

    @UiHandler("cancel")
    void closeView(ClickEvent event) {
        dialogBox.hide();
    }

    @UiHandler("ok")
    void add(ClickEvent event) {
        final WorkerClient client = GWT.create(WorkerClient.class);
        Defaults.setServiceRoot(GWT.getHostPageBaseURL() + "backend");
        CompanyDto companyDto = createDto();
        client.add(companyDto, new MethodCallback<Boolean>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.toString() + "\n" + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, Boolean aBoolean) {
                dialogBox.hide();
                Window.alert("Added");
                client.getAllCompanies(new MethodCallback<List<CompanyDto>>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert(exception.toString() + "\n" + exception.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, List<CompanyDto> companyDtos) {
                        companiesPresenter.getCellTable().setRowData(companyDtos);
                    }
                });

            }
        });


    }

    private CompanyDto createDto() {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setCompanyName(companyName.getText());
        companyDto.setAddress(companyAddress.getText());
        companyDto.setIec(Integer.parseInt(iec.getText()));
        companyDto.setTin(Integer.parseInt(tin.getText()));
        companyDto.setEmail(email.getText());
        companyDto.setTelNumber(Integer.parseInt(tel.getText()));
        companyDto.setId(0);

        return companyDto;
    }

}
