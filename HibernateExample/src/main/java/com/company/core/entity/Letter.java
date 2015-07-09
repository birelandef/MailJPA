package com.company.core.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

/**
 * The entity of the letter
 *
 * @author Sofia Ruban
 */
@javax.persistence.Entity
public class Letter extends Entity {

    /**
     * The idPerson identifier
     */
    @ManyToOne
    @JoinColumn(name = "idPerson")
    private Person idPerson;
    /**
     * The identifier of the folder containing the message
     */
    @ManyToOne
    @JoinColumn(name = "idFolder")
    private Folder idFolder;
    /**
     * The idAccount identifier
     */
    @ManyToOne
    @JoinColumn(name = "idAccount")
    private  Account idAccount;
    /**
     * Flag view the letter
     */

    private boolean isSeen;
    /**
     * Email address of the mailbox from which the mail is sent
     */
    @Column(name = "FROMWHOM")
    private String fromWhom;
    /**
     * A list of electronic mailbox address of the recipients of the letter
     */
    @Transient
    private List<String> toWhom = new ArrayList<String>();
    /**
     * A list of email addresses mailboxes of recipients of copies of the letter
     */
    @Transient
    private List<String> copy = new ArrayList<String>();
    /**
     * Message subject
     */

    private String subject;
    /**
     * The body of the message
     */

    private String message;
    /**
     * The list of files attached to the message
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDLETTER")
    private List<Attachment> attachments = new ArrayList<>();
    /**
     * Date receive or send message
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATETO")
    private Date date;

    /**
    * Additional field for mapping List<String> toWhom
     */
    @Column(name = "TOWHOM")
    private String toWhomM;
    /**
     * Additional field for mapping List<String> copy
     */
    @Column(name = "COPY")
    private String copyM;

    public Letter() {   }

    public Letter(Person idPerson, Folder idFolder, Account idAccount, boolean isSeen, String fromWhom, List<String> toWhom,
                  List<String> copy, String subject, String message, List<Attachment> attachments, Date date) {
        super();
        this.idPerson = idPerson;
        this.idFolder = idFolder;
        this.idAccount = idAccount;
        this.isSeen = isSeen;
        this.fromWhom = fromWhom;
        this.toWhom = toWhom;
        this.toWhomM = getToWhom();
        this.copy = copy;
        this.copyM = getCopy();
        this.subject = subject;
        this.message = message;
        this.attachments = attachments;
        this.date = date;
    }

    public Person getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Person idPerson) {
        this.idPerson = idPerson;
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

    public boolean isSeen() {
        return isSeen;
    }

    public void setIsSeen(boolean isSeen) {
        this.isSeen = isSeen;
    }

    public String getFromWhom() {
        return fromWhom;
    }

    public void setFromWhom(String fromWhom) {
        this.fromWhom = fromWhom;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCopyM() {  return copyM;  }

    public void setCopyM(String copyM) { this.copyM = copyM; }

    public String getToWhomM() {
        return toWhomM;
    }

    public void setToWhomM(String toWhomM) {
        this.toWhomM = toWhomM;
    }

    public String getToWhom(){
        StringBuffer listToString = new StringBuffer();
        for (String content: this.toWhom){
            listToString.append(content);
            listToString.append(", ");
        }
        return listToString.toString();
    }

    private void setToWhom(String listAsString){
        List<String> list = new ArrayList<>();
        if (listAsString != null){
            String [] items = listAsString.split(",");
            list = Arrays.asList(items);
        }
        this.toWhom = list;
    }

    public String getCopy(){
        StringBuffer listToString = new StringBuffer();
        for (String content: this.copy){
            listToString.append(content);
            listToString.append(", ");
        }
        return listToString.toString();
    }

    private void setCopy(String listAsString){
        List<String> list = new ArrayList<>();
        if (listAsString != null){
            String [] items = listAsString.split(",");
            list = Arrays.asList(items);
        }
        this.toWhom = list;
    }


    @Override
    public String toString() {
        return "Letter{" +
                "idPerson=" + idPerson.toString() +
                ", idFolder=" + idFolder.toString() +
                ", idAccount=" + idAccount.toString() +
                ", isSeen=" + isSeen +
                ", fromWhom='" + fromWhom.toString() + '\'' +
                ", toWhom=" + toWhomM +
                ", copy=" + copyM +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", attachments=" + attachments.toString() +
                ", date=" + date.toString() +
                '}';
    }
}
