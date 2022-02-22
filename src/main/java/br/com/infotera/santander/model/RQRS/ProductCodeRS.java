package br.com.infotera.santander.model.RQRS;

public class ProductCodeRS {

    private Integer id;

    private String description;

    public ProductCodeRS() {
    }

    public ProductCodeRS(Integer id, String description) {
        this.id = id;
        this.description = description;
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
