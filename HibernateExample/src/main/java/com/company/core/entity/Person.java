package com.company.core.entity;

import com.company.api.Gender;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The com.company.core.entity of the user.
 *
 * @author Sofia Ruban
 */

@javax.persistence.Entity
public class Person extends Entity {

    /**
     * The user login. Used to sign in to the app
     */
    private String login;
    /**
     * The user's password. The password used to log in to the app
     */
    private String password;
    /**
     * Username
     */
    private String name;
    /**
     * The user's last name
     */
    private String surname;
    /**
     * The gender of the user
     */
    private Gender gender;
    /**
     * The country in which the user lives
     */
    private String country;
    /**
     * The city where the user lives
     */
    private String city;
    /**
     * Additional information about the user
     */
    private String info;
    /**
     * Birthday user
     */
    @Temporal(value = TemporalType.DATE)
    private Date birthday;
    /**
     * The list of user mailboxes
     */

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @MapKey(name="id")
    @Transient
    private Map<String, Account> mailboxes = new HashMap<String, Account>();
    /**
     * The user's contact list
     */
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinTable(name = "PERSON_CONTACT",
            joinColumns = {@JoinColumn(name = "IDPERSON")},
            inverseJoinColumns = @JoinColumn(name = "IDCONTACT"))
    @JoinColumn(name = "IDPERSON")
    @MapKey(name="id")
    private Map<String, Contact> contacts = new HashMap<String, Contact>();

    public Person() {
        super();
    }

    public Person(String login, String password, String name, String surname, Gender gender, Date birthday,
                  String country, String city, String info, Map<String, Account> mailboxes, Map<String, Contact> contacts) {
        super();
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthday = birthday;
        this.country = country;
        this.city = city;
        this.info = info;
        this.mailboxes = mailboxes;
        this.contacts = contacts;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Map<String, Account> getMailboxes() {
        return mailboxes;
    }

    public void setMailboxes(Map<String, Account> mailboxes) {
        this.mailboxes.putAll(mailboxes);
    }

    public Map<String, Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Contact> contacts) {
        this.contacts.putAll(contacts);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + getId() + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", info='" + info + '\'' +
                ", mailboxes=" + mailboxes +
                ", contacts=" + contacts +
                '}';
    }
}
