package edu.mail.core.entities;

import edu.mail.api.Gender;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity contains information about single user Mail Client
 *
 * @author Sofia Ruban
 */
@javax.persistence.Entity
public class Person extends Entity {

    /**
     * The login user from account in application Mail Client
     */
    private String login;

    /**
     * The password user from  account in application Mail Client
     */
    private String password;

    /**
     * Real user name
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
     * The list of accounts from mailbox user
     */
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @MapKey(name="id")
//    @JoinColumn(name = "idPerson")
    @Transient
    private Map<String, Account> mailboxes = new HashMap<String, Account>();

    /**
     * The list of users who have already been sent letters
     */
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinTable(name = "PERSON_CONTACT",
            joinColumns = {@JoinColumn(name = "IDPERSON")},
            inverseJoinColumns = @JoinColumn(name = "IDCONTACT"))
    @JoinColumn(name = "IDPERSON")
    @MapKey(name="id")
    private Map<String, Contact> contacts = new HashMap<String, Contact>();

    public Person() {}

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
        this.mailboxes = mailboxes;
    }

    public Map<String, Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Person{");
        builder.append("login='").append(login);
        builder.append("', password='").append(password);
        builder.append("', name='").append(name);
        builder.append("', surname='").append(surname);
        builder.append("', gender='").append(gender.getValue());
        builder.append("', country='").append(country);
        builder.append("', city='").append(city);
        builder.append("', info='").append(info);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        builder.append("', birthday='").append(sdf.format(birthday));
        builder.append("', mailboxes=").append(mailboxes.toString());
        builder.append(", contacts=").append(contacts.toString());
        builder.append("}");
        return builder.toString();
    }
}
