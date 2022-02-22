package br.com.infotera.santander.model;

import java.util.Date;

public class Customer {

    private String dateOfBirthFoundation;

    private String document;

    private CellPhone cellPhone;

    private String participationType;

    private Address address;

    private Legal legal;

    private Natural natural;

    private String name;

    private String email;

    public Customer() {
    }

    public Customer(String dateOfBirthFoundation, String document, CellPhone cellPhone, String participationType, Address address, Legal legal, Natural natural, String name, String email) {
        this.dateOfBirthFoundation = dateOfBirthFoundation;
        this.document = document;
        this.cellPhone = cellPhone;
        this.participationType = participationType;
        this.address = address;
        this.legal = legal;
        this.natural = natural;
        this.name = name;
        this.email = email;
    }

    public Customer(String dtNascimento, String nrDocumento, CellPhone cellPhone) {
        this.dateOfBirthFoundation = dtNascimento;
        this.document = nrDocumento;
        this.cellPhone = cellPhone;
    }

    public String getDateOfBirthFoundation() {
        return dateOfBirthFoundation;
    }

    public void setDateOfBirthFoundation(String dateOfBirthFoundation) {
        this.dateOfBirthFoundation = dateOfBirthFoundation;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public CellPhone getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(CellPhone cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getParticipationType() {
        return participationType;
    }

    public void setParticipationType(String participationType) {
        this.participationType = participationType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Legal getLegal() {
        return legal;
    }

    public void setLegal(Legal legal) {
        this.legal = legal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Natural getNatural() {
        return natural;
    }

    public void setNatural(Natural natural) {
        this.natural = natural;
    }
}
