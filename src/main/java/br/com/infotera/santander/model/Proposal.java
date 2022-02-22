package br.com.infotera.santander.model;

public class Proposal {

    private String document;

    private Integer proposalId;

    public Proposal(String document, Integer proposalId) {
        this.document = document;
        this.proposalId = proposalId;
    }

    public Proposal() {
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Integer getProposalId() {
        return proposalId;
    }

    public void setProposalId(Integer proposalId) {
        this.proposalId = proposalId;
    }
}
