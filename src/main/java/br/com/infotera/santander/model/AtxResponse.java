package br.com.infotera.santander.model;

import java.util.List;

public class AtxResponse {

    private String priceTable;

    private FirstInstallment firstInstallment;

    private Entry entry;

    private List<Term> terms;

    private List<PaymentForm> paymentForms;

    private Fee fees;

    private List<FlexPackage> flexPackages;

    private Boolean accountHolder;

    public AtxResponse() {
    }

    public AtxResponse(String priceTable, FirstInstallment firstInstallment, Entry entry, List<Term> terms, List<PaymentForm> paymentForms, Fee fees, List<FlexPackage> flexPackages, Boolean accountHolder) {
        this.priceTable = priceTable;
        this.firstInstallment = firstInstallment;
        this.entry = entry;
        this.terms = terms;
        this.paymentForms = paymentForms;
        this.fees = fees;
        this.flexPackages = flexPackages;
        this.accountHolder = accountHolder;
    }

    public String getPriceTable() {
        return priceTable;
    }

    public void setPriceTable(String priceTable) {
        this.priceTable = priceTable;
    }

    public FirstInstallment getFirstInstallment() {
        return firstInstallment;
    }

    public void setFirstInstallment(FirstInstallment firstInstallment) {
        this.firstInstallment = firstInstallment;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public List<PaymentForm> getPaymentForms() {
        return paymentForms;
    }

    public void setPaymentForms(List<PaymentForm> paymentForms) {
        this.paymentForms = paymentForms;
    }

    public Fee getFees() {
        return fees;
    }

    public void setFees(Fee fees) {
        this.fees = fees;
    }

    public List<FlexPackage> getFlexPackages() {
        return flexPackages;
    }

    public void setFlexPackages(List<FlexPackage> flexPackages) {
        this.flexPackages = flexPackages;
    }

    public Boolean getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(Boolean accountHolder) {
        this.accountHolder = accountHolder;
    }
}
