package br.com.infotera.santander.model.RQRS;

public class CancelarRS {

    private Integer response;

    public CancelarRS() {
    }

    public CancelarRS(Integer response) {
        this.response = response;
    }

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }
}
