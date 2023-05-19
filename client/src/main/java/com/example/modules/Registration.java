package com.example.modules;

import com.example.proxy.ProxyController;
import com.example.proxy.Validation;
import javafx.scene.control.TextField;

import java.util.Locale;

import static com.example.proxy.ProxyController.getField;

public class Registration implements Runnable {

    public void register(Locale locale) {
        Validation validation = new Validation();

        if (validation.registerEmpty(locale) & validation.registerLong(locale)) {
            String host = ((TextField) getField("host")).getText();
            int port = Integer.parseInt(((TextField) getField("port")).getText());

            new Thread(this).start();
            System.out.println("ddd");
        }
    }

    @Override
    public void run() {
        System.out.println("fmkrmf");
    }
}
