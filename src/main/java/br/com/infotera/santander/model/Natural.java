package br.com.infotera.santander.model;

public class Natural {

    private Integer birthCity;

    private String birthState;

    private BankReference bankReference;

    private CellPhone cellPhone;

    private String dateOfIssue;

    private Integer documentId;

    private String documentState;

    private String documentNumber;

    private String gender;

    private String issuingBodyDocument;

    private Integer maritalStatus;

    private Double monthlyIncome;

    private String mother;

    private Integer nationality;

    private Integer occupation;

    private Integer patrimony;

    public Natural() {
    }

    public Natural(Integer birthCity, String birthState, BankReference bankReference, CellPhone cellPhone, String dateOfIssue, Integer documentId, String documentState, String documentNumber, String gender, String issuingBodyDocument, Integer maritalStatus, Double monthlyIncome, String mother, Integer nationality, Integer occupation, Integer patrimony) {
        this.birthCity = birthCity;
        this.birthState = birthState;
        this.bankReference = bankReference;
        this.cellPhone = cellPhone;
        this.dateOfIssue = dateOfIssue;
        this.documentId = documentId;
        this.documentState = documentState;
        this.documentNumber = documentNumber;
        this.gender = gender;
        this.issuingBodyDocument = issuingBodyDocument;
        this.maritalStatus = maritalStatus;
        this.monthlyIncome = monthlyIncome;
        this.mother = mother;
        this.nationality = nationality;
        this.occupation = occupation;
        this.patrimony = patrimony;
    }

    public Integer getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(Integer birthCity) {
        this.birthCity = birthCity;
    }

    public String getBirthState() {
        return birthState;
    }

    public void setBirthState(String birthState) {
        this.birthState = birthState;
    }

    public BankReference getBankReference() {
        return bankReference;
    }

    public void setBankReference(BankReference bankReference) {
        this.bankReference = bankReference;
    }

    public CellPhone getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(CellPhone cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getDocumentState() {
        return documentState;
    }

    public void setDocumentState(String documentState) {
        this.documentState = documentState;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIssuingBodyDocument() {
        return issuingBodyDocument;
    }

    public void setIssuingBodyDocument(String issuingBodyDocument) {
        this.issuingBodyDocument = issuingBodyDocument;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public Integer getNationality() {
        return nationality;
    }

    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    public Integer getOccupation() {
        return occupation;
    }

    public void setOccupation(Integer occupation) {
        this.occupation = occupation;
    }

    public Integer getPatrimony() {
        return patrimony;
    }

    public void setPatrimony(Integer patrimony) {
        this.patrimony = patrimony;
    }
}
