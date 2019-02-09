package com.exampleproject.gwt.startpoint.client.pages.account;

import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.gwt.startpoint.client.presenter.TabPresenter;
import com.exampleproject.model.shared.BankAccDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class AccountsPresenter implements TabPresenter {
    interface MyUiBinder extends UiBinder<VerticalPanel, AccountsPresenter> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private final WorkerClient client = GWT.create(WorkerClient.class);
    private final SingleSelectionModel<BankAccDto> selectionModel = new SingleSelectionModel<>();

    @UiField
    CellTable cellTable;
    @UiField
    Button addButton;
    @UiField
    Button deleteButton;

    private TextColumn<BankAccDto> accColumn;
    private TextColumn<BankAccDto> bankColumn;
    private TextColumn<BankAccDto> comColumn;


    private VerticalPanel root;

    public AccountsPresenter() {
        root = uiBinder.createAndBindUi(this);
        initTable();
        Defaults.setServiceRoot(GWT.getHostPageBaseURL() + "backend");
        client.getAllAccounts(new MethodCallback<List<BankAccDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.toString() + "\n" + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<BankAccDto> bankAccDto) {

                cellTable.setRowData(bankAccDto);
            }
        });
    }

    @UiHandler("addButton")
    void addBtn(ClickEvent event) {
        AddAccountView addAccountView = GWT.create(AddAccountView.class);
        addAccountView.setAccountsPresenter(this);
    }

    @UiHandler("deleteButton")
    void deleteBtn(ClickEvent event) {
        BankAccDto bankAccDto = ((SingleSelectionModel<BankAccDto>)cellTable.getSelectionModel()).getSelectedObject();
        if(bankAccDto != null) {
            client.deleteAccount(bankAccDto.getCorAcc(), new MethodCallback<Boolean>() {
                @Override
                public void onFailure(Method method, Throwable exception) {
                    Window.alert(exception.toString() + "\n" + exception.getMessage());
                }

                @Override
                public void onSuccess(Method method, Boolean aBoolean) {
                    Window.alert("Deleted");
                    client.getAllAccounts(new MethodCallback<List<BankAccDto>>() {
                        @Override
                        public void onFailure(Method method, Throwable exception) {
                            Window.alert(exception.toString() + "\n" + exception.getMessage());
                        }
                        @Override
                        public void onSuccess(Method method, List<BankAccDto> bankAccDtos) {
                            getCellTable().setRowData(bankAccDtos);
                        }
                    });
                }
            });
        }
        else {
            Window.alert("Select bank");
        }
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

        cellTable.setSelectionModel(selectionModel);


    }

    public VerticalPanel getElement() {
        return root;
    }

    public CellTable getCellTable() {
        return cellTable;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }
}
