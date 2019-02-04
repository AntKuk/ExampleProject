package com.exampleproject.web.rest.entity;

import javax.persistence.*;

@Entity
@Table(name="myuser")
public class User implements BasicEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "myuser_id_Sequence")
    @SequenceGenerator(name = "myuser_id_Sequence", sequenceName = "myuser_seq", allocationSize=1)
    @Column(name = "iduser", nullable = false)
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "pwd")
    private int pwd;

    public User() {
    }

    public User(String login, int pwd) {
        this.login = login;
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPwd() {
        return pwd;
    }

    public void setPwd(int pwd) {
        this.pwd = pwd;
    }
}
