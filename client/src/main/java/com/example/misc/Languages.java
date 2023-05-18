package com.example.misc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.Locale;

public enum Languages {
    RUSSIAN("Русский"), ENGLISH("English"), CZECH("Český"), LITHUANIAN("Lietuviškas");

    private String language;

    Languages(String language) {
        this.language = language;
    }

    public static ObservableList<String> getLangArray() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for(Languages lang: Languages.values()) {
            list.add(lang.language);
        }
        return list;
    }

    public static Locale getLocale(String language) {
        switch (language) {
            case "Русский": return new Locale("ru", "RU");
            case "English": return new Locale("en", "IE");
            case "Český": return new Locale("cs", "CZ");
            case "Lietuviškas": return new Locale("lt", "LT");
        }
        return null;
    }
}
