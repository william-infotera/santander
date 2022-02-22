package br.com.infotera.santander.model;

public class Payment {

    private Double downPayment;

    private String firstPaymentDate;

    private Integer installmentAmount;

    private Double installmentValue;

    private String modality;

    private String coupon;

    private Integer paymentFormId;

    private String agency;

    private String account;

    private Double totalValue;

    public Payment() {
    }

    public Payment(Double downPayment, String firstPaymentDate, Integer installmentAmount, Double installmentValue, String modality, String coupon, Integer paymentFormId, String agency, String account, Double totalValue) {
        this.downPayment = downPayment;
        this.firstPaymentDate = firstPaymentDate;
        this.installmentAmount = installmentAmount;
        this.installmentValue = installmentValue;
        this.modality = modality;
        this.coupon = coupon;
        this.paymentFormId = paymentFormId;
        this.agency = agency;
        this.account = account;
        this.totalValue = totalValue;
    }

    public Double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(Double downPayment) {
        this.downPayment = downPayment;
    }

    public String getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(String firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    public Integer getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(Integer installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public Double getInstallmentValue() {
        return installmentValue;
    }

    public void setInstallmentValue(Double installmentValue) {
        this.installmentValue = installmentValue;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public Integer getPaymentFormId() {
        return paymentFormId;
    }

    public void setPaymentFormId(Integer paymentFormId) {
        this.paymentFormId = paymentFormId;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }
}
