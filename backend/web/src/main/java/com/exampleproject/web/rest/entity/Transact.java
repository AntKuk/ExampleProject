package com.exampleproject.web.rest.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


@Entity
@Table(name = "transaction")
public class Transact implements BasicEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtran", nullable = false)
    private BigInteger id;

    @Column(name = "trandate", nullable = false)
    private Date tranDate;

    //@Column(name = "idseller", nullable = false)
    @ManyToOne
    @JoinColumn(name = "idseller", referencedColumnName = "idcom")
    private Company idSeller;
    //private int idSeller;

    @ManyToOne
    @JoinColumn(name = "idcustomer", referencedColumnName = "idcom")
    private Company idCustomer;
    //@Column(name = "idcustomer")
    //private int idCustomer;

    @Column(name = "total")
    private int total;


    @ManyToOne
    @JoinColumn(name = "coracc_sel", referencedColumnName = "coracc")
    private BankAccount sellerAcc;

    @ManyToOne
    @JoinColumn(name = "coracc_cust", referencedColumnName = "coracc")
    private BankAccount customerAcc;
    //@Column(name = "coracc_sel")
    //private int selAcc;

    //@Column(name = "coracc_cust")
    //private int custAcc;


    public Transact() {
    }

    public Transact(Date tranDate, Company idSeller, Company idCustomer, int total, BankAccount sellerAcc, BankAccount customerAcc) {
        this.tranDate = tranDate;
        this.idSeller = idSeller;
        this.idCustomer = idCustomer;
        this.total = total;
        this.sellerAcc = sellerAcc;
        this.customerAcc = customerAcc;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public Company getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(Company idSeller) {
        this.idSeller = idSeller;
    }

    public Company getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Company idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public BankAccount getSellerAcc() {
        return sellerAcc;
    }

    public void setSellerAcc(BankAccount sellerAcc) {
        this.sellerAcc = sellerAcc;
    }

    public BankAccount getCustomerAcc() {
        return customerAcc;
    }

    public void setCustomerAcc(BankAccount customerAcc) {
        this.customerAcc = customerAcc;
    }

    @Override
    public String toString() {
        return "Transact{" +
                "idTran=" + id +
                ", tranDate=" + tranDate +
                ", idSeller=" + idSeller +
                ", idCustomer=" + idCustomer +
                ", total=" + total +
                ", sellerAcc=" + sellerAcc +
                ", customerAcc=" + customerAcc +
                '}';
    }
}
