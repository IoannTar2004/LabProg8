package com.example.modules;

import com.example.controllers.RegistrationController;
import com.example.controllers.TableController;
import com.example.grapghics.NodeManager;
import com.example.grapghics.Translation;
import com.example.run.ProxyController;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.collections.*;

import java.time.temporal.TemporalAccessor;
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

    public DragonCave cave(TextField field) {
        try {
            double depth = Double.parseDouble(field.getText());
            return new DragonCave(depth);
        } catch (NumberFormatException e) {
            field.setText("");
            field.setPromptText(ResourceBundle.getBundle("properties.Table", Translation.getLocale()).getString("numberError"));
            new NodeManager().textFieldError(field);
        }
        return null;
    }

    public Dragon getDragon(long id) {
        Object[] elements = new Object[5];
        ProxyController controller = new ProxyController(TableController.class);
        Validation validation = new Validation();
        elements[0] = validation.string(controller.getField("nameField"));
        elements[1] = validation.integer(controller.getField("xField"));
        elements[2] = validation.along(controller.getField("yField"));
        elements[3] = validation.integer(controller.getField("ageField"));
        elements[4] = validation.cave(controller.getField("caveField"));

        try {
            Arrays.stream(elements).filter(Objects::isNull).findFirst();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }

        ChoiceBox<String> colorChoice = controller.getField("colorChoice");
        ChoiceBox<String> typeChoice = controller.getField("typeChoice");
        ChoiceBox<String> characterChoice = controller.getField("characterChoice");

        Dragon dragon = new Dragon(id, StaticData.getData().getLogin(), (String) elements[0],
                new Coordinates((int) elements[1], (long) elements[2]).toString(), (int) elements[3],
                Color.values()[colorChoice.getSelectionModel().getSelectedIndex()].getColor(),
                DragonType.values()[typeChoice.getSelectionModel().getSelectedIndex()].getType(),
                DragonCharacter.values()[characterChoice.getSelectionModel().getSelectedIndex()].getCharacter(),
                ((DragonCave) elements[4]).getDepth());
        return dragon;
    }
}
