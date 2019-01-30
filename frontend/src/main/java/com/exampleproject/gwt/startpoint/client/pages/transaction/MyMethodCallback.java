package com.exampleproject.gwt.startpoint.client.pages.transaction;

import com.exampleproject.model.shared.CompanyDto;
import com.google.gwt.user.client.Window;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class MyMethodCallback implements MethodCallback<List<CompanyDto>> {

    public List<CompanyDto> list;
    @Override
    public void onFailure(Method method, Throwable exception) {
        Window.alert(exception.toString() + "\n" + exception.getMessage());
    }
    @Override
    public void onSuccess(Method method, List<CompanyDto> companyDtos) {
        this.list = companyDtos;
        System.out.println();
    }

    public List<CompanyDto> getList() {
        return list;
    }
}
