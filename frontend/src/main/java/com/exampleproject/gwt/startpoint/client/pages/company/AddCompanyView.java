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

    public AddCompanyView() {
        dialogBox = uiBinder.createAndBindUi(this);
        //this.companiesPresenter = companiesPresenter;

        dialogBox.setText("Adding company");
        dialogBox.setAnimationEnabled(true);
        dialogBox.setGlassEnabled(true);
        dialogBox.center();

        dialogBox.show();
    }

/*
    public AddCompanyView(CompaniesPresenter companiesPresenter) {
        dialogBox = uiBinder.createAndBindUi(this);
        this.companiesPresenter = companiesPresenter;

        dialogBox.setText("Adding company");
        dialogBox.setAnimationEnabled(true);
        dialogBox.setGlassEnabled(true);
        dialogBox.center();

        dialogBox.show();
    }
*/

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
                    public void onSuccess(Method method, List<CompanyDto> companyDto) {
                        companiesPresenter.getCellTable().setRowData(companyDto);
                    }
                });
            }
        });
    }

    protected CompanyDto createDto() {
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

    public void setCompaniesPresenter(CompaniesPresenter companiesPresenter) {
        this.companiesPresenter = companiesPresenter;
    }

    public CompaniesPresenter getCompaniesPresenter() {
        return companiesPresenter;
    }

    public DialogBox getDialogBox() {
        return dialogBox;
    }

    public TextBox getCompanyName() {
        return companyName;
    }

    public void setCompanyName(TextBox companyName) {
        this.companyName = companyName;
    }

    public TextBox getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(TextBox companyAddress) {
        this.companyAddress = companyAddress;
    }

    public TextBox getIec() {
        return iec;
    }

    public void setIec(TextBox iec) {
        this.iec = iec;
    }

    public TextBox getTin() {
        return tin;
    }

    public void setTin(TextBox tin) {
        this.tin = tin;
    }

    public TextBox getTel() {
        return tel;
    }

    public void setTel(TextBox tel) {
        this.tel = tel;
    }

    public TextBox getEmail() {
        return email;
    }

    public void setEmail(TextBox email) {
        this.email = email;
    }
}
