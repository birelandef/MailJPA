package edu.mail.core.entities;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * An entity describes a single folder of a user account in the mailbox
 *
 * @author Sofia Ruban
 */
@javax.persistence.Entity
public class Folder extends Entity {

    /**
     * The identifier account of the mailbox user  which contains this folder
     * TODO поменять на идентификатор
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
     * TODO поменять на идентификатор
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idParentFolder")
    private Folder idParentFolder;

    /**
     * The identifier of the user account in application the Mail Client that owns the folder
     * TODO поменять на идентификатор
     */
    @ManyToOne
    @JoinColumn(name="idPerson")
    private Person idPerson;

    /**
     * Mark about system folder
     */
    private boolean isSystemFolder;

    /**
     * Description of folder
     */
    private String description;

    public Folder() {}

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
        StringBuilder builder = new StringBuilder("Folder{");
        builder.append("idAccount=").append(idAccount.getId());
        builder.append(", name='").append(name);
        builder.append("', idParentFolder=").append(idParentFolder.getId());
        builder.append(", idPerson=").append(idPerson.getId());
        builder.append(", isSystemFolder=").append(isSystemFolder);
        builder.append(", description='").append(description);
        builder.append("'}");
        return builder.toString();
    }
}
