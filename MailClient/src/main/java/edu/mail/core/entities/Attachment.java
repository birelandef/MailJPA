package edu.mail.core.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The entity contains information about single file attached to the letter
 *
 * @author Sofia Ruban
 */
@javax.persistence.Entity
public class Attachment extends Entity {

    /**
     * File name attached to the letter
     */
    private String name;

    /**
     * Binary representation of the file
     */
    @Column(name = "FILES")
    private byte[] files;

    /**
     * The message identifier which is attached to the file
     * TODO поменять на идентификатор
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idLetter")
    private Letter idLetter;

    /**
     * TODO удалить. Мы можем по письму определить папку
     * idFolder
     */
    @ManyToOne
    @JoinColumn(name="idFolder")
    private Folder idFolder;

    /**
     * TODO удалить. Мы можем по папке определить аккаунт
     * idAccount
     */
    @ManyToOne
    @JoinColumn(name="idAccount")
    private Account idAccount;

    /**
     * TODO удалить. Мы можем по письму определить пользователя
     * idPerson
     */
    @ManyToOne
    @JoinColumn(name="idPerson")
    private Person idPerson;

    public Attachment() {}

    public Attachment(String name, byte[] files, Letter idLetter, Folder idFolder, Account idAccount, Person idPerson) {
        super();
        this.name = name;
        this.files = files;
        this.idLetter = idLetter;
        this.idFolder = idFolder;
        this.idAccount = idAccount;
        this.idPerson = idPerson;
    }

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
        StringBuilder builder = new StringBuilder("Attachment{");
        builder.append("name='").append(name);
        builder.append("', idLetter=").append(idLetter.getId());
        builder.append(", idFolder=").append(idFolder.getId());
        builder.append(", idAccount=").append(idAccount.getId());
        builder.append(", idPerson=").append(idPerson.getId());
        builder.append("}");
        return builder.toString();
    }
}
