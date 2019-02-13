package com.exampleproject.model.shared;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
//@Component
//@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyDto implements BasicDto {
    private int id;
    private String companyName;
    private String address;
    private long tin;
    private long iec;
    private long telNumber;
    private String email;
    //private BankAccDto acc;

    public CompanyDto() {
    }

    public CompanyDto(int id, String companyName, String address, long tin, long iec, long telNumber, String email) {
        this.id = id;
        this.companyName = companyName;
        this.address = address;
        this.tin = tin;
        this.iec = iec;
        this.telNumber = telNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTin() {
        return tin;
    }

    public void setTin(long tin) {
        this.tin = tin;
    }

    public long getIec() {
        return iec;
    }

    public void setIec(long iec) {
        this.iec = iec;
    }

    public long getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(long telNumber) {
        this.telNumber = telNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CompanyDto{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", tin=" + tin +
                ", iec=" + iec +
                ", telNumber=" + telNumber +
                ", email='" + email + '\'' +
                '}';
    }
}
