package com.exampleproject.gwt.startpoint.client.pages;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.TextBox;

public class Validator {
    private String errorString = "";
    public Validator() {
    }

    public boolean isEmail(String str) {

        if(str.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            return true;
        }
        else {
            errorString += "\n Wrong email \n";
            return false;
        }
    }

    public boolean isTin(String str) {
        if(str.matches("\\d{10}")) {
            return true;
        }
        else {
            errorString += "\n Wrong TIN. Must contain 10 digits \n";
            return false;
        }
    }

    public boolean isIec(String str) {
        if(str.matches("\\d{9}")) {
            return true;
        }
        else {
            errorString += "\n Wrong IEC. Must contain 9 digits \n";
            return false;
        }
    }

    public boolean isTel(String str) {
        if(str.matches("\\d+")) {
            return true;
        }
        else {
            errorString += "\n Wrong TelNumber. Must contain only digits \n";
            return false;
        }
    }

    public boolean isBic(String str) {
        if(str.matches("\\d{9}")) {
            return true;
        }
        else {
            errorString += "\n Wrong BIC. Must contain 9 digits \n";
            return false;
        }
    }

    public boolean isBankAcc(String str) {
        if(str.matches("\\d{9}")) {
            return true;
        }
        else {
            errorString += "\n Wrong Bank Account number. Must contain 9 digits \n";
            return false;
        }
    }

    public boolean isTotal(String str) {
        if(str.matches("\\d+")) {
            return true;
        }
        else {
            errorString += "\n Wrong Total. Must contain only digits \n";
            return false;
        }
    }


    public String getErrorString() {
        return errorString;
    }

    public void resetErrorString() {
        this.errorString = "";
    }
}
