package com.exampleproject.model.shared;

import java.math.BigInteger;

public class BankAccDto implements BasicDto{
    private int corAcc;
    private String idBank;
    private String idCom;

    public BankAccDto() {
    }

    public BankAccDto(int corAcc, String idBank, String idCom) {
        this.corAcc = corAcc;
        this.idBank = idBank;
        this.idCom = idCom;
    }

    public int getCorAcc() {
        return corAcc;
    }

    public void setCorAcc(int corAcc) {
        this.corAcc = corAcc;
    }

    public String getIdBank() {
        return idBank;
    }

    public void setIdBank(String idBank) {
        this.idBank = idBank;
    }

    public String getIdCom() {
        return idCom;
    }

    public void setIdCom(String idCom) {
        this.idCom = idCom;
    }
}
