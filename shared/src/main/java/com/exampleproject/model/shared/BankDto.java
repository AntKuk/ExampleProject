package com.exampleproject.model.shared;

public class BankDto implements BasicDto{
    private int id;
    private String bankName;
    private String city;
    private long bic;

    public BankDto() {
    }

    public BankDto(int id, String bankName, String city, long bic) {
        this.id = id;
        this.bankName = bankName;
        this.city = city;
        this.bic = bic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public long getBic() {
        return bic;
    }

    public void setBic(long bic) {
        this.bic = bic;
    }


    @Override
    public String toString() {
        return "BankDto{" +
                "id=" + id +
                ", bankName='" + bankName + '\'' +
                ", city='" + city + '\'' +
                ", bic=" + bic +
                '}';
    }
}
