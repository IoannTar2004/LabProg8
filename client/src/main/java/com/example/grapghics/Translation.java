package com.example.grapghics;

import com.example.controllers.RegistrationController;
import com.example.modules.Languages;
import com.example.run.ProxyController;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Class for translation texts to different languages using text from .properties files
 */
public class Translation {

    private ProxyController controller;

    public Translation(Class<?> clas) {
        controller = new ProxyController(clas);
    }

    /**
     * Translate all texts in application
     * @param event
     */
    public void changeLanguage(ActionEvent event) {
        String[] bundles = {"Registration"};
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

    public Locale getLocale() {
        try {
            Field field = RegistrationController.class.getDeclaredField("languages");
            field.setAccessible(true);
            String language = ((ChoiceBox<String>) field.get(controller.getControllerClass())).getValue();
            return Languages.getLocale(language);
        } catch (NoSuchFieldException | IllegalAccessException e) {e.printStackTrace();}
        return null;
    }
}