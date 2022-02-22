package br.com.infotera.santander.model.RQRS;

import java.util.UUID;

public class CodigoTabRS {

    private Integer tabId;

    private UUID uuid;

    public CodigoTabRS() {
    }

    public CodigoTabRS(Integer tabId, UUID uuid) {
        this.tabId = tabId;
        this.uuid = uuid;
    }

    public Integer getTabId() {
        return tabId;
    }

    public void setTabId(Integer tabId) {
        this.tabId = tabId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
