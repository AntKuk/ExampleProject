package com.exampleproject.gwt.startpoint.client.pages.main;

import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.model.shared.LoginStatus;
import com.exampleproject.model.shared.UserDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class LoginPage {
    interface MyUiBinder extends UiBinder<DialogBox, LoginPage> {
    }

    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    final WorkerClient client = GWT.create(WorkerClient.class);

    @UiField
    TextBox loginTB;
    @UiField
    PasswordTextBox pwdTB;
    @UiField
    Button ok;


    private DialogBox dialogBox;

    public LoginPage() {
        dialogBox = uiBinder.createAndBindUi(this);
        dialogBox.show();
        loginTB.setFocus(true);
    }

    @UiHandler("ok")
    void ok(ClickEvent event) {
        String name = loginTB.getText();
        Integer p = pwdTB.getText().hashCode();
        UserDto user = new UserDto(name, p, "none");
        client.login(user, new LoginMethodCallback());
    }


    @UiHandler("pwdTB")
    void enter(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            UserDto user = new UserDto(loginTB.getText(), pwdTB.getText().hashCode(), "none");
            client.login(user, new LoginMethodCallback());
        }
    }

    private class LoginMethodCallback implements MethodCallback<LoginStatus> {
        @Override
        public void onFailure(Method method, Throwable exception) {
            Window.alert(exception.toString() + "\n" + exception.getMessage());
        }

        @Override
        public void onSuccess(Method method, LoginStatus loginStatus) {
            if (loginStatus.getRole().equals("none")) {
                Window.alert("ACCESS DENIED");
                return;
            }
            //dialogBox.hide();
            MainPage mainPage = GWT.create(MainPage.class);
            mainPage.setAdmin(loginStatus.getRole().equals("admin"));

            mainPage.loadPagesForRole();
            dialogBox.removeFromParent();
            RootPanel.get().add(mainPage.getElement());

        }
    }

}
