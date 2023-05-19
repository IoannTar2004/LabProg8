package com.example.proxy;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Locale;
import java.util.ResourceBundle;

public class Validation extends ProxyController {
    public void registerEmpty(Locale locale) {
        String bundle = "properties.Registration";
        TextField host = getField("host");
        if (host.getText().length() == 0) {
            Label label = getField("hostLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("hostEmpty"));
        }

        TextField port = getField("port");
        if (port.getText().length() == 0) {
            Label label = getField("portLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("portEmpty"));
        }

        TextField login = getField("login");
        if (login.getText().length() == 0) {
            Label label = getField("loginLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("loginEmpty"));
        }

        TextField password = getField("password");
        if (password.getText().length() == 0) {
            Label label = getField("passwordLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("passwordEmpty"));
        }
    }

    public void registerLong(Locale locale) {
        String bundle = "properties.Registration";

        TextField port = getField("port");
        try {
            if (port.getText().length() != 0) {
                Integer.parseInt(port.getText());
            }
        } catch (NumberFormatException e) {
            Label label = getField("portLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("portWrongFormat"));
        }

        TextField login = getField("login");
        if (login.getText().length() > 32) {
            Label label = getField("loginLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("loginLong"));
        }

        TextField password = getField("password");
        if (password.getText().length() > 32) {
            Label label = getField("passwordLabel");
            label.setText(ResourceBundle.getBundle(bundle, locale).getString("passwordLong"));
        }
    }
}
