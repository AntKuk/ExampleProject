package com.exampleproject.gwt.startpoint.client.pages.transaction;

import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.gwt.startpoint.client.pages.Validator;
import com.exampleproject.gwt.startpoint.client.pages.bank.BanksPresenter;
import com.exampleproject.model.shared.BankAccDto;
import com.exampleproject.model.shared.BankDto;
import com.exampleproject.model.shared.CompanyDto;
import com.exampleproject.model.shared.TransactDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
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

import java.util.ArrayList;
import java.util.List;

import static com.exampleproject.gwt.startpoint.client.pages.Styler.setDefaultStyle;
import static com.exampleproject.gwt.startpoint.client.pages.Styler.setErrorStyle;

public class AddTransactView {
    interface MyUiBinder extends UiBinder<DialogBox, AddTransactView> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private final WorkerClient client = GWT.create(WorkerClient.class);

    @UiField
    Button ok;
    @UiField
    Button cancel;
    @UiField
    ListBox sellerName;
    @UiField
    ListBox customerName;
    @UiField
    TextBox total;
    @UiField
    ListBox customerAcc;
    @UiField
    ListBox sellerAcc;


    private DialogBox dialogBox;
    private TransactionsPresenter transactionsPresenter;
    private Validator validator;

    public AddTransactView() {
        dialogBox = uiBinder.createAndBindUi(this);
        validator = new Validator();

        dialogBox.setText("Adding transaction");
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
        setDefaultStyle(total);
        validator.resetErrorString();
        if (checkInputs()) {
            TransactDto transactDto = createDto();
            client.addTransact(transactDto, new MethodCallback<Boolean>() {
                @Override
                public void onFailure(Method method, Throwable exception) {
                    Window.alert(exception.toString() + "\n" + exception.getMessage());
                }
                @Override
                public void onSuccess(Method method, Boolean aBoolean) {
                    dialogBox.hide();
                    Window.alert("Added");
                    client.getAllTransacts(new MethodCallback<List<TransactDto>>() {
                        @Override
                        public void onFailure(Method method, Throwable exception) {
                            Window.alert(exception.toString() + "\n" + exception.getMessage());
                        }
                        @Override
                        public void onSuccess(Method method, List<TransactDto> transactDto) {
                            transactionsPresenter.getCellTable().setRowData(transactDto);
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

    @UiHandler("sellerName")
    void sellerChanged(ChangeEvent event) {
        //List<BankAccDto> list = new ArrayList<>();
        sellerAcc.clear();
        client.getAccounts(sellerName.getSelectedItemText(), new ListMethodCallback(sellerAcc));
    }

    @UiHandler("customerName")
    void customerChanged(ChangeEvent event) {
        //List<BankAccDto> list = new ArrayList<>();
        customerAcc.clear();
        client.getAccounts(customerName.getSelectedItemText(), new ListMethodCallback(customerAcc));
    }

    private boolean checkInputs() {
        boolean isValid = true;
        if (!validator.isTotal(total.getText())) {
            isValid = false;
            setErrorStyle(total);
        }
        return isValid;
    }


    private TransactDto createDto() {
        TransactDto transactDto = new TransactDto();
        transactDto.setSeller(sellerName.getSelectedItemText());
        transactDto.setCustomer(customerName.getSelectedItemText());
        transactDto.setTotal(Integer.parseInt(total.getText()));
        transactDto.setCustomerAcc(Long.parseLong(customerAcc.getSelectedItemText()));
        transactDto.setSellerAcc(Long.parseLong(sellerAcc.getSelectedItemText()));

        return transactDto;
    }

    private void setListBoxes() {
        client.getAllCompanies(new MethodCallback<List<CompanyDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.toString() + "\n" + exception.getMessage());
            }
            @Override
            public void onSuccess(Method method, List<CompanyDto> companyDtos) {
                for(CompanyDto companyDto : companyDtos) {
                    sellerName.addItem(companyDto.getCompanyName());
                    customerName.addItem(companyDto.getCompanyName());
                }
            }
        });
    }


    public ListBox getSellerName() {
        return sellerName;
    }

    public void setSellerName(ListBox sellerName) {
        this.sellerName = sellerName;
    }

    public ListBox getCustomerName() {
        return customerName;
    }

    public void setCustomerName(ListBox customerName) {
        this.customerName = customerName;
    }

    public TextBox getTotal() {
        return total;
    }

    public void setTotal(TextBox total) {
        this.total = total;
    }

    public DialogBox getDialogBox() {
        return dialogBox;
    }

    public void setDialogBox(DialogBox dialogBox) {
        this.dialogBox = dialogBox;
    }

    public TransactionsPresenter getTransactionsPresenter() {
        return transactionsPresenter;
    }

    public void setTransactionsPresenter(TransactionsPresenter transactionsPresenter) {
        this.transactionsPresenter = transactionsPresenter;
    }

    private class ListMethodCallback implements MethodCallback<List<BankAccDto>> {
        ListBox listBox;
        ListMethodCallback(ListBox listBox) {
            this.listBox = listBox;
        }
        @Override
        public void onFailure(Method method, Throwable exception) {
            Window.alert(exception.toString() + "\n" + exception.getMessage());
        }

        @Override
        public void onSuccess(Method method, List<BankAccDto> bankAccDtos) {
            for(BankAccDto bankAccDto : bankAccDtos) {
                listBox.addItem(Long.toString(bankAccDto.getCorAcc()));
            }
        }
    }
}
