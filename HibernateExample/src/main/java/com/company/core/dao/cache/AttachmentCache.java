package com.company.core.dao.cache;

import com.company.core.entity.Attachment;

/**
 * TODO javadoc
 *
 * @author Sofia Ruban
 */
public class AttachmentCache extends AbstractCache<Attachment> {

    @Override
    protected Class<Attachment> getClassEntity() {
        return Attachment.class;
    }
}
