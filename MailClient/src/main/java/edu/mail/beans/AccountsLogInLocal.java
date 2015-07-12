package edu.mail.beans;

import edu.mail.core.entities.Account;
import edu.mail.core.entities.Person;

import java.io.IOException;
import java.util.List;

/**
 * @author Sophie
 * @date 07.07.2015.
 */
public interface AccountsLogInLocal {
    void logIn(String idAccount) throws IOException;
    void logOut(String idAccount);
    List<Account> getAccountsList(Person person);
}
