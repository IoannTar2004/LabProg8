package com.example.modules;

import com.example.grapghics.Animations;
import com.example.grapghics.NodeManager;
import com.example.run.ProxyController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Registration extends ProxyController implements Runnable {
    private static final Rectangle rectangle = new Rectangle(0, 0, Color.WHITE);

    private final String bundle = "properties.Registration";
    private String host;
    private String port;
    private Locale locale;
    private String mode;

    public Registration(String mode, Locale locale) {
        this.locale = locale;
        this.mode = mode;
    }

    public Registration() {}

    public boolean register() {
        Validation validation = new Validation();

        if (validation.registerEmpty(locale) & validation.registerLong(locale)) {
            Node[] fields = getNodes("register", "enter", "languages");
            new NodeManager().forEach(fields, f -> f.setDisable(true));

            host = ((TextField) getField("host")).getText();
            port = ((TextField) getField("port")).getText();
            initialize();
            new Thread(this).start();

            return true;
        }
        return false;
    }

    @FXML
    public void initialize() {
        ((Label) getField("hostInput")).setText(host);
        ((Label) getField("portInput")).setText(port);

        AnchorPane anchorPane = getField("mainAnchor");
        AnchorPane connectionAnchor = getField("connectionAnchor");

        String[] fields = {"connectionText", "hostText", "portText", "cancelButton"};
        String[] keys = {"connectionText", "host_", "port_", "cancelButton"};
        new NodeManager().setText(bundle, locale, fields, keys);
        Animations animations = new Animations();

        rectangle.setStroke(Color.WHITE);
        rectangle.setLayoutX(175);
        rectangle.setLayoutY(193);
        try {
            anchorPane.getChildren().add(13, rectangle);
        } catch (IllegalArgumentException ignored) {} //если объект добавлен, то просто проигнорировать
        animations.scaleTransition(Duration.millis(250), rectangle, 0,0,302,217);

        connectionAnchor.setVisible(true);
        animations.scaleTransition(Duration.millis(250), connectionAnchor, 0,0,1,1);
    }

    public void cancel() {
        AnchorPane connectionAnchor = getField("connectionAnchor");
        Animations animations = new Animations();

        animations.scaleTransition(Duration.millis(250), connectionAnchor, 1,1,0,0);
        animations.scaleTransition(Duration.millis(250), rectangle, 302,217,0,0);

        Node[] fields = getNodes("register", "enter", "languages");
        new NodeManager().forEach(fields, f -> f.setDisable(false));
    }

    @Override
    public void run() {
        String login = ((TextField) getField("login")).getText();
        String password = hash(((TextField) getField("password")).getText());
        Connection connection = new Connection(host, Integer.parseInt(port));
        NodeManager nodeManager = new NodeManager();

        if (connection.run()) {
            String key = connection.<String, String>exchange(new String[]{"user_access"}, mode, login, password)[0];
            if (!key.equals("access")) {
                Platform.runLater(() -> nodeManager.setText(bundle, locale, new String[]{"loginLabel"}, new String[]{key}));
            } else {
                // переключение на другую сцену
            }
            cancel();
        }
    }

    private String hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-224");
            byte[] bytes = md.digest(password.getBytes());
            BigInteger integer = new BigInteger(1, bytes);
            return integer.toString(16);
        } catch (NoSuchAlgorithmException e) {e.printStackTrace();}
        return null;
    }
}
