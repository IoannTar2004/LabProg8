package com.example.run;

import com.example.proxy.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

/**
 * Controls all nodes in application
 */
public class Controller {
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

        languages.setItems(Languages.getLangArray());
        languages.setValue("Русский");
        new Translation().changeLanguage(null);
        languages.setOnAction(new Translation()::changeLanguage);
    }

    @FXML
    protected void registerClick(MouseEvent event) {
        new Validation().registerEmpty(Languages.getLocale(languages.getValue()));
        new Validation().registerLong(Languages.getLocale(languages.getValue()));
    }
}