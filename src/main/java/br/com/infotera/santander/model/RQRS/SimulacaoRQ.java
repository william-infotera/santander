package br.com.infotera.santander.model.RQRS;

import br.com.infotera.santander.model.Customer;
import br.com.infotera.santander.model.Fee;
import br.com.infotera.santander.model.Payment;
import br.com.infotera.santander.model.Store;

import java.util.UUID;

public class SimulacaoRQ {

    private String tableNumber;

    private UUID uuid;

    private Customer customer;

    private Payment payment;

    private Store store;

    private Fee fees;

    public SimulacaoRQ() {
    }

    public SimulacaoRQ(String tableNumber, UUID uuid, Customer customer, Payment payment, Store store, Fee fees) {
        this.tableNumber = tableNumber;
        this.uuid = uuid;
        this.customer = customer;
        this.payment = payment;
        this.store = store;
        this.fees = fees;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Fee getFees() {
        return fees;
    }

    public void setFees(Fee fees) {
        this.fees = fees;
    }
}
