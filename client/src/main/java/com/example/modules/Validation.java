package com.example.modules;

import com.example.run.ProxyController;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Locale;
import java.util.ResourceBundle;

public class Validation extends ProxyController {

    public boolean registerEmpty(Locale locale) {
        boolean result = true;
        String bundle = "properties.Registration";
        TextField host = getField("host");
        if (host.getText().length() == 0) {
            Label label = getField("hostLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("hostEmpty"));
            result = false;
        }

        TextField port = getField("port");
        if (port.getText().length() == 0) {
            Label label = getField("portLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("portEmpty"));
            result = false;
        }

        TextField login = getField("login");
        if (login.getText().length() == 0) {
            Label label = getField("loginLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("loginEmpty"));
            result = false;
        }

        TextField password = getField("password");
        if (password.getText().length() == 0) {
            Label label = getField("passwordLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("passwordEmpty"));
            result = false;
        }

        return result;
    }

    public boolean registerLong(Locale locale) {
        boolean result = true;
        String bundle = "properties.Registration";

        TextField port = getField("port");
        try {
            if (port.getText().length() != 0) {
                Integer.parseInt(port.getText());
            }
        } catch (NumberFormatException e) {
            Label label = getField("portLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("portWrongFormat"));
            result = false;
        }

        TextField login = getField("login");
        if (login.getText().length() > 32) {
            Label label = getField("loginLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("loginLong"));
            result = false;
        }

        TextField password = getField("password");
        if (password.getText().length() > 32) {
            Label label = getField("passwordLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("passwordLong"));
            result = false;
        }

        return result;
    }
}
