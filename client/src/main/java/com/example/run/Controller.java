package com.example.run;

import com.example.proxy.ProxyController;
import com.example.proxy.Translation;
import com.example.proxy.Languages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controls all nodes in application
 */
public class Controller {
    @FXML
    private Button enter;

    @FXML
    private TextField host;

    @FXML
    private ChoiceBox<String> languages;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField port;

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
}