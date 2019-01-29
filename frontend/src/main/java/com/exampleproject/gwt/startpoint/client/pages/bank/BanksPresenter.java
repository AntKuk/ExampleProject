package com.exampleproject.gwt.startpoint.client.pages.bank;

import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.gwt.startpoint.client.presenter.TabPresenter;
import com.exampleproject.model.shared.BankDto;
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
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class BanksPresenter implements TabPresenter {
    interface MyUiBinder extends UiBinder<VerticalPanel, BanksPresenter> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private final WorkerClient client = GWT.create(WorkerClient.class);

    @UiField
    CellTable cellTable;

    @UiField
    Button addButton;

    private TextColumn<BankDto> idColumn;
    private TextColumn<BankDto> nameColumn;
    private TextColumn<BankDto> cityColumn;
    private TextColumn<BankDto> bicColumn;
    private TextColumn<BankDto> coraccColumn;


    private VerticalPanel root;

    public BanksPresenter() {
        root = uiBinder.createAndBindUi(this);
        initTable();
        Defaults.setServiceRoot(GWT.getHostPageBaseURL() + "backend");
        client.getAllBanks(new MethodCallback<List<BankDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.toString() + "\n" + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<BankDto> bankDto) {

                cellTable.setRowData(bankDto);
            }
        });
    }

    public VerticalPanel getElement() {
        return root;
    }

    public CellTable getCellTable() {
        return cellTable;
    }

    @UiHandler("addButton")
    void addBtn(ClickEvent event) {
        new AddBankView();
    }

    private void initTable() {
        idColumn = new TextColumn<BankDto>() {
            @Override
            public String getValue(BankDto bankDto) {
                return Integer.toString(bankDto.getId());
            }
        };

        nameColumn = new TextColumn<BankDto>() {
            @Override
            public String getValue(BankDto bankDto) {
                return bankDto.getBankName();
            }
        };

        cityColumn = new TextColumn<BankDto>() {
            @Override
            public String getValue(BankDto bankDto) {
                return bankDto.getCity();
            }
        };

        bicColumn = new TextColumn<BankDto>() {
            @Override
            public String getValue(BankDto bankDto) {
                return Integer.toString(bankDto.getBic());
            }
        };

        cellTable.addColumn(idColumn, "Id");
        cellTable.addColumn(nameColumn, "Name");
        cellTable.addColumn(cityColumn, "City");
        cellTable.addColumn(bicColumn, "BIC");
    }

}
