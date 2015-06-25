package com.company.core.dao.cache;

import com.company.core.entity.Person;

/**
 * TODO javadoc
 *
 * @author Sofia Ruban
 */
public class PersonCache extends AbstractCache<Person> {

    @Override
    protected Class<Person> getClassEntity() {
        return Person.class;
    }
}
