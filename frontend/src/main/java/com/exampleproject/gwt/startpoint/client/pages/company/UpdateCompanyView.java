package com.exampleproject.gwt.startpoint.client.pages.company;

import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.model.shared.CompanyDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class UpdateCompanyView extends AddCompanyView {
    private int id = -1;

    public  UpdateCompanyView() {
        super();
        getDialogBox().setText("Update company");
        getBankAcc().removeFromParent();
        getBank().removeFromParent();
        getBankAccLabel().removeFromParent();
        getBankLabel().removeFromParent();
    }

    @UiHandler("ok")
    void add(ClickEvent event) {
        final WorkerClient client = GWT.create(WorkerClient.class);
        Defaults.setServiceRoot(GWT.getHostPageBaseURL() + "backend");
        CompanyDto companyDto = createDto(false);
        companyDto.setId(this.id);
        client.updateCompany(companyDto, new MethodCallback<Boolean>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.toString() + "\n" + exception.getMessage());
            }
            @Override
            public void onSuccess(Method method, Boolean aBoolean) {
                getDialogBox().hide();
                Window.alert("Updated");
                client.getAllCompanies(new MethodCallback<List<CompanyDto>>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert(exception.toString() + "\n" + exception.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, List<CompanyDto> companyDto) {
                        CompaniesPresenter presenter = getCompaniesPresenter();
                        CellTable<CompanyDto> c = presenter.getCellTable();
                        c.setRowData(companyDto);
                        //getCompaniesPresenter().getCellTable().setRowData(companyDto);
                    }
                });
            }
        });
    }

    public void fillTextFields(CompanyDto companyDto) {
        this.id = companyDto.getId();
        getCompanyName().setText(companyDto.getCompanyName());
        getCompanyAddress().setText(companyDto.getCompanyName());
        getIec().setText(Integer.toString(companyDto.getIec()));
        getTin().setText(Integer.toString(companyDto.getTin()));
        getEmail().setText(companyDto.getEmail());
        getTel().setText(Integer.toString(companyDto.getTelNumber()));
        //getBankAcc().setText(Integer.toString(companyDto.getAcc().getCorAcc()));
    }


}
