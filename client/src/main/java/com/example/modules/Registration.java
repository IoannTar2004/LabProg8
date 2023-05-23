package com.example.modules;

import com.example.controllers.RegistrationController;
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

public class Registration implements Runnable {
    private static final Rectangle rectangle = new Rectangle(0, 0, Color.WHITE);

    private final ProxyController controller = new ProxyController(RegistrationController.class);
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
            Object[] fields = controller.getFields("register", "enter", "languages");
            Arrays.stream(fields).forEach(r -> ((Node) r).setDisable(true));

            host = ((TextField) controller.getField("host")).getText();
            port = ((TextField) controller.getField("port")).getText();
            initialize();
            new Thread(this).start();

            return true;
        }
        return false;
    }

    @FXML
    public void initialize() {
        ((Label) controller.getField("hostInput")).setText(host);
        ((Label) controller.getField("portInput")).setText(port);

        AnchorPane anchorPane = controller.getField("mainAnchor");
        AnchorPane connectionAnchor = controller.getField("connectionAnchor");

        String[] fields = {"connectionText", "hostText", "portText", "cancelButton"};
        String[] keys = {"connectionText", "host_", "port_", "cancelButton"};
        new NodeManager().setText(RegistrationController.class, bundle, locale, fields, keys);

        rectangle.setStroke(Color.WHITE);
        rectangle.setLayoutX(175);
        rectangle.setLayoutY(193);
        try {
            anchorPane.getChildren().add(13, rectangle);
        } catch (IllegalArgumentException ignored) {} //если объект добавлен, то просто проигнорировать

        Animations animations = new Animations();
        animations.scaleTransition(Duration.millis(250), rectangle, 0,0,302,217);
        connectionAnchor.setVisible(true);
        animations.scaleTransition(Duration.millis(250), connectionAnchor, 0,0,1,1);
    }

    public void cancel() {
        AnchorPane connectionAnchor = controller.getField("connectionAnchor");
        Animations animations = new Animations();

        animations.scaleTransition(Duration.millis(250), connectionAnchor, 1,1,0,0);
        animations.scaleTransition(Duration.millis(250), rectangle, 302,217,0,0);

        Object[] fields = controller.getFields("register", "enter", "languages");
        Arrays.stream(fields).forEach(r -> ((Node) r).setDisable(false));
    }

    @Override
    public void run() {
        String login = ((TextField) controller.getField("login")).getText();
        String password = hash(((TextField) controller.getField("password")).getText());
        Connection connection = new Connection(host, Integer.parseInt(port));

        if (connection.run()) {
            String key = connection.<String, String>exchange("user_access", mode, login, password);
            if (!key.equals("access")) {
                Platform.runLater(() -> new NodeManager().setText(RegistrationController.class,
                        bundle, locale, new String[]{"loginLabel"}, new String[]{key}));
                connection.close();
            } else {
                Platform.runLater(() -> {
                    ProxyController.changeScene(controller.getField("enter"), "table.fxml");
                    new DragonTable(connection.getSocket(), login).getAndFill();
                }
                );
                StaticData.getData().setLogin(login);
                StaticData.getData().setConnection(connection);
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
