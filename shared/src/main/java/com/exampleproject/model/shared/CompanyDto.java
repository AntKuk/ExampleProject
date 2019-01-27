package com.exampleproject.model.shared;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyDto implements BasicDto {
    private int id;
    private String companyName;
    private String address;
    private int tin;
    private int iec;
    private int telNumber;
    private String email;

    public CompanyDto() {
    }

    public CompanyDto(int id, String companyName, String address, int tin, int iec, int telNumber, String email) {
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

    public int getTin() {
        return tin;
    }

    public void setTin(int tin) {
        this.tin = tin;
    }

    public int getIec() {
        return iec;
    }

    public void setIec(int iec) {
        this.iec = iec;
    }

    public int getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(int telNumber) {
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
