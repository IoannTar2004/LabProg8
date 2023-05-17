package org.example.tools;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class to output texts from .properties files.
 */
public class OutputText {

    private static ResourceBundle registration;

    public static String registration(String key, Locale locale) {
        return ResourceBundle.getBundle("key", locale).getString(key);
    }
}
