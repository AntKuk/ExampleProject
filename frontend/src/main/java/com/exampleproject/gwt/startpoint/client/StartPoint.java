package com.exampleproject.gwt.startpoint.client;


import com.exampleproject.gwt.startpoint.client.pages.bank.BanksPresenter;
import com.exampleproject.gwt.startpoint.client.pages.company.CompaniesPresenter;
import com.exampleproject.gwt.startpoint.client.pages.main.MainPage;
import com.exampleproject.gwt.startpoint.client.pages.transaction.TransactionsPresenter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import com.exampleproject.model.shared.TestDto;
import com.google.gwt.view.client.ListDataProvider;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.Arrays;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class StartPoint implements EntryPoint {

    private final WorkerClient client = GWT.create(WorkerClient.class);

    private static final String HELLO_MESSAGE = "Hi, I'm your gwt application!";
/*
    //Test class
    private static class Company {
        private final String address;
        private final String name;

        public Company(String name, String address) {
            this.name = name;
            this.address = address;
        }
        public String getAddress() {
            return address;
        }
        public String getName() {
            return  name;
        }
    }

    //Test List of Company
    private static List<Company> companies = Arrays.asList(new Company("John",
            "123 Fourth Road"), new Company("Mary", "222 Lancer Lane"), new Company(
            "Zander", "94 Road Street"));


*/
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        Defaults.setServiceRoot(GWT.getHostPageBaseURL() + "backend");
        MainPage mainPage = GWT.create(MainPage.class);


        //CompaniesPresenter companiesPresenter = GWT.create(CompaniesPresenter.class);
/*        CellTable<Company> cellTable = companiesPresenter.getCellTable();
        TextColumn<Company> companyName = new TextColumn<Company>() {
            @Override
            public String getValue(Company company) {
                return company.getName();
            }
        };
        cellTable.addColumn(companyName, "Name");
        TextColumn<Company> companyAdress = new TextColumn<Company>() {
            @Override
            public String getValue(Company company) {
                return company.getAddress();
            }
        };
        cellTable.addColumn(companyAdress, "Adress");

        // Create a data provider.
        ListDataProvider<Company> dataProvider = new ListDataProvider<Company>();

        // Connect the table to the data provider.
        dataProvider.addDataDisplay(cellTable);

        // Add the data to the data provider, which automatically pushes it to the
        // widget.
        List<Company> list = dataProvider.getList();
        for (Company company : companies) {
            list.add(company);
        }
*/
        //RootPanel.get().add(presenter.getElement());

        //BanksPresenter banksPresenter = GWT.create(BanksPresenter.class);
        //TransactionsPresenter transactionsPresenter = GWT.create(TransactionsPresenter.class);

        //mainPage.getSimplePanel().add(companiesPresenter.getElement());
/*
        //Adding Buttons Handlers
        mainPage.getBanksBtn().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Widget widget = mainPage.getSimplePanel().getWidget();
                if(widget != null) {
                    mainPage.getSimplePanel().remove(widget);
                }
                mainPage.getSimplePanel().add(banksPresenter.getElement());
            }
        });

        mainPage.getTransactionsBtn().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Widget widget = mainPage.getSimplePanel().getWidget();
                if(widget != null) {
                    mainPage.getSimplePanel().remove(widget);
                }
                mainPage.getSimplePanel().add(transactionsPresenter.getElement());
            }
        });

        mainPage.getCompaniesBtn().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Widget widget = mainPage.getSimplePanel().getWidget();
                if(widget != null) {
                    mainPage.getSimplePanel().remove(widget);
                }
                mainPage.getSimplePanel().add(companiesPresenter.getElement());
            }
        });


*/




        RootPanel.get().add(mainPage.getElement());



        Button send = new Button("Send");
        send.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                client.get(new MethodCallback<TestDto>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        // TODO Auto-generated method stub
                        Window.alert(exception.toString() + "\n" + exception.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, TestDto response) {
                        // TODO Auto-generated method stub
                        Window.alert(response.getMessage());
                    }

                });
            }
        });
        RootPanel.get().add(send);

        Button testDb = new Button("Test DB");
        testDb.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                client.getDb(new MethodCallback<Boolean>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        // TODO Auto-generated method stub
                        Window.alert(exception.toString() + "\n" + exception.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, Boolean response) {
                        // TODO Auto-generated method stub
                        Window.alert(response.toString());
                    }

                });
            }
        });
        RootPanel.get().add(testDb);

        final Label label = new Label(HELLO_MESSAGE);
        RootPanel.get().add(label);

        final Button button = new Button("Click me");
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Window.alert("You press on me! Don't it anymore");
            }
        });
        RootPanel.get().add(button);
    }
}
