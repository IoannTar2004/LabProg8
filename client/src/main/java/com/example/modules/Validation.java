package com.example.modules;

import com.example.controllers.RegistrationController;
import com.example.run.ProxyController;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Locale;
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
}
