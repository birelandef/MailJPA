package com.company.core.entity;

import javax.persistence.*;

/**
 * The entity of the account of the mailbox
 *
 * @author Sofia Ruban
 */
@javax.persistence.Entity
public class Account extends Entity {

    /**
     * Email address of the user's mailbox
     */
    private String email;

    /**
     * The password from the user's mailbox
     */
    private String password;

    /**
     * Outgoing mail server
     */
    private String outgoingMailServer;

    /**
     * Incoming mail server
     */
    private String incomingMailServer;

    /**
     * id Person
     */
    @ManyToOne
    @JoinColumn(name="idPerson")
    private Person idPerson;

    public Account() {}

    public Account(String email, String password, String outgoingMailServer, String incomingMailServer, Person idPerson) {
        super();
        this.email = email;
        this.password = password;
        this.outgoingMailServer = outgoingMailServer;
        this.incomingMailServer = incomingMailServer;
        this.idPerson = idPerson;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOutgoingMailServer() {
        return outgoingMailServer;
    }

    public void setOutgoingMailServer(String outgoingMailServer) {
        this.outgoingMailServer = outgoingMailServer;
    }

    public String getIncomingMailServer() {
        return incomingMailServer;
    }

    public void setIncomingMailServer(String incomingMailServer) {
        this.incomingMailServer = incomingMailServer;
    }

    public Person  getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Person idPerson) {
        this.idPerson = idPerson;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + getId() + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", outgoingMailServer='" + outgoingMailServer + '\'' +
                ", incomingMailServer='" + incomingMailServer + '\'' +
                ", idPerson='" + idPerson + '\'' +
                '}';
    }
}
