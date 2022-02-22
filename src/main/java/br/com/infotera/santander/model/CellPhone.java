package br.com.infotera.santander.model;

public class CellPhone {

    private String ddd;

    private String number;

    public CellPhone() {
    }

    public CellPhone(String ddd, String number) {
        this.ddd = ddd;
        this.number = number;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
