package com.example.modules;

import com.example.grapghics.Animations;
import com.example.grapghics.TextSetter;
import com.example.run.ProxyController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.lang.reflect.Field;
import java.util.*;

public class Registration extends ProxyController {
    private static final Rectangle rectangle = new Rectangle(0, 0, Color.WHITE);

    private final String bundle = "properties.Registration";
    private String host;
    private String port;
    private Locale locale;

    public void register(Locale locale) {
        Validation validation = new Validation();
        this.locale = locale;
        if (validation.registerEmpty(locale) & validation.registerLong(locale)) {
            Field[] fields = getFields("register", "enter", "languages");
            Arrays.stream(fields).forEach(f -> {
                try {
                    ((Node) f.get(controller)).setDisable(true);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });

            host = ((TextField) getField("host")).getText();
            port = ((TextField) getField("port")).getText();
            ((Label) getField("hostInput")).setText(host);
            ((Label) getField("portInput")).setText(port);
            //new Thread(new Connection(host, port)).start();

            initialize();
        }
    }

    @FXML
    public void initialize() {
        AnchorPane anchorPane = getField("mainAnchor");
        AnchorPane connectionAnchor = getField("connectionAnchor");

        String[] fields = {"connectionText", "hostText", "portText", "cancelButton"};
        String[] keys = {"connectionText", "host_", "port_", "cancelButton"};
        TextSetter.set(ResourceBundle.getBundle(bundle, locale), fields, keys);
        Animations animations = new Animations();

        rectangle.setStroke(Color.WHITE);
        rectangle.setLayoutX(175);
        rectangle.setLayoutY(193);
        anchorPane.getChildren().add(13, rectangle);
        animations.scaleTransition(Duration.millis(250), rectangle, 0,0,302,217);

        connectionAnchor.setVisible(true);
        animations.scaleTransition(Duration.millis(250), connectionAnchor, 0,0,1,1);
    }

    public void cancelConnection() {
        AnchorPane connectionAnchor = getField("connectionAnchor");
        AnchorPane anchorPane = getField("mainAnchor");
        Animations animations = new Animations();

        Connection.stop();
        animations.scaleTransition(Duration.millis(250), connectionAnchor, 1,1,0,0);
        animations.scaleTransition(Duration.millis(250), rectangle, 302,217,0,0);

        anchorPane.getChildren().remove(13);
    }
}
