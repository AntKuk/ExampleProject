package com.exampleproject.model.shared;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
//@Component
//@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class TransactDto implements BasicDto, Serializable {
    private int id;
    //@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private String tranDate;
    private String seller;
    private String customer;
    private int total;
    private long sellerAcc;
    private long customerAcc;

    public TransactDto() {
    }

    public TransactDto(int id, String tranDate, String seller, String customer, int total, long sellerAcc, long customerAcc) {
        this.id = id;
        this.tranDate = tranDate;
        this.seller = seller;
        this.customer = customer;
        this.total = total;
        this.sellerAcc = sellerAcc;
        this.customerAcc = customerAcc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getSellerAcc() {
        return sellerAcc;
    }

    public void setSellerAcc(long sellerAcc) {
        this.sellerAcc = sellerAcc;
    }

    public long getCustomerAcc() {
        return customerAcc;
    }

    public void setCustomerAcc(long customerAcc) {
        this.customerAcc = customerAcc;
    }

    @Override
    public String toString() {
        return "TransactDto{" +
                "id=" + id +
                ", tranDate=" + tranDate +
                ", seller='" + seller + '\'' +
                ", customer='" + customer + '\'' +
                ", total=" + total +
                ", sellerAcc=" + sellerAcc +
                ", customerAcc=" + customerAcc +
                '}';
    }
}
