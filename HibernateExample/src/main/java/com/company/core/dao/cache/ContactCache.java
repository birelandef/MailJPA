package com.company.core.dao.cache;

import com.company.core.entity.Contact;

/**
 * TODO javadoc
 *
 * @author Sofia Ruban
 */
public class ContactCache extends AbstractCache<Contact> {

    @Override
    protected Class<Contact> getClassEntity() {
        return Contact.class;
    }
}
