package com.exampleproject.gwt.startpoint.client.pages.bank;

import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.model.shared.BankDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class UpdateBankView extends AddBankView {
    private int id = -1;

    public  UpdateBankView() {
        super();
        getDialogBox().setText("Update bank");
    }

    @UiHandler("ok")
    void add(ClickEvent event) {
        final WorkerClient client = GWT.create(WorkerClient.class);
        Defaults.setServiceRoot(GWT.getHostPageBaseURL() + "backend");
        BankDto bankDto = createDto();
        bankDto.setId(this.id);
        client.updateBank(bankDto, new MethodCallback<Boolean>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.toString() + "\n" + exception.getMessage());
            }
            @Override
            public void onSuccess(Method method, Boolean aBoolean) {
                getDialogBox().hide();
                Window.alert("Updated");
                client.getAllBanks(new MethodCallback<List<BankDto>>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert(exception.toString() + "\n" + exception.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, List<BankDto> bankDto) {
                        BanksPresenter presenter = getBanksPresenter();
                        CellTable<BankDto> c = presenter.getCellTable();
                        c.setRowData(bankDto);
                        //getCompaniesPresenter().getCellTable().setRowData(companyDto);
                    }
                });
            }
        });
    }

    public void fillTextFields(BankDto bankDto) {
        this.id = bankDto.getId();
        getBankName().setText(bankDto.getBankName());
        getBankCity().setText(bankDto.getCity());
        getBankBic().setText(Integer.toString(bankDto.getBic()));
    }
}
