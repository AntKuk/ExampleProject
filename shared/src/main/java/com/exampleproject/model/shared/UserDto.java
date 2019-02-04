package com.exampleproject.model.shared;

public class UserDto implements BasicDto{
    private Integer id;
    private String login;
    private Integer pwd;

    public UserDto() {
    }

    public UserDto(String login, Integer pwd) {
        this.login = login;
        this.pwd = pwd;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getPwd() {
        return pwd;
    }

    public void setPwd(Integer pwd) {
        this.pwd = pwd;
    }
}
