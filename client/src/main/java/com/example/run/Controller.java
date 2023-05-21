package com.example.run;

import com.example.modules.Connection;
import com.example.modules.Languages;
import com.example.modules.Registration;
import com.example.modules.Translation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Controls all nodes in application
 */
public class Controller {

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
    private Label hostEmpty;

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
    public void initialize() {
        connectionAnchor.setVisible(false);
        ProxyController.setController(this);

        languages.setItems(Languages.getLangArray());
        languages.setValue("Русский");
        new Translation().changeLanguage(null);
        languages.setOnAction(new Translation()::changeLanguage);
    }

    @FXML
    protected void registerClick(MouseEvent event) {
        Registration registration = new Registration("newUser", Languages.getLocale(languages.getValue()));
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
        Registration registration = new Registration("existedUser", Languages.getLocale(languages.getValue()));
        registration.register();
    }
}