package com.exampleproject.web.rest.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="bankacc")
public class BankAccount implements BasicEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coracc", nullable = false)
    private BigInteger corAcc;

    @Column(name = "idbank", nullable = false)
    private int idBank;

    @Column(name = "idcom", nullable = false)
    private int idCom;

    public BankAccount() {
    }

    public BankAccount(int idBank, int tin) {
        this.idBank = idBank;
        this.idCom = tin;
    }

    public BigInteger getId() {
        return corAcc;
    }

    public void setId(BigInteger corAcc) {
        this.corAcc = corAcc;
    }

    public int getIdBank() {
        return idBank;
    }

    public void setIdBank(int idBank) {
        this.idBank = idBank;
    }

    public int getTin() {
        return idCom;
    }

    public void setTin(int tin) {
        this.idCom = tin;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "corAcc=" + corAcc +
                ", idBank=" + idBank +
                ", tin=" + idCom +
                '}';
    }
}
