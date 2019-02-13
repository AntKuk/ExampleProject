package com.exampleproject.model.shared;

public class LoginStatus {
    private String role;

    public LoginStatus() {
        this.role = "none";
    }

    public LoginStatus(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
