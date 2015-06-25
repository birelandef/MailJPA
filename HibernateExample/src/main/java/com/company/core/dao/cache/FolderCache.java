package com.company.core.dao.cache;

import com.company.core.entity.Folder;

/**
 * TODO javadoc
 *
 * @author Sofia Ruban
 */
public class FolderCache extends AbstractCache<Folder> {

    @Override
    protected Class<Folder> getClassEntity() {
        return Folder.class;
    }
}
