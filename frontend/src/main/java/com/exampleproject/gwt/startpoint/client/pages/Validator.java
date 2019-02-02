package com.exampleproject.gwt.startpoint.client.pages;

public class Validator {
    public Validator() {
    }

    public boolean isEmail(String str) {
        if(str.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            return true;
        }
        return false;
    }
}
