package com.exampleproject.gwt.startpoint.client.pages.bank;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class AddBankView {
    interface MyUiBinder extends UiBinder<DialogBox, AddBankView> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    Button ok;

    @UiField
    Button cancel;


    private DialogBox dialogBox;



    public AddBankView() {
        dialogBox = uiBinder.createAndBindUi(this);
        dialogBox.show();
    }

    @UiHandler("cancel")
    void closeView(ClickEvent event) {
       dialogBox.hide();
    }

}
