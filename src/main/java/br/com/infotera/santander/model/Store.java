package br.com.infotera.santander.model;

public class Store {

    private Integer tabId;

    private String name;

    public Store() {
    }

    public Store(Integer tabId,String name) {
        this.tabId = tabId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTabId() {
        return tabId;
    }

    public void setTabId(Integer tabId) {
        this.tabId = tabId;
    }
}
