package com.example.run;

import com.example.modules.Languages;
import com.example.modules.Registration;
import com.example.modules.Translation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Controls all nodes in application
 */
public class Controller {
    @FXML
    private AnchorPane anchor;

    @FXML
    private Button enter;

    @FXML
    private TextField host;

    @FXML
    private Label hostEmpty;

    @FXML
    private Label hostLabel;

    @FXML
    private ChoiceBox<String> languages;

    @FXML
    private TextField login;

    @FXML
    private Label loginLabel;

    @FXML
    private PasswordField password;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField port;

    @FXML
    private Label portLabel;

    @FXML
    private Button register;


    @FXML
    public void initialize() {
        ProxyController.setController(this);
        Circle circle = new Circle(3, Color.BLUE);
        circle.setRadius(100);

        languages.setItems(Languages.getLangArray());
        languages.setValue("Русский");
        new Translation().changeLanguage(null);
        languages.setOnAction(new Translation()::changeLanguage);
    }

    @FXML
    protected void registerClick(MouseEvent event) {
        new Registration().register(Languages.getLocale(languages.getValue()));
    }
}