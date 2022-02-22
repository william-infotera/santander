package br.com.infotera.santander.model;

public class DocumentGet {

    private Integer id;

    private String description;

    public DocumentGet(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public DocumentGet() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
