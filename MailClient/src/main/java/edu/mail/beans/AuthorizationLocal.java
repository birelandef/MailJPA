package edu.mail.beans;

/**
 * @author Sophie
 * @date 07.07.2015.
 */
public interface AuthorizationLocal {
    boolean checkExistencePersonAndPassword(String login, String password)  throws RuntimeException;
    void logOut();
}
