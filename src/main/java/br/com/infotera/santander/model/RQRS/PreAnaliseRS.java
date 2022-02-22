package br.com.infotera.santander.model.RQRS;

import br.com.infotera.santander.model.AtxResponse;

import java.util.UUID;

public class PreAnaliseRS {

    private AtxResponse atxResponse;

    private UUID uuid;

    private Integer id;

    public PreAnaliseRS() {
    }

    public PreAnaliseRS(AtxResponse atxResponse, UUID uuid, Integer id) {
        this.atxResponse = atxResponse;
        this.uuid = uuid;
        this.id = id;
    }

    public AtxResponse getAtxResponse() {
        return atxResponse;
    }

    public void setAtxResponse(AtxResponse atxResponse) {
        this.atxResponse = atxResponse;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
