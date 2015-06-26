package com.company.core.entity;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The entity of the folder. The container containing the user's messages.
 *
 * @author Sofia Ruban
 */
@javax.persistence.Entity
public class Folder extends Entity {

    /**
     * The account identifier of the mailbox which contains this folder
     */
    @ManyToOne
    @JoinColumn(name="idAccount")
    private Account idAccount;
    /**
     * The name of the folder
     */
    private String name;
    /**
     * The identifier of the parent folder
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idParentFolder")
    private Folder idParentFolder;
    /**
     * The identifier of the user who owns the folder
     */
    @ManyToOne
    @JoinColumn(name="idPerson")
    private Person idPerson;
    /**
     * System folder or not
     */
    private boolean isSystemFolder;
    /**
     * Description of folder
     */
    private String description;

    public Folder() {    }

    public Folder(Account idAccount, String name, Folder idParentFolder, Person idPerson, boolean isSystemFolder,
                  String description) {
        super();
        this.idAccount = idAccount;
        this.name = name;
        this.idParentFolder = idParentFolder;
        this.idPerson = idPerson;
        this.isSystemFolder = isSystemFolder;
        this.description = description;
    }

    public Account getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Account idAccount) {
        this.idAccount = idAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Folder getIdParentFolder() {
        return idParentFolder;
    }

    public void setIdParentFolder(Folder idParentFolder) {
        this.idParentFolder = idParentFolder;
    }

    public Person getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Person idPerson) {
        this.idPerson = idPerson;
    }

    public boolean isSystemFolder() {
        return isSystemFolder;
    }

    public void setIsSystemFolder(boolean isSystemFolder) {
        this.isSystemFolder = isSystemFolder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id='" + getId() + '\'' +
                ", idAccount='" + idAccount + '\'' +
                ", name='" + name + '\'' +
                ", idParentFolder='" + idParentFolder + '\'' +
                ", idPerson='" + idPerson + '\'' +
                ", isSystemFolder=" + isSystemFolder +
                ", description='" + description + '\'' +
                '}';
    }
}
