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
}
