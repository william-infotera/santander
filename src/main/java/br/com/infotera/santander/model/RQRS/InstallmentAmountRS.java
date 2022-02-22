package br.com.infotera.santander.model.RQRS;

public class InstallmentAmountRS {

    private String description;

    public InstallmentAmountRS() {
    }

    public InstallmentAmountRS(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
