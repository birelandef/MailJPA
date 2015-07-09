package com.company.core.beans;

import com.company.core.entity.Account;
import com.company.core.entity.Person;

import java.io.FileNotFoundException;
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
