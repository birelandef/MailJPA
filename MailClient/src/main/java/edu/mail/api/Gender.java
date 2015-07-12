package edu.mail.api;

/**
 * The gender of the user using the application
 *
 * @author Sofia Ruban
 */
public enum Gender {

    MAN("man"), WOMAN("woman");

    private String value;

    private Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
