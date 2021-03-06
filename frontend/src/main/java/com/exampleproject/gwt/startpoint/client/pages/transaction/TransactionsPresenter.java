package com.exampleproject.gwt.startpoint.client.pages.transaction;

import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.gwt.startpoint.client.presenter.TabPresenter;
import com.exampleproject.model.shared.TransactDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class TransactionsPresenter implements TabPresenter {
    interface MyUiBinder extends UiBinder<VerticalPanel, TransactionsPresenter> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private final WorkerClient client = GWT.create(WorkerClient.class);
    private final SingleSelectionModel<TransactDto> selectionModel = new SingleSelectionModel<>();

    @UiField
    CellTable cellTable;
    @UiField
    Button addButton;
    @UiField
    Button deleteButton;
    @UiField
    Label label;

    private TextColumn<TransactDto> idColumn;
    private TextColumn<TransactDto> dateColumn;
    private TextColumn<TransactDto> sellerColumn;
    private TextColumn<TransactDto> customerColumn;
    private TextColumn<TransactDto> totalColumn;
    private TextColumn<TransactDto> sellerAccColumn;
    private TextColumn<TransactDto> customerAccColumn;

    private VerticalPanel root;

    public TransactionsPresenter() {
        root = uiBinder.createAndBindUi(this);
        label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        initTable();
        client.getAllTransacts(new MethodCallback<List<TransactDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.toString() + "\n" + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<TransactDto> transactDto) {
                cellTable.setRowData(transactDto);
            }
        });
    }

    @UiHandler("addButton")
    void addBtn(ClickEvent event) {
        AddTransactView addTransactView = GWT.create(AddTransactView.class);
        addTransactView.setTransactionsPresenter(this);
    }

    @UiHandler("deleteButton")
    void deleteDtn(ClickEvent event) {
        TransactDto transactDto = ((SingleSelectionModel<TransactDto>)cellTable.getSelectionModel()).getSelectedObject();
        if(transactDto != null) {

            client.deleteTransact(transactDto.getId(), new MethodCallback<Boolean>() {
                @Override
                public void onFailure(Method method, Throwable exception) {
                    Window.alert(exception.toString() + "\n" + exception.getMessage());
                }

                @Override
                public void onSuccess(Method method, Boolean aBoolean) {
                    Window.alert("Deleted");

                    client.getAllTransacts(new MethodCallback<List<TransactDto>>() {
                        @Override
                        public void onFailure(Method method, Throwable exception) {
                            Window.alert(exception.toString() + "\n" + exception.getMessage());
                        }
                        @Override
                        public void onSuccess(Method method, List<TransactDto> companyDto) {
                            getCellTable().setRowData(companyDto);
                        }
                    });
                }
            });
        }
        else {
            Window.alert("Select transaction");
        }
    }

    private void initTable() {
        idColumn = new TextColumn<TransactDto>() {
            @Override
            public String getValue(TransactDto transactDto) {
                return Integer.toString(transactDto.getId());
            }
        };
        dateColumn = new TextColumn<TransactDto>() {
            @Override
            public String getValue(TransactDto transactDto) {
                return transactDto.getTranDate();
            }
        };
        sellerColumn = new TextColumn<TransactDto>() {
            @Override
            public String getValue(TransactDto transactDto) {
                return transactDto.getSeller();
            }
        };
        customerColumn = new TextColumn<TransactDto>() {
            @Override
            public String getValue(TransactDto transactDto) {
                return transactDto.getCustomer();
            }
        };
        totalColumn = new TextColumn<TransactDto>() {
            @Override
            public String getValue(TransactDto transactDto) {
                return Integer.toString(transactDto.getTotal());
            }
        };
        sellerAccColumn = new TextColumn<TransactDto>() {
            @Override
            public String getValue(TransactDto transactDto) {
                return Long.toString(transactDto.getSellerAcc());
            }
        };
        customerAccColumn = new TextColumn<TransactDto>() {
            @Override
            public String getValue(TransactDto transactDto) {
                return Long.toString(transactDto.getCustomerAcc());
            }
        };

        cellTable.addColumn(idColumn, "Id");
        cellTable.addColumn(dateColumn, "Date");
        cellTable.addColumn(sellerColumn, "Seller");
        cellTable.addColumn(customerColumn, "Customer");
        cellTable.addColumn(totalColumn, "Total");
        cellTable.addColumn(sellerAccColumn, "Seller Acc");
        cellTable.addColumn(customerAccColumn, "Customer Acc");


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

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }
}
