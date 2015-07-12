package edu.mail.core.entities;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * The entity contains information about single letter user mail client
 *
 * @author Sofia Ruban
 */
@javax.persistence.Entity
public class Letter extends Entity {

    /**
     * The identifier user that owns the letter
     * TODO поменять на идентификатор
     */
    @ManyToOne
    @JoinColumn(name = "idPerson")
    private Person idPerson;

    /**
     * The identifier of the folder containing the letter
     */
    @ManyToOne
    @JoinColumn(name = "idFolder")
    private Folder idFolder;

    /**
     * The idAccount identifier
     * TODO удалить. По папке содержащей письмо можно вычислить аккаунт
     */
    @ManyToOne
    @JoinColumn(name = "idAccount")
    private Account idAccount;

    /**
     * Mark view letter
     */
    private boolean isSeen;

    /**
     * Email address of the mailbox from which was sent or sent a letter
     */
    @Column(name = "FROMWHOM")
    private String fromWhom;

    /**
     * The list of electronic addresses of recipients letter
     */
    @Transient
    private List<String> toWhom = new ArrayList<String>();

    /**
     * The list of electronic addresses of recipients of copies of the letter
     */
    @Transient
    private List<String> copy = new ArrayList<String>();

    /**
     * Subject letter
     */
    private String subject;

    /**
     * The body letter
     */
    private String message;

    /**
     * The list of files attached to the letter
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDLETTER")
    private List<Attachment> attachments = new ArrayList<>();

    /**
     * Date and time of sending or receiving letters
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATETO")
    private Date date;

    /**
     * TODO удалить to Whom должна мапиться как строка в таблицу
     * Additional field for mapping List<String> toWhom
     */
    @Column(name = "TOWHOM")
    private String toWhomM;

    /**
     * TODO удалить copy должна мапиться как строка в таблицу
     * Additional field for mapping List<String> copy
     */
    @Column(name = "COPY")
    private String copyM;

    public Letter() {}

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

    // TODO удалить
    public String getCopyM() {
        return copyM;
    }

    // TODO удалить
    public void setCopyM(String copyM) {
        this.copyM = copyM;
    }

    // TODO удалить
    public String getToWhomM() {
        return toWhomM;
    }

    // TODO удалить
    public void setToWhomM(String toWhomM) {
        this.toWhomM = toWhomM;
    }

    // TODO поправить на обычный гет
    public String getToWhom() {
        StringBuffer listToString = new StringBuffer();
        for (String content : this.toWhom) {
            listToString.append(content);
            listToString.append(", ");
        }
        return listToString.toString();
    }

    // TODO поправить на обычный сет
    private void setToWhom(String listAsString) {
        List<String> list = new ArrayList<>();
        if (listAsString != null) {
            String[] items = listAsString.split(",");
            list = Arrays.asList(items);
        }
        this.toWhom = list;
    }

    // TODO поправить на обычный гет
    public String getCopy() {
        StringBuffer listToString = new StringBuffer();
        for (String content : this.copy) {
            listToString.append(content);
            listToString.append(", ");
        }
        return listToString.toString();
    }

    // TODO поправить на обычный сет
    private void setCopy(String listAsString) {
        List<String> list = new ArrayList<>();
        if (listAsString != null) {
            String[] items = listAsString.split(",");
            list = Arrays.asList(items);
        }
        this.toWhom = list;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Letter{");
        builder.append("idPerson=").append(idPerson.getId());
        builder.append(", idFolder=").append(idFolder.getId());
        builder.append(", idAccount=").append(idAccount.getId());
        builder.append(", isSeen=").append(isSeen);
        builder.append(", fromWhom='").append(fromWhom);
        builder.append("', toWhom=").append(toWhom.toString());
        builder.append(", copy=").append(copy.toString());
        builder.append(", subject='").append(subject);
        builder.append("', message='").append(message);
        builder.append("', attachments=").append(attachments.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy kk:mm:ss");
        builder.append(", date='").append(sdf.format(date));
        builder.append("'}");
        return builder.toString();
    }
}
