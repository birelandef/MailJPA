package com.company.core.utils;

import java.util.UUID;

/**
 * Abstract class used to generate unique identifiers for entities
 *
 * @author Sofia Ruban
 */
public final class IdGenerator {

    public static String generatorId() {
        return UUID.randomUUID().toString();
    }
}
