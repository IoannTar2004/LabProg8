package com.example.modules;

import com.example.grapghics.Animations;
import com.example.grapghics.TextSetter;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.lang.reflect.Field;
import java.util.*;

import static com.example.run.ProxyController.getField;

public class Registration {
    private final String bundle = "properties.Registration";
    private String host;
    private String port;
    private Locale locale;

    public void register(Locale locale) {
        Validation validation = new Validation();
        this.locale = locale;
        if (validation.registerEmpty(locale) & validation.registerLong(locale)) {
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

        Rectangle rectangle = new Rectangle(0, 0, Color.WHITE);
        rectangle.setStroke(Color.WHITE);
        rectangle.setLayoutX(175);
        rectangle.setLayoutY(193);
        anchorPane.getChildren().add(13, rectangle);
        animations.scaleTransitionIn(Duration.millis(250), rectangle, 0,0,302,217);


        connectionAnchor.setVisible(true);
        animations.scaleTransitionIn(Duration.millis(250), connectionAnchor, 0,0,1,1);
    }
}
