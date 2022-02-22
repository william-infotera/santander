package br.com.infotera.santander.model.RQRS;

import br.com.infotera.santander.model.Customer;

import java.util.UUID;

public class PropostaRQ {

    private Customer customer;

    private String ip;

    private UUID uuid;

    public PropostaRQ() {
    }

    public PropostaRQ(Customer customer, String ip, UUID uuid) {
        this.customer = customer;
        this.ip = ip;
        this.uuid = uuid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
