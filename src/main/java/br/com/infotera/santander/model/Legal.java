package br.com.infotera.santander.model;

import com.google.gson.annotations.SerializedName;

public class Legal {

    @SerializedName("bankReference")
    private BankReference bankReference;

    @SerializedName("companySize")
    private String companySize;

    @SerializedName("economicActivity")
    private Integer economicActivity;

    @SerializedName("groupEconomicActivity")
    private String groupEconomicActivity;

    @SerializedName("guarantor")
    private Guarantor guarantor;

    @SerializedName("birthCity")
    private BirthCity birthCity;

    @SerializedName("legalNature")
    private Integer legalNature;

    @SerializedName("monthlyInvoicing")
    private Integer monthlyInvoicing;

    @SerializedName("phone")
    private CellPhone cellPhone;

    public Legal() {
    }

    public Legal(BankReference bankReference, String companySize, Integer economicActivity, String groupEconomicActivity, Guarantor guarantor, BirthCity birthCity, Integer legalNature, Integer monthlyInvoicing, CellPhone cellPhone) {
        this.bankReference = bankReference;
        this.companySize = companySize;
        this.economicActivity = economicActivity;
        this.groupEconomicActivity = groupEconomicActivity;
        this.guarantor = guarantor;
        this.birthCity = birthCity;
        this.legalNature = legalNature;
        this.monthlyInvoicing = monthlyInvoicing;
        this.cellPhone = cellPhone;
    }

    public BankReference getBankReference() {
        return bankReference;
    }

    public void setBankReference(BankReference bankReference) {
        this.bankReference = bankReference;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public Integer getEconomicActivity() {
        return economicActivity;
    }

    public void setEconomicActivity(Integer economicActivity) {
        this.economicActivity = economicActivity;
    }

    public String getGroupEconomicActivity() {
        return groupEconomicActivity;
    }

    public void setGroupEconomicActivity(String groupEconomicActivity) {
        this.groupEconomicActivity = groupEconomicActivity;
    }

    public Guarantor getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(Guarantor guarantor) {
        this.guarantor = guarantor;
    }

    public BirthCity getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(BirthCity birthCity) {
        this.birthCity = birthCity;
    }

    public Integer getLegalNature() {
        return legalNature;
    }

    public void setLegalNature(Integer legalNature) {
        this.legalNature = legalNature;
    }

    public Integer getMonthlyInvoicing() {
        return monthlyInvoicing;
    }

    public void setMonthlyInvoicing(Integer monthlyInvoicing) {
        this.monthlyInvoicing = monthlyInvoicing;
    }

    public CellPhone getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(CellPhone cellPhone) {
        this.cellPhone = cellPhone;
    }
}
