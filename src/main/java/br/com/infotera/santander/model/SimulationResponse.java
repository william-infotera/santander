package br.com.infotera.santander.model;

import java.util.UUID;

public class SimulationResponse {

    private UUID uuid;

    private Double installmentValue;

    private Double initialInstallmentValue;

    private Double lastInstallmentValue;

    private Double releaseValue;

    private Double totalFinancedValue;

    private Double tcValue;

    private Double tabValue;

    private Integer installmentAmount;

    private Integer paymentFormid;

    public SimulationResponse() {
    }

    public SimulationResponse(UUID uuid, Double installmentValue, Double initialInstallmentValue, Double lastInstallmentValue, Double releaseValue, Double totalFinancedValue, Double tcValue, Double tabValue, Integer installmentAmount, Integer paymentFormid) {
        this.uuid = uuid;
        this.installmentValue = installmentValue;
        this.initialInstallmentValue = initialInstallmentValue;
        this.lastInstallmentValue = lastInstallmentValue;
        this.releaseValue = releaseValue;
        this.totalFinancedValue = totalFinancedValue;
        this.tcValue = tcValue;
        this.tabValue = tabValue;
        this.installmentAmount = installmentAmount;
        this.paymentFormid = paymentFormid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Double getInstallmentValue() {
        return installmentValue;
    }

    public void setInstallmentValue(Double installmentValue) {
        this.installmentValue = installmentValue;
    }

    public Double getInitialInstallmentValue() {
        return initialInstallmentValue;
    }

    public void setInitialInstallmentValue(Double initialInstallmentValue) {
        this.initialInstallmentValue = initialInstallmentValue;
    }

    public Double getLastInstallmentValue() {
        return lastInstallmentValue;
    }

    public void setLastInstallmentValue(Double lastInstallmentValue) {
        this.lastInstallmentValue = lastInstallmentValue;
    }

    public Double getReleaseValue() {
        return releaseValue;
    }

    public void setReleaseValue(Double releaseValue) {
        this.releaseValue = releaseValue;
    }

    public Double getTotalFinancedValue() {
        return totalFinancedValue;
    }

    public void setTotalFinancedValue(Double totalFinancedValue) {
        this.totalFinancedValue = totalFinancedValue;
    }

    public Double getTcValue() {
        return tcValue;
    }

    public void setTcValue(Double tcValue) {
        this.tcValue = tcValue;
    }

    public Double getTabValue() {
        return tabValue;
    }

    public void setTabValue(Double tabValue) {
        this.tabValue = tabValue;
    }

    public Integer getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(Integer installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public Integer getPaymentFormid() {
        return paymentFormid;
    }

    public void setPaymentFormid(Integer paymentFormid) {
        this.paymentFormid = paymentFormid;
    }
}
