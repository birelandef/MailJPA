package com.company.core.dao.cache;

import com.company.core.entity.Account;

/**
 * TODO javadoc
 *
 * @author Sofia Ruban
 */
public class AccountCache extends AbstractCache<Account> {

    @Override
    protected Class<Account> getClassEntity() {
        return Account.class;
    }
}
