package com.exampleproject.gwt.startpoint.client.pages.user;

import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.gwt.startpoint.client.presenter.TabPresenter;
import com.exampleproject.model.shared.UserDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class UserPresenter implements TabPresenter {
    interface MyUiBinder extends UiBinder<VerticalPanel, UserPresenter> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private final WorkerClient client = GWT.create(WorkerClient.class);
    private final SingleSelectionModel<UserDto> selectionModel = new SingleSelectionModel<>();

    @UiField
    CellTable cellTable;
    @UiField
    Button addButton;
    @UiField
    Button deleteButton;

    private TextColumn<UserDto> idColumn;
    private TextColumn<UserDto> userColumn;

    private VerticalPanel root;

    public UserPresenter() {
        root = uiBinder.createAndBindUi(this);
        initTable();
        client.getUsers(new MethodCallback<List<UserDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.toString() + "\n" + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<UserDto> userDtos) {
                cellTable.setRowData(userDtos);
            }
        });
    }

    private void initTable() {
        idColumn = new TextColumn<UserDto>() {
            @Override
            public String getValue(UserDto userDto) {
                return Integer.toString(userDto.getId());
            }
        };

        userColumn = new TextColumn<UserDto>() {
            @Override
            public String getValue(UserDto userDto) {
                return userDto.getLogin();
            }
        };

        cellTable.addColumn(idColumn, "Id");
        cellTable.addColumn(userColumn, "Login");

        cellTable.setSelectionModel(selectionModel);
    }






    public VerticalPanel getElement() {
        return root;
    }

    public CellTable getCellTable() {
        return cellTable;
    }
}
