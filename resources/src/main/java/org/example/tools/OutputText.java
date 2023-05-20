package org.example.tools;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class to output texts from .properties files.
 */
public class OutputText {

    public static String[] getDataBaseResources() {
        String dataBase = "Database";
        return new String[] {ResourceBundle.getBundle(dataBase).getString("url"),
                            ResourceBundle.getBundle(dataBase).getString("login"),
                            ResourceBundle.getBundle(dataBase).getString("password")};
    }
}
