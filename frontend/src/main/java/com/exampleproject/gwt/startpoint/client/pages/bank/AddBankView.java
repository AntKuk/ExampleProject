package com.exampleproject.gwt.startpoint.client.pages.bank;

import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.gwt.startpoint.client.pages.Validator;
import com.exampleproject.model.shared.BankDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

import static com.exampleproject.gwt.startpoint.client.pages.Styler.setDefaultStyle;
import static com.exampleproject.gwt.startpoint.client.pages.Styler.setErrorStyle;

public class AddBankView {
    interface MyUiBinder extends UiBinder<DialogBox, AddBankView> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    protected final WorkerClient client = GWT.create(WorkerClient.class);

    @UiField
    Button ok;
    @UiField
    Button cancel;
    @UiField
    TextBox bankName;
    @UiField
    TextBox bankCity;
    @UiField
    TextBox bankBic;


    private DialogBox dialogBox;
    private BanksPresenter banksPresenter;
    protected Validator validator;


    public AddBankView() {
        dialogBox = uiBinder.createAndBindUi(this);
        validator = new Validator();

        dialogBox.setText("Adding bank");
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
        setDefaultStyle(bankName);
        setDefaultStyle(bankCity);
        setDefaultStyle(bankBic);
        validator.resetErrorString();
        if (checkInputs()) {
            BankDto bankDto = createDto();
            client.addBank(bankDto, new MethodCallback<Boolean>() {
                @Override
                public void onFailure(Method method, Throwable exception) {
                    Window.alert(exception.toString() + "\n" + exception.getMessage());
                }
                @Override
                public void onSuccess(Method method, Boolean aBoolean) {
                    dialogBox.hide();
                    Window.alert("Added");
                    client.getAllBanks(new MethodCallback<List<BankDto>>() {
                        @Override
                        public void onFailure(Method method, Throwable exception) {
                            Window.alert(exception.toString() + "\n" + exception.getMessage());
                        }
                        @Override
                        public void onSuccess(Method method, List<BankDto> bankDto) {
                            banksPresenter.getCellTable().setRowData(bankDto);
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


    protected BankDto createDto() {
        BankDto bankDto = new BankDto();
        bankDto.setBankName(bankName.getText());
        bankDto.setCity(bankCity.getText());
        bankDto.setBic(Long.parseLong(bankBic.getText()));
        return  bankDto;
    }

    protected boolean checkInputs() {
        boolean isValid = true;
        if(bankName.getText().isEmpty()) {
            isValid = false;
            setErrorStyle(bankName);
        }
        if (bankCity.getText().isEmpty()) {
            isValid = false;
            setErrorStyle(bankCity);
        }
        if(!validator.isBic(bankBic.getText())) {
            isValid = false;
            setErrorStyle(bankBic);
        }
        return isValid;
    }



    public TextBox getBankName() {
        return bankName;
    }

    public void setBankName(TextBox bankName) {
        this.bankName = bankName;
    }

    public TextBox getBankCity() {
        return bankCity;
    }

    public void setBankCity(TextBox bankCity) {
        this.bankCity = bankCity;
    }

    public TextBox getBankBic() {
        return bankBic;
    }

    public void setBankBic(TextBox bankBic) {
        this.bankBic = bankBic;
    }

    public DialogBox getDialogBox() {
        return dialogBox;
    }

    public void setDialogBox(DialogBox dialogBox) {
        this.dialogBox = dialogBox;
    }

    public BanksPresenter getBanksPresenter() {
        return banksPresenter;
    }

    public void setBanksPresenter(BanksPresenter banksPresenter) {
        this.banksPresenter = banksPresenter;
    }
}
