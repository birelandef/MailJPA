package com.company.core.entity;

/**
 * The entity of the contact.
 *
 * @author Sofia Ruban
 */
@javax.persistence.Entity
public class Contact extends Entity {

    /**
     * The electronic mailbox address of the contact
     */
    private String email;
    /**
     * Contact name
     */
    private String name;
    /**
     * Last name of the contact
     */
    private String surname;

    public Contact() {
        super();
    }

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
        return "Contact{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
