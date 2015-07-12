package edu.mail.core.utils;

import java.util.UUID;

/**
 * Class used to generate unique identifiers for entities of the Mail Client
 *
 * @author Sofia Ruban
 */
public final class IdGenerator {

    public static String generatorId() {
        return UUID.randomUUID().toString();
    }
}
