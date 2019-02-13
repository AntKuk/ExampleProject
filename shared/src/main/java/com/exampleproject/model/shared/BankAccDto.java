package com.exampleproject.model.shared;

import java.math.BigInteger;

public class BankAccDto implements BasicDto{
    private Long corAcc;
    private String bankName;
    private String comName;

    public BankAccDto() {
    }

    public BankAccDto(long corAcc, String bankName, String comName) {
        this.corAcc = corAcc;
        this.bankName = bankName;
        this.comName = comName;
    }

    public Long getCorAcc() {
        return corAcc;
    }

    public void setCorAcc(Long corAcc) {
        this.corAcc = corAcc;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }
}
