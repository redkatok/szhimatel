package com.app.requestanalyzer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String clientIdNumber;

    public Client() {
    }

    public Client(String clientIdNumber) {
        this.clientIdNumber = clientIdNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientIdNumber() {
        return clientIdNumber;
    }

    public void setClientIdNumber(String clientIdNumber) {
        this.clientIdNumber = clientIdNumber;
    }
}
