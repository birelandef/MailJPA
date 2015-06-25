package com.company.api;

/**
 * The gender of the user
 *
 * @author Sofia Ruban
 */
public enum Gender {

    MAN("man"), WOMAN("woman");

    private String value;

    private Gender(String value) {
        this.value = value;
    }
}
