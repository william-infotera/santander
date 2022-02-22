package br.com.infotera.santander.model;

public class BirthCity {

    private String birthState;

    private CellPhone cellPhone;

    private String dateOfBirth;

    private String dateOfIssue;

    private String document;

    private Integer documentId;

    private String documentNumber;

    private String documentState;

    private String email;

    private String gender;

    private String issuingBodyDocument;

    private Integer maritalStatus;

    private Integer monthlyIncome;

    private String mother;

    private String name;

    private Integer nationality;

    private Integer occupation;

    private String participationType;

    private Integer relationshipTypeCompany;

    private Integer patrimony;

    private Spouse spouse;

    public BirthCity() {
    }

    public BirthCity(String birthState, CellPhone cellPhone, String dateOfBirth, String dateOfIssue, String document, Integer documentId, String documentNumber, String documentState, String email, String gender, String issuingBodyDocument, Integer maritalStatus, Integer monthlyIncome, String mother, String name, Integer nationality, Integer occupation, String participationType, Integer relationshipTypeCompany, Integer patrimony, Spouse spouse) {
        this.birthState = birthState;
        this.cellPhone = cellPhone;
        this.dateOfBirth = dateOfBirth;
        this.dateOfIssue = dateOfIssue;
        this.document = document;
        this.documentId = documentId;
        this.documentNumber = documentNumber;
        this.documentState = documentState;
        this.email = email;
        this.gender = gender;
        this.issuingBodyDocument = issuingBodyDocument;
        this.maritalStatus = maritalStatus;
        this.monthlyIncome = monthlyIncome;
        this.mother = mother;
        this.name = name;
        this.nationality = nationality;
        this.occupation = occupation;
        this.participationType = participationType;
        this.relationshipTypeCompany = relationshipTypeCompany;
        this.patrimony = patrimony;
        this.spouse = spouse;
    }

    public String getBirthState() {
        return birthState;
    }

    public void setBirthState(String birthState) {
        this.birthState = birthState;
    }

    public CellPhone getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(CellPhone cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentState() {
        return documentState;
    }

    public void setDocumentState(String documentState) {
        this.documentState = documentState;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Integer monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getParticipationType() {
        return participationType;
    }

    public void setParticipationType(String participationType) {
        this.participationType = participationType;
    }

    public Integer getRelationshipTypeCompany() {
        return relationshipTypeCompany;
    }

    public void setRelationshipTypeCompany(Integer relationshipTypeCompany) {
        this.relationshipTypeCompany = relationshipTypeCompany;
    }

    public Integer getPatrimony() {
        return patrimony;
    }

    public void setPatrimony(Integer patrimony) {
        this.patrimony = patrimony;
    }

    public Spouse getSpouse() {
        return spouse;
    }

    public void setSpouse(Spouse spouse) {
        this.spouse = spouse;
    }
}
