package com.example.misc;

import com.example.run.Controller;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Translation {
    private Controller controller;

    public Translation(Controller controller) {
        this.controller = controller;
    }

    public void changeLanguage(ActionEvent event) {
        String[] bundles = {"Registration"};
        Class<Controller> controllerClass = Controller.class;
        Field[] fields = controllerClass.getDeclaredFields();
        Locale locale = getLocale();

        for (String bundle: bundles) {
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    String data = ResourceBundle.getBundle("properties." + bundle, locale).getString(field.getName());
                    if (field.getType() == TextField.class || field.getType() == PasswordField.class) {
                        TextInputControl textInputControl = (TextInputControl) field.get(controller);
                        textInputControl.setPromptText(data);
                    } else if (field.getType() == Button.class) {
                        Labeled button = (Labeled) field.get(controller);
                        button.setText(data);
                    }
                } catch (MissingResourceException | IllegalAccessException ignored) {}
            }
        }
    }

    private Locale getLocale() {
        try {
            Field field = Controller.class.getDeclaredField("languages");
            field.setAccessible(true);
            String language = ((ChoiceBox<String>) field.get(controller)).getValue();
            return Languages.getLocale(language);
        } catch (NoSuchFieldException | IllegalAccessException e) {e.printStackTrace();}
        return null;
    }
}
