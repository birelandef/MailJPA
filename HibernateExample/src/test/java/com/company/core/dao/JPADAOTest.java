package com.company.core.dao;

import com.company.core.entity.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Sophie
 * @date 27.06.2015.
 */
public class JPADAOTest {

    @Before
    public void setUp() throws Exception {
        JPADAO<Person> personJPADAO = new JPADAO<>("mailDB");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddEntity() throws Exception {

    }

    @Test
    public void testGetAllEntity() throws Exception {

    }

    @Test
    public void testFindEntityById() throws Exception {

    }

    @Test
    public void testUpdateEntity() throws Exception {

    }

    @Test
    public void testRemoveEntity() throws Exception {

    }
}