package com.exampleproject.gwt.startpoint.client.pages.company;


import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.gwt.startpoint.client.pages.Validator;
import com.exampleproject.model.shared.BankAccDto;
import com.exampleproject.model.shared.BankDto;
import com.exampleproject.model.shared.CompanyDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class AddCompanyView {
    interface MyUiBinder extends UiBinder<DialogBox, AddCompanyView> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    final WorkerClient client = GWT.create(WorkerClient.class);

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
    @UiField
    ListBox bank;
    @UiField
    TextBox bankAcc;
    @UiField
    Label bankLabel;
    @UiField
    Label bankAccLabel;



    private DialogBox dialogBox;
    private CompaniesPresenter companiesPresenter;
    private Validator validator;

    public AddCompanyView() {
        dialogBox = uiBinder.createAndBindUi(this);
        //this.companiesPresenter = companiesPresenter;
        this.validator = new Validator();

        dialogBox.setText("Adding company");
        dialogBox.setAnimationEnabled(true);
        dialogBox.setGlassEnabled(true);
        dialogBox.center();

        setListBox();

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

        Defaults.setServiceRoot(GWT.getHostPageBaseURL() + "backend");
        validator.resetErrorString();
        if(isValid()) {
            CompanyDto companyDto = createDto(true);
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


    private void setListBox() {
        client.getAllBanks(new MethodCallback<List<BankDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.toString() + "\n" + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<BankDto> bankDtos) {
                for (BankDto bankDto : bankDtos) {
                    bank.addItem(bankDto.getBankName());
                }
            }
        });
    }


    protected CompanyDto createDto(boolean isNew) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setCompanyName(companyName.getText());
        companyDto.setAddress(companyAddress.getText());
        companyDto.setIec(Long.parseLong(iec.getText()));
        companyDto.setTin(Long.parseLong(tin.getText()));
        companyDto.setEmail(email.getText());
        companyDto.setTelNumber(Long.parseLong(tel.getText()));
        companyDto.setId(0);
        if (isNew) {
            companyDto.setAcc(createAcc());
        }
        return companyDto;
    }

    protected BankAccDto createAcc() {
        BankAccDto acc = new BankAccDto();
        acc.setBankName(bank.getSelectedItemText());
        acc.setComName(companyName.getText());
        acc.setCorAcc(Long.parseLong(bankAcc.getText()));

        return acc;
    }

    private boolean isValid() {
        return validator.isTin(tin.getText())
                & validator.isEmail(email.getText())
                & validator.isIec(iec.getText())
                & validator.isTel(tel.getText());
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

    public ListBox getBank() {
        return bank;
    }

    public void setBank(ListBox bank) {
        this.bank = bank;
    }

    public TextBox getBankAcc() {
        return bankAcc;
    }

    public void setBankAcc(TextBox bankAcc) {
        this.bankAcc = bankAcc;
    }

    public Label getBankLabel() {
        return bankLabel;
    }

    public Label getBankAccLabel() {
        return bankAccLabel;
    }
}
