package com.exampleproject.gwt.startpoint.client.pages.company;

import com.exampleproject.model.shared.CompanyDto;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

import static com.exampleproject.gwt.startpoint.client.pages.Styler.setDefaultStyle;

public class UpdateCompanyView extends AddCompanyView {
    private int id = -1;

    public  UpdateCompanyView() {
        super();
        getDialogBox().setText("Update company");
    }

    @UiHandler("ok")
    void add(ClickEvent event) {
        for(TextBox textBox : inputs) {
            setDefaultStyle(textBox);
        }
        validator.resetErrorString();
        if(checkInputs()) {
            CompanyDto companyDto = createDto();
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

    public void fillTextFields(CompanyDto companyDto) {
        this.id = companyDto.getId();
        getCompanyName().setText(companyDto.getCompanyName());
        getCompanyAddress().setText(companyDto.getCompanyName());
        getIec().setText(Long.toString(companyDto.getIec()));
        getTin().setText(Long.toString(companyDto.getTin()));
        getEmail().setText(companyDto.getEmail());
        getTel().setText(Long.toString(companyDto.getTelNumber()));
    }
}
