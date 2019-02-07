package com.exampleproject.gwt.startpoint.client.pages.company;

import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.model.shared.BankAccDto;
import com.exampleproject.model.shared.TransactDto;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.Date;
import java.util.List;

public class CompanyAccounts {
    interface MyUiBinder extends UiBinder<DialogBox, CompanyAccounts> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    final WorkerClient client = GWT.create(WorkerClient.class);


    @UiField
    Button cancel;
    @UiField
    CellTable cellTable;

    private DialogBox dialogBox;

    private TextColumn<BankAccDto> accColumn;
    private TextColumn<BankAccDto> bankColumn;
    private TextColumn<BankAccDto> comColumn;


    public CompanyAccounts(String companyName) {
        dialogBox = uiBinder.createAndBindUi(this);
        dialogBox.setText("Company accounts");
        dialogBox.setAnimationEnabled(true);
        dialogBox.setGlassEnabled(true);
        dialogBox.center();

        initTable();

        client.getAccounts(companyName, new MethodCallback<List<BankAccDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.toString() + "\n" + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<BankAccDto> bankAccDtos) {
                cellTable.setRowData(bankAccDtos);
            }
        });
        dialogBox.show();
    }

    @UiHandler("cancel")
    void closeView(ClickEvent event) {
        dialogBox.hide();
    }

    private void initTable() {
        accColumn = new TextColumn<BankAccDto>() {
            @Override
            public String getValue(BankAccDto bankAccDto) {
                return Long.toString(bankAccDto.getCorAcc());
            }
        };

        bankColumn = new TextColumn<BankAccDto>() {
            @Override
            public String getValue(BankAccDto bankAccDto) {
                return bankAccDto.getBankName();
            }
        };

        comColumn = new TextColumn<BankAccDto>() {
            @Override
            public String getValue(BankAccDto bankAccDto) {
                return bankAccDto.getComName();
            }
        };

        cellTable.addColumn(accColumn, "Correspondence account");
        cellTable.addColumn(bankColumn, "Bank");
        cellTable.addColumn(comColumn, "Company");

        //cellTable.setSelectionModel(selectionModel);


    }
}
