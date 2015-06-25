package com.company.core.entity;

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
    private String idAccount;
    /**
     * The name of the folder
     */
    private String name;
    /**
     * The identifier of the parent folder
     */
    private String idParentFolder;
    /**
     * The identifier of the user who owns the folder
     */
    private String idPerson;
    /**
     * System folder or not
     */
    private boolean isSystemFolder;
    /**
     * Description of folder
     */
    private String description;

    public void setIsSystemFolder(boolean isSystemFolder) {
        this.isSystemFolder = isSystemFolder;
    }

    public Folder(String idAccount, String name, String idParentFolder, String idPerson, boolean isSystemFolder,
                  String description) {
        super();
        this.idAccount = idAccount;
        this.name = name;
        this.idParentFolder = idParentFolder;
        this.idPerson = idPerson;
        this.isSystemFolder = isSystemFolder;
        this.description = description;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdParentFolder() {
        return idParentFolder;
    }

    public void setIdParentFolder(String idParentFolder) {
        this.idParentFolder = idParentFolder;
    }

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public boolean isSystemFolder() {
        return isSystemFolder;
    }

    public void setSystemFolder(boolean isSystemFolder) {
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
