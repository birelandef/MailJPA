package edu.mail.core.entities;

import javax.persistence.*;

/**
 * The entity contains information about a single account mailbox client
 *
 * @author Sofia Ruban
 */
@javax.persistence.Entity
public class Account extends Entity {

    /**
     * E-mail address of the user's mailbox
     */
    private String email;

    /**
     * The password from the user's mailbox
     */
    private String password;

    /**
     * Address outgoing mail server
     */
    private String outgoingMailServer;

    /**
     * Address incoming mail server
     */
    private String incomingMailServer;

    /**
     * todo переделать на id пользователя
     * The identifier of the user who owns the account in the mailbox
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
        StringBuilder builder = new StringBuilder("Account{");
        builder.append("email='").append(email);
        builder.append("', password='").append(password);
        builder.append("', outgoingMailServer='").append(outgoingMailServer);
        builder.append("', incomingMailServer='").append(incomingMailServer);
        builder.append("', idPerson=").append(idPerson.getId());
        builder.append("}");
        return builder.toString();
    }
}
