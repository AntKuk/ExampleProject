package com.exampleproject.gwt.startpoint.client.pages;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.TextBox;

public class Styler {
    public static void setErrorStyle(TextBox textBox) {
        textBox.setStyleName("");
        Style style = textBox.getElement().getStyle();
        style.setBackgroundColor("red");
    }

    public static void setDefaultStyle(TextBox textBox) {
        textBox.setStyleName("");
        Style style = textBox.getElement().getStyle();
        style.setBackgroundColor("white");
    }
}
