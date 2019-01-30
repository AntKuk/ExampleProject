package com.exampleproject.gwt.startpoint.client.pages.transaction;

import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.gwt.startpoint.client.pages.bank.BanksPresenter;
import com.exampleproject.model.shared.BankDto;
import com.exampleproject.model.shared.CompanyDto;
import com.exampleproject.model.shared.TransactDto;
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
    TextBox sellerAcc;
    @UiField
    TextBox customerAcc;


    private DialogBox dialogBox;
    private TransactionsPresenter transactionsPresenter;
    //private List<CompanyDto> list;


    public AddTransactView() {
        dialogBox = uiBinder.createAndBindUi(this);

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
        final WorkerClient client = GWT.create(WorkerClient.class);
        //Defaults.setServiceRoot(GWT.getHostPageBaseURL() + "backend");
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



    protected TransactDto createDto() {
        TransactDto transactDto = new TransactDto();
        transactDto.setSeller(sellerName.getSelectedItemText());
        transactDto.setCustomer(customerName.getSelectedItemText());
        transactDto.setTotal(Integer.parseInt(total.getText()));

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
/*
        MyMethodCallback myMethodCallback = new MyMethodCallback();
        client.getAllCompanies(myMethodCallback);
        List<CompanyDto> list = myMethodCallback.getList();

        for(CompanyDto companyDto : list) {
            sellerName.addItem(companyDto.getCompanyName());
            customerName.addItem(companyDto.getCompanyName());
        }
*/
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

    public TextBox getSellerAcc() {
        return sellerAcc;
    }

    public void setSellerAcc(TextBox sellerAcc) {
        this.sellerAcc = sellerAcc;
    }

    public TextBox getCustomerAcc() {
        return customerAcc;
    }

    public void setCustomerAcc(TextBox customerAcc) {
        this.customerAcc = customerAcc;
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
}
