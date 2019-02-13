package com.exampleproject.web.rest.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="bankacc")
public class BankAccount implements BasicEntity{
    @Id
    @Column(name = "coracc", nullable = false)
    private BigInteger corAcc;

    @Column(name = "idbank", nullable = false)
    private int idBank;

    @Column(name = "idcom", nullable = false)
    private int idCom;

    public BankAccount() {
    }



    public BigInteger getCorAcc() {
        return corAcc;
    }

    public void setCorAcc(BigInteger corAcc) {
        this.corAcc = corAcc;
    }

    public int getIdBank() {
        return idBank;
    }

    public void setIdBank(int idBank) {
        this.idBank = idBank;
    }

    public int getIdCom() {
        return idCom;
    }

    public void setIdCom(int idCom) {
        this.idCom = idCom;
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
