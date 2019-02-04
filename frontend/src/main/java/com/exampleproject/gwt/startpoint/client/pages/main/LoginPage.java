package com.exampleproject.gwt.startpoint.client.pages.main;

import com.exampleproject.gwt.startpoint.client.WorkerClient;
import com.exampleproject.model.shared.UserDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class LoginPage {
    interface MyUiBinder extends UiBinder<DialogBox, LoginPage> {}
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
    }

    @UiHandler("ok")
    void ok(ClickEvent event) {
        String name = loginTB.getText();
        Integer p = pwdTB.getText().hashCode();
        UserDto user = new UserDto(name, p);
        client.login(user, new MethodCallback<Boolean>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                Window.alert("ACCESS DENIED");
            }

            @Override
            public void onSuccess(Method method, Boolean aBoolean) {
                if(aBoolean) {
                    //dialogBox.hide();

                    MainPage mainPage = GWT.create(MainPage.class);
                    if(name.equals("admin")) {
                        mainPage.setAdmin(true);
                    }
                    mainPage.loadPagesForRole();
                    dialogBox.removeFromParent();
                    RootPanel.get().add(mainPage.getElement());
                }
                else {
                    Window.alert("ACCESS DENIED");
                }
            }
        });
    }
}
