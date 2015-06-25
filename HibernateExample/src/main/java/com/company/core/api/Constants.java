package com.company.core.api;

import java.io.File;

/**
 * Created by Sophie on 29.03.2015.
 */
public interface Constants {
    String SEPARATOR  = File.separator;
    String PATHTORESOURCES = System.getProperty("user.dir") + Constants.SEPARATOR +
            "resources" + Constants.SEPARATOR;
    String URL = "jdbc:oracle:thin:@localhost:1521/XE";
    String name = "SOPHIE";
    String password  = "SOPHIE";
    String [] priorityOfTable = {"LETTER", "FOLDER", "PERSON", "ACCOUNT", "ATTACHMENT", "CONTACT"};

}
