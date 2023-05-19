package com.example.modules;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Locale;

import static com.example.run.ProxyController.getField;

public class Registration {

    private String host;
    private int port;

    public void register(Locale locale) {
        Validation validation = new Validation();

        if (validation.registerEmpty(locale) & validation.registerLong(locale)) {
            host = ((TextField) getField("host")).getText();
            port = Integer.parseInt(((TextField) getField("port")).getText());
            //new Thread(new Connection(host, port)).start();

            initialize();
        }
    }

    @FXML
    public void initialize() {
        AnchorPane pane = getField("anchor");
        Rectangle rectangle = new Rectangle(302, 217, Color.WHITE);
        rectangle.setStroke(Color.WHITE);
        rectangle.setLayoutY(84);
        rectangle.setLayoutX(25);

        ScaleTransition scaleTransitionIn = new ScaleTransition(Duration.millis(150), rectangle);

        pane.getChildren().add(rectangle);
    }
}
