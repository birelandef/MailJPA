package com.company.core.entity;

import java.util.Arrays;

/**
 * The entity of the attached file
 *
 * @author Sofia Ruban
 */
public class Attachment extends Entity {

    /**
     * The name of the attached file
     */
    private String name;
    /**
     * The contents of the file
     */
    private byte[] files;

    /**
     * idLetter
     */
    private String idLetter;

    /**
     * idFolder
     */
    private String idFolder;

    /**
     * idAccount
     */
    private String idAccount;

    /**
     * idPerson
     */
    private String idPerson;

    public Attachment(String name, byte[] files, String idLetter, String idFolder, String idAccount, String idPerson) {
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

    public String getIdLetter() {
        return idLetter;
    }

    public void setIdLetter(String idLetter) {
        this.idLetter = idLetter;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", files=" + Arrays.toString(files) +
                ", idLetter='" + idLetter + '\'' +
                ", idFolder='" + idFolder + '\'' +
                ", idAccount='" + idAccount + '\'' +
                ", idPerson='" + idPerson + '\'' +
                '}';
    }
}
