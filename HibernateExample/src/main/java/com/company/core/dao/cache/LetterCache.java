package com.company.core.dao.cache;

import com.company.core.entity.Letter;

/**
 * TODO javadoc
 *
 * @author Sofia Ruban
 */
public class LetterCache extends AbstractCache<Letter> {

    @Override
    protected Class<Letter> getClassEntity() {
        return Letter.class;
    }
}
