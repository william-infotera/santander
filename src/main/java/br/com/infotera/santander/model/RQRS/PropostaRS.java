package br.com.infotera.santander.model.RQRS;

public class PropostaRS {

    private String creationDate;

    private String proposalExternalId;

    private Integer proposalId;

    private String status;

    private String statusAdp;

    private String statusDescription;

    public PropostaRS() {
    }

    public PropostaRS(String creationDate, String proposalExternalId, Integer proposalId, String status, String statusAdp, String statusDescription) {
        this.creationDate = creationDate;
        this.proposalExternalId = proposalExternalId;
        this.proposalId = proposalId;
        this.status = status;
        this.statusAdp = statusAdp;
        this.statusDescription = statusDescription;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getProposalExternalId() {
        return proposalExternalId;
    }

    public void setProposalExternalId(String proposalExternalId) {
        this.proposalExternalId = proposalExternalId;
    }

    public Integer getProposalId() {
        return proposalId;
    }

    public void setProposalId(Integer proposalId) {
        this.proposalId = proposalId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusAdp() {
        return statusAdp;
    }

    public void setStatusAdp(String statusAdp) {
        this.statusAdp = statusAdp;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}
