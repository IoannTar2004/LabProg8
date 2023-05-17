package com.example.run;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ResourceBundle;

public class Controller {
    @FXML
    private TextField hostField;

    @FXML
    private TextField portField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passField;


    @FXML
    public void initialize() {
        hostField.setPromptText(ResourceBundle.getBundle("Registration").getString("host"));
    }
}