package com.exampleproject.gwt.startpoint.client.pages.account;

import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.model.shared.BankAccDto;
import com.exampleproject.model.shared.BankDto;
import com.exampleproject.model.shared.CompanyDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class AddAccountView {
    interface MyUiBinder extends UiBinder<DialogBox, AddAccountView> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private final WorkerClient client = GWT.create(WorkerClient.class);

    @UiField
    Button ok;
    @UiField
    Button cancel;
    @UiField
    ListBox bankName;
    @UiField
    ListBox companyName;
    @UiField
    TextBox account;



    private DialogBox dialogBox;
    private AccountsPresenter accountsPresenter;


    public AddAccountView() {
        dialogBox = uiBinder.createAndBindUi(this);

        dialogBox.setText("Adding account");
        dialogBox.setAnimationEnabled(true);
        dialogBox.setGlassEnabled(true);
        dialogBox.center();

        setListBoxes();


        dialogBox.show();
    }

    @UiHandler("cancel")
    void closeView(ClickEvent event) {
        dialogBox.hide();
    }

    @UiHandler("ok")
    void add(ClickEvent event) {

        BankAccDto bankAccDto = createDto();
        client.addAccount(bankAccDto, new MethodCallback<Boolean>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.toString() + "\n" + exception.getMessage());
            }
            @Override
            public void onSuccess(Method method, Boolean aBoolean) {
                dialogBox.hide();
                Window.alert("Added");
                client.getAllAccounts(new MethodCallback<List<BankAccDto>>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert(exception.toString() + "\n" + exception.getMessage());
                    }
                    @Override
                    public void onSuccess(Method method, List<BankAccDto> transactDto) {
                       accountsPresenter.getCellTable().setRowData(transactDto);
                    }
                });
            }
        });
    }


    protected BankAccDto createDto() {
        BankAccDto bankAccDto = new BankAccDto();
        bankAccDto.setComName(companyName.getSelectedItemText());
        bankAccDto.setBankName(bankName.getSelectedItemText());
        bankAccDto.setCorAcc(Long.parseLong(account.getText()));

        return bankAccDto;
    }

    private void setListBoxes() {
        client.getAllBanks(new MethodCallback<List<BankDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.toString() + "\n" + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<BankDto> bankDtos) {
                for (BankDto bankDto : bankDtos) {
                    bankName.addItem(bankDto.getBankName());
                }
            }
        });

        client.getAllCompanies(new MethodCallback<List<CompanyDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.toString() + "\n" + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<CompanyDto> companyDtos) {
                for (CompanyDto companyDto : companyDtos) {
                    companyName.addItem(companyDto.getCompanyName());
                }
            }
        });
    }


    public AccountsPresenter getAccountsPresenter() {
        return accountsPresenter;
    }

    public void setAccountsPresenter(AccountsPresenter accountsPresenter) {
        this.accountsPresenter = accountsPresenter;
    }
}
