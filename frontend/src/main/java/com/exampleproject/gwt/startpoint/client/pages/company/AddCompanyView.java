package com.exampleproject.gwt.startpoint.client.pages.company;


import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.gwt.startpoint.client.pages.Validator;
import com.exampleproject.model.shared.CompanyDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.*;

import static com.exampleproject.gwt.startpoint.client.pages.Styler.setDefaultStyle;
import static com.exampleproject.gwt.startpoint.client.pages.Styler.setErrorStyle;

public class AddCompanyView {
    interface MyUiBinder extends UiBinder<DialogBox, AddCompanyView> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    protected final WorkerClient client = GWT.create(WorkerClient.class);

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
    protected Validator validator;
    protected List<TextBox> inputs;

    public AddCompanyView() {
        dialogBox = uiBinder.createAndBindUi(this);

        this.validator = new Validator();

        inputs = new ArrayList<>();
        inputs.add(companyName);
        inputs.add(companyAddress);
        inputs.add(iec);
        inputs.add(tin);
        inputs.add(tel);
        inputs.add(email);

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
        for(TextBox textBox : inputs) {
            setDefaultStyle(textBox);
        }

        Defaults.setServiceRoot(GWT.getHostPageBaseURL() + "backend");
        validator.resetErrorString();
        if(checkInputs()) {
            CompanyDto companyDto = createDto();
            client.addCompany(companyDto, new MethodCallback<Boolean>() {
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
        else {
            Window.alert(validator.getErrorString());
            validator.resetErrorString();
        }
    }

    protected CompanyDto createDto() {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setCompanyName(companyName.getText());
        companyDto.setAddress(companyAddress.getText());
        companyDto.setIec(Long.parseLong(iec.getText()));
        companyDto.setTin(Long.parseLong(tin.getText()));
        companyDto.setEmail(email.getText());
        companyDto.setTelNumber(Long.parseLong(tel.getText()));
        companyDto.setId(0);

        return companyDto;
    }

    protected boolean checkInputs() {
        boolean isValid = true;
        if(companyName.getText().isEmpty()) {
            isValid = false;
            setErrorStyle(companyName);
        }
        if(companyAddress.getText().isEmpty()) {
            isValid = false;
            setErrorStyle(companyAddress);
        }
        if(!validator.isIec(iec.getText())) {
            isValid = false;
            setErrorStyle(iec);
        }
        if(!validator.isTin(tin.getText())) {
            isValid = false;
            setErrorStyle(tin);
        }
        if (!validator.isTel(tel.getText())) {
            isValid = false;
            setErrorStyle(tel);
        }
        if (!validator.isEmail(email.getText())) {
            isValid = false;
            setErrorStyle(email);
        }

        return isValid;
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
