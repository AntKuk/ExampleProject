package com.exampleproject.gwt.startpoint.client.pages.company;

import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.model.shared.CompanyDto;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompaniesPresenter {
    interface MyUiBinder extends UiBinder<VerticalPanel, CompaniesPresenter> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private final WorkerClient client = GWT.create(WorkerClient.class);

    private VerticalPanel root;

    @UiField
    CellTable cellTable;
    @UiField
    Button addButton;

    @UiHandler("addButton")
    void addBtn(ClickEvent event) {
        new AddCompanyView();
    }

    private TextColumn<CompanyDto> idColumn;
    private TextColumn<CompanyDto> nameColumn;
    private TextColumn<CompanyDto> addrColumn;
    private TextColumn<CompanyDto> tinColumn;
    private TextColumn<CompanyDto> iecColumn;
    private TextColumn<CompanyDto> telColumn;
    private TextColumn<CompanyDto> emailColumn;


    public CompaniesPresenter() {

        root = uiBinder.createAndBindUi(this);
        initTable();
        Defaults.setServiceRoot(GWT.getHostPageBaseURL() + "backend");
        client.getAll(new MethodCallback<List<CompanyDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.toString() + "\n" + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<CompanyDto> companyDto) {

                cellTable.setRowData(companyDto);
            }
        });

    }

    public VerticalPanel getElement() {
        return root;
    }

    public CellTable getCellTable() {
        return cellTable;
    }

    private void initTable() {
        idColumn = new TextColumn<CompanyDto>() {
            @Override
            public String getValue(CompanyDto companyDto) {
                return Integer.toString(companyDto.getId());
            }
        };

        nameColumn = new TextColumn<CompanyDto>() {
            @Override
            public String getValue(CompanyDto companyDto) {
                return companyDto.getCompanyName();
            }
        };

        addrColumn = new TextColumn<CompanyDto>() {
            @Override
            public String getValue(CompanyDto companyDto) {
                return companyDto.getAddress();
            }
        };

        tinColumn = new TextColumn<CompanyDto>() {
            @Override
            public String getValue(CompanyDto companyDto) {
                return Integer.toString(companyDto.getTin());
            }
        };

        iecColumn = new TextColumn<CompanyDto>() {
            @Override
            public String getValue(CompanyDto companyDto) {
                return Integer.toString(companyDto.getIec());
            }
        };

        telColumn = new TextColumn<CompanyDto>() {
            @Override
            public String getValue(CompanyDto companyDto) {
                return Integer.toString(companyDto.getTelNumber());
            }
        };

        emailColumn = new TextColumn<CompanyDto>() {
            @Override
            public String getValue(CompanyDto companyDto) {
                return companyDto.getEmail();
            }
        };

        cellTable.addColumn(idColumn, "ID");
        cellTable.addColumn(nameColumn, "Name");
        cellTable.addColumn(addrColumn, "Address");
        cellTable.addColumn(tinColumn, "TIN");
        cellTable.addColumn(iecColumn, "IEC");
        cellTable.addColumn(telColumn, "Tel");
        cellTable.addColumn(emailColumn, "Email");
    }


}
