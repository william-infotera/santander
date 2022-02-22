package br.com.infotera.santander.model.RQRS;

import br.com.infotera.santander.model.Customer;
import br.com.infotera.santander.model.Purchase;
import br.com.infotera.santander.model.Store;

public class PreAnaliseRQ {

    private Customer customer;

    private Purchase purchase;

    private Store store;

    private String uuid;

    public PreAnaliseRQ() {
    }

    public PreAnaliseRQ(Customer customer, Purchase purchase, Store store, String uuid) {
        this.customer = customer;
        this.purchase = purchase;
        this.store = store;
        this.uuid = uuid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
