package com.company.core.beans;

import com.company.core.entity.Account;

/**
 * @author Sophie
 * @date 07.07.2015.
 */
public interface AuthorizationLocal {
    boolean checkExistencePersonAndPassword(String login, String password)  throws RuntimeException;
    void logOut();
}
