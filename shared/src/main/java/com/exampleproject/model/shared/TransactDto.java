package com.exampleproject.model.shared;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class TransactDto implements BasicDto{
    private int id;
    private Date tranDate;
    private String seller;
    private String customer;
    private int total;
    private long sellerAcc;
    private long customerAcc;

    public TransactDto() {
    }

    public TransactDto(int id, Date tranDate, String seller, String customer, int total, long sellerAcc, long customerAcc) {
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

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
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
