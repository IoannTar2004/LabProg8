package com.example.controllers;

import com.example.grapghics.Animations;
import com.example.grapghics.BoxInitialize;
import com.example.grapghics.Translation;
import com.example.modules.DragonTable;
import com.example.run.ProxyController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class VisualizationController implements Initializable {
    @FXML
    private Label age;

    @FXML
    private Button cancel;

    @FXML
    private Label caveField;

    @FXML
    private Label character;

    @FXML
    private ChoiceBox<String> characterChoice;

    @FXML
    private Label color;

    @FXML
    private ChoiceBox<String> colorChoice;

    @FXML
    private Label exitButton;

    @FXML
    private Label name;

    @FXML
    private TextField nameField;

    @FXML
    private TextField numberError;

    @FXML
    private Button save;

    @FXML
    private Label type;

    @FXML
    private ChoiceBox<String> typeChoice;

    @FXML
    private Label xField;

    @FXML
    private AnchorPane exit;

    @FXML
    private ChoiceBox<String> languages;

    @FXML
    void exitFromTable(MouseEvent event) {
        new TableController().exitFromTable();
    }

    @FXML
    void exitIn() {
        exit.setVisible(true);
        exitButton.setVisible(true);
        Animations animations = new Animations();
        animations.pathTransition(Duration.millis(250), exit,65,0,65,50);
    }

    @FXML
    void exitOut() {
        exitButton.setVisible(false);
        Animations animations = new Animations();
        animations.pathTransition(Duration.millis(250), exit,65,50,65,0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit.setVisible(false);
        ProxyController.setController(VisualizationController.class, this);

        new BoxInitialize().initialize(VisualizationController.class,languages, colorChoice, characterChoice, typeChoice);
    }
}
