package com.exampleproject.gwt.startpoint.client.pages.user;

import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.model.shared.UserDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class AddUserView {
    interface MyUiBinder extends UiBinder<DialogBox, AddUserView> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    Button ok;
    @UiField
    Button cancel;
    @UiField
    TextBox loginTB;
    @UiField
    TextBox pwdTB;



    private DialogBox dialogBox;
    private UserPresenter userPresenter;



    public AddUserView() {
        dialogBox = uiBinder.createAndBindUi(this);

        dialogBox.setText("Adding user");
        dialogBox.setAnimationEnabled(true);
        dialogBox.setGlassEnabled(true);
        dialogBox.center();

        dialogBox.show();
    }

    @UiHandler("cancel")
    void closeView(ClickEvent event) {
        dialogBox.hide();
    }

    @UiHandler("ok")
    void ok(ClickEvent event) {
        final WorkerClient client = GWT.create(WorkerClient.class);
        UserDto userDto = new UserDto(loginTB.getText(), pwdTB.getText().hashCode());
        client.addUser(userDto, new MethodCallback<Boolean>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert(exception.toString() + "\n" + exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, Boolean aBoolean) {
                if(aBoolean) {
                    Window.alert("User added");
                    dialogBox.hide();
                    client.getUsers(new MethodCallback<List<UserDto>>() {
                        @Override
                        public void onFailure(Method method, Throwable exception) {
                            Window.alert(exception.toString() + "\n" + exception.getMessage());
                        }

                        @Override
                        public void onSuccess(Method method, List<UserDto> userDtos) {
                            userPresenter.getCellTable().setRowData(userDtos);
                        }
                    });
                }
                else {
                    Window.alert("There is a user with this login");
                }
            }
        });
    }

    public void setUserPresenter(UserPresenter userPresenter) {
        this.userPresenter = userPresenter;
    }
}
