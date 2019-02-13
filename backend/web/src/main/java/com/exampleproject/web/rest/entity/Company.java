package com.exampleproject.web.rest.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="company")
public class Company implements BasicEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "company_id_Sequence")
    @SequenceGenerator(name = "company_id_Sequence", sequenceName = "company_seq", allocationSize=1)
    @Column(name = "idcom", nullable = false)
    private BigInteger id;

    @Column(name = "comname", nullable = false)
    private String companyName;

    @Column(name = "addr", nullable = false)
    private String address;

    @Column(name = "tin", nullable = false)
    private long tin;

    @Column(name = "iec", nullable = false)
    private long iec;

    @Column(name = "tel", nullable = false)
    private long telNumber;

    @Column(name = "email", nullable = false)
    private String email;

    public Company() {
    }

    public Company(String companyName, String address, long tin, long iec, long telNumber, String email) {
        this.companyName = companyName;
        this.address = address;
        this.tin = tin;
        this.iec = iec;
        this.telNumber = telNumber;
        this.email = email;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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
        return "Company{" +
                "idCom=" + id +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", tin=" + tin +
                ", iec=" + iec +
                ", telNumber=" + telNumber +
                ", email='" + email + '\'' +
                '}';
    }
}
