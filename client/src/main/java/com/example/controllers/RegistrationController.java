package com.example.controllers;

import com.example.modules.Connection;
import com.example.modules.Registration;
import com.example.grapghics.Translation;
import com.example.run.ProxyController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls all nodes in application
 */
public class RegistrationController implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private AnchorPane connectionAnchor;

    @FXML
    private Label connectionText;

    @FXML
    private Button enter;

    @FXML
    private TextField host;

    @FXML
    private Label hostInput;

    @FXML
    private Label hostLabel;

    @FXML
    private Label hostText;

    @FXML
    private ChoiceBox<String> languages;

    @FXML
    private TextField login;

    @FXML
    private Label loginLabel;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private PasswordField password;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField port;

    @FXML
    private Label portInput;

    @FXML
    private Label portLabel;

    @FXML
    private Label portText;

    @FXML
    private Button register;

    @FXML
    public void initialize(URL url, ResourceBundle bundle) {
        connectionAnchor.setVisible(false);
        ProxyController.setController(RegistrationController.class, this);

        languages.setItems(Translation.getAllLanguages());
        languages.setValue(Translation.getLanguage());
        new Translation(RegistrationController.class).changeLanguage(null);
        languages.setOnAction(new Translation(RegistrationController.class)::changeLanguage);
    }

    @FXML
    protected void registerClick(MouseEvent event) {
        Registration registration = new Registration("newUser", Translation.getLocale());
        registration.register();

    }

    @FXML
    protected void cancelConnection(MouseEvent event) {
        new Registration().cancel();
        Connection.stop();
    }

    @FXML
    protected void fieldClick(MouseEvent event) {
        hostLabel.setText("");
        portLabel.setText("");
        loginLabel.setText("");
        passwordLabel.setText("");
    }

    @FXML
    protected void enterClick(MouseEvent event) {
    Registration registration = new Registration("existedUser", Translation.getLocale());
    registration.register();
    }
}