package com.example.grapghics;

import com.example.run.ProxyController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Class for translation texts to different languages using text from .properties files
 */
public class Translation {
    private static final Map<String, Locale> languages = new LinkedHashMap<>();
    static {
        languages.put("Русский", new Locale("ru", "RU"));
        languages.put("English", new Locale("en", "IE"));
        languages.put("Český", new Locale("cs", "CZ"));
        languages.put("Lietuviškas", new Locale("lt", "LT"));
    }

    private ProxyController controller;
    private static String language;

    public Translation(Class<?> clas) {
        controller = new ProxyController(clas);
    }

    public static void setLanguage(String language) {
        Translation.language = language;
    }

    public static String getLanguage() {
        return language;
    }

    /**
     * Translate all texts in application
     * @param event
     */
    public void changeLanguage(ActionEvent event) {
        String[] bundles = {"Registration", "Table"};
        setLanguage(((ChoiceBox<String>)controller.getField("languages")).getValue());
        Field[] fields = controller.getAllFields();
        Locale locale = getLocale();

        for (String bundle: bundles) {
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    String data = ResourceBundle.getBundle("properties." + bundle, locale).getString(field.getName());
                    if (field.getType() == TextField.class || field.getType() == PasswordField.class) {
                        TextInputControl textInputControl = (TextInputControl) field.get(controller.getControllerClass());
                        textInputControl.setPromptText(data);
                    } else if (field.getType() == Button.class) {
                        Labeled button = (Labeled) field.get(controller.getControllerClass());
                        button.setText(data);
                    }
                } catch (MissingResourceException | IllegalAccessException ignored) {}
            }
        }
    }

    public static Locale getLocale() {
        return languages.get(language);
    }

    public static ObservableList<String> getAllLanguages() {
        return FXCollections.observableArrayList(languages.keySet().stream().toList());
    }
}