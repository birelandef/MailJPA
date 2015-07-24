package edu.mail.beans;

/**
 * Local interface for log in mailClient by special login and password(for this application).
 * @author Sophie
 * @date 07.07.2015.
 */
public interface AuthorizationLocal {
    boolean logIn(String login, String password)  throws RuntimeException;
    void logOut();
}
