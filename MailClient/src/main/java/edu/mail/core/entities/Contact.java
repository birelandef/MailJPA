package edu.mail.core.entities;

import edu.mail.core.entities.Entity;

/**
 * The entity describing one recipient of the letter, which the current user sent a letter
 *
 * @author Sofia Ruban
 */
@javax.persistence.Entity
public class Contact extends Entity {

    /**
     * The mailbox address of the user who sent the letter
     */
    private String email;

    /**
     * The name of the user who sent the letter
     */
    private String name;

    /**
     * The surname of the user who sent the letter
     */
    private String surname;

    public Contact() {}

    public Contact(String email, String name, String surname) {
        super();
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Contact{");
        builder.append("email='").append(email);
        builder.append("', name='").append(name);
        builder.append("', surname='").append(surname);
        builder.append("'}");
        return builder.toString();
    }
}
