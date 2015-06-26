package com.company.core.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Arrays;

/**
 * The entity of the attached file
 *
 * @author Sofia Ruban
 */
@javax.persistence.Entity
public class Attachment extends Entity {

    /**
     * The name of the attached file
     */
    private String name;
    /**
     * The contents of the file
     */
    @Column(name = "FILES")
    private byte[] files;

    /**
     * idLetter
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idLetter")
    private Letter idLetter;

    /**
     * idFolder
     */
    @ManyToOne
    @JoinColumn(name="idFolder")
    private Folder idFolder;

    /**
     * idAccount
     */
    @ManyToOne
    @JoinColumn(name="idAccount")
    private Account idAccount;

    /**
     * idPerson
     */
    @ManyToOne
    @JoinColumn(name="idPerson")
    private Person idPerson;

    public Attachment(String name, byte[] files, Letter idLetter, Folder idFolder, Account idAccount, Person idPerson) {
        this.name = name;
        this.files = files;
        this.idLetter = idLetter;
        this.idFolder = idFolder;
        this.idAccount = idAccount;
        this.idPerson = idPerson;
    }

    public Attachment() {    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public byte[] getFiles() {
        return files;
    }

    public void setFiles(byte[] files) {
        this.files = files;
    }

    public Letter getIdLetter() {
        return idLetter;
    }

    public void setIdLetter(Letter idLetter) {
        this.idLetter = idLetter;
    }

    public Folder getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(Folder idFolder) {
        this.idFolder = idFolder;
    }

    public Account getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Account idAccount) {
        this.idAccount = idAccount;
    }

    public Person getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Person idPerson) {
        this.idPerson = idPerson;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "name='" + name + '\'' +
                ", files=" + Arrays.toString(files) +
                ", idLetter=" + idLetter.getId() +
                ", idFolder=" + idFolder.getId() +
                ", idAccount=" + idAccount.getId() +
                ", idPerson=" + idPerson.getId() +
                '}';
    }
}
