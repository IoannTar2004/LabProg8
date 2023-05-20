package org.example.tools;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class to output texts from .properties files.
 */
public class OutputText {

    private static ResourceBundle registration;

    public static String print(String bundle, Locale locale, String key) {
        return ResourceBundle.getBundle(bundle, locale).getString(key);
    }
}
