package com.exampleproject.web.rest.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="bank")
public class Bank implements BasicEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "bank_id_Sequence")
    @SequenceGenerator(name = "bank_id_Sequence", sequenceName = "bank_seq", allocationSize=1)
    @Column(name = "idbank", nullable = false)
    private BigInteger id;

    @Column(name = "bankname", nullable = false)
    private String bankName;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "bic", nullable = false)
    private int bic;


    public Bank() {
    }

    public Bank(String bankName, String city, int bic) {
        this.bankName = bankName;
        this.city = city;
        this.bic = bic;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getBic() {
        return bic;
    }

    public void setBic(int bic) {
        this.bic = bic;
    }


    @Override
    public String toString() {
        return "Bank{" +
                "idBank=" + id +
                ", bankName='" + bankName + '\'' +
                ", city='" + city + '\'' +
                ", bic=" + bic +
                '}';
    }
}
