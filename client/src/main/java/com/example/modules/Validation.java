package com.example.modules;

import com.example.controllers.RegistrationController;
import com.example.grapghics.NodeManager;
import com.example.grapghics.Translation;
import com.example.run.ProxyController;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.collections.*;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class Validation {

    public boolean registerEmpty(Locale locale) {
        ProxyController controller = new ProxyController(RegistrationController.class);

        boolean result = true;
        String bundle = "properties.Registration";
        TextField host = controller.getField("host");
        if (host.getText().length() == 0) {
            Label label = controller.getField("hostLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("hostEmpty"));
            result = false;
        }

        TextField port = controller.getField("port");
        if (port.getText().length() == 0) {
            Label label = controller.getField("portLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("portEmpty"));
            result = false;
        }

        TextField login = controller.getField("login");
        if (login.getText().length() == 0) {
            Label label = controller.getField("loginLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("loginEmpty"));
            result = false;
        }

        TextField password = controller.getField("password");
        if (password.getText().length() == 0) {
            Label label = controller.getField("passwordLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("passwordEmpty"));
            result = false;
        }

        return result;
    }

    public boolean registerLong(Locale locale) {
        ProxyController controller = new ProxyController(RegistrationController.class);
        boolean result = true;
        String bundle = "properties.Registration";

        TextField port = controller.getField("port");
        try {
            if (port.getText().length() != 0) {
                Integer.parseInt(port.getText());
            }
        } catch (NumberFormatException e) {
            Label label = controller.getField("portLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("portWrongFormat"));
            result = false;
        }

        TextField login = controller.getField("login");
        if (login.getText().length() > 32) {
            Label label = controller.getField("loginLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("loginLong"));
            result = false;
        }

        TextField password = controller.getField("password");
        if (password.getText().length() > 32) {
            Label label = controller.getField("passwordLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("passwordLong"));
            result = false;
        }

        return result;
    }

    public String string(TextField field) {
        String name = field.getText();
        if (name.length() > 0) {
            return name;
        }
        field.setText("");
        field.setPromptText(ResourceBundle.getBundle("properties.Table", Translation.getLocale()).getString("nameEmpty"));
        new NodeManager().textFieldError(field);
        return null;
    }

    public Integer integer(TextField field) {
        try {
            return Integer.parseInt(field.getText());
        } catch (NumberFormatException e) {
            field.setText("");
            field.setPromptText(ResourceBundle.getBundle("properties.Table", Translation.getLocale()).getString("numberError"));
            new NodeManager().textFieldError(field);
        }
        return null;
    }

    public Long along(TextField field) {
        try {
            return Long.parseLong(field.getText());
        } catch (NumberFormatException e) {
            field.setText("");
            field.setPromptText(ResourceBundle.getBundle("properties.Table", Translation.getLocale()).getString("numberError"));
            new NodeManager().textFieldError(field);
        }
        return null;
    }

    public Double cave(TextField field) {
        try {
            return Double.parseDouble(field.getText());
        } catch (NumberFormatException e) {
            field.setText("");
            field.setPromptText(ResourceBundle.getBundle("properties.Table", Translation.getLocale()).getString("numberError"));
            new NodeManager().textFieldError(field);
        }
        return null;
    }

    public Integer choiceBox(ChoiceBox<String> choiceBox) {
        if (choiceBox.getValue() != null) {
           return choiceBox.getSelectionModel().getSelectedIndex();
        }
        return null;
    }

    public Object[] validate(Class<?> controllerClass) {
        Object[] elements = new Object[6];
        ProxyController controller = new ProxyController(controllerClass);
        elements[0] = string(controller.getField("nameField"));
        elements[1] = integer(controller.getField("ageField"));
        elements[2] = choiceBox(controller.getField("colorChoice"));
        elements[3] = choiceBox(controller.getField("typeChoice"));
        elements[4] = choiceBox(controller.getField("characterChoice"));
        elements[5] = cave(controller.getField("caveField"));

        return elements;
    }

    public ProxyDragon getDragon(long id, Class<?> controllerClass) {
        Object[] elements = validate(controllerClass);
        try {
            Arrays.stream(elements).filter(Objects::isNull).findFirst();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }

        return new ProxyDragon(id, StaticData.getData().getLogin(), (String) elements[0], (int) elements[1],
                Color.values()[(int) elements[2]].getColor(), DragonType.values()[(int) elements[3]].getType(),
                DragonCharacter.values()[(int) elements[4]].getCharacter(), (double) elements[5]);
    }
}
