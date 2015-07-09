package com.company.core.beans;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Sophie
 * @date 07.07.2015.
 */
public class AuthorizationTest {

    private Authorization authorization = new Authorization();

    @Test
    public void testCheckExistencePersonAndPassword() throws Exception {
        assertTrue("Check existence Person", authorization.checkExistencePersonAndPassword("duma@mail.ru", "password"));

        assertFalse("Check existence Person", authorization.checkExistencePersonAndPassword("duma1@mail.ru", "password"));

        assertFalse("Check existence Person", authorization.checkExistencePersonAndPassword("duma@mail.ru", "Password"));

    }
}