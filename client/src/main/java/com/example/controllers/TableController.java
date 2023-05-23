package com.example.controllers;

import com.example.grapghics.Animations;
import com.example.grapghics.Translation;
import com.example.modules.Connection;
import com.example.modules.DragonTable;
import com.example.run.ProxyController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;
import org.example.collections.*;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private TableView<Dragon> dragonsTable;

    @FXML
    private TableColumn<Dragon, Integer> age;

    @FXML
    private TableColumn<Dragon, Double> cave;

    @FXML
    private TableColumn<Dragon, String> character;

    @FXML
    private TableColumn<Dragon, String> color;

    @FXML
    private TableColumn<Dragon, Timestamp> creationDate;

    @FXML
    private TableColumn<Dragon, Long> id;

    @FXML
    private TableColumn<Dragon, String> name;

    @FXML
    private TableColumn<Dragon, String> type;

    @FXML
    private TableColumn<Dragon, String> coordinates;

    @FXML
    private TableColumn<Dragon, String> user;

    @FXML
    private Button add;

    @FXML
    private Button edit;

    @FXML
    private Button remove;

    @FXML
    private Button filter;

    @FXML
    private ChoiceBox<String> languages;

    @FXML
    private AnchorPane exit;

    @FXML
    private Label exitButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField xField;

    @FXML
    private TextField yField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField caveField;

    @FXML
    private ChoiceBox<String> colorChoice;

    @FXML
    private ChoiceBox<String> typeChoice;

    @FXML
    private ChoiceBox<String> characterChoice;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorChoice.getItems().addAll(Color.getAll());
        typeChoice.getItems().addAll(DragonType.getAll());
        characterChoice.getItems().addAll(DragonCharacter.getAll());

        exit.setVisible(false);
        ProxyController.setController(TableController.class, this);

        languages.setItems(Translation.getAllLanguages());
        languages.setValue(Translation.getLanguage());
        new Translation(TableController.class).changeLanguage(null);
        languages.setOnAction(new Translation(TableController.class)::changeLanguage);
    }

    @FXML
    protected void exitIn() {
        exit.setVisible(true);
        exitButton.setVisible(true);
        Animations animations = new Animations();
        animations.pathTransition(Duration.millis(250), exit,65,0,65,50);
    }

    @FXML
    protected void exitOut() {
        exitButton.setVisible(false);
        Animations animations = new Animations();
        animations.pathTransition(Duration.millis(250), exit,65,50,65,0);
    }

    @FXML
    protected void exitFromTable() {
        ProxyController.changeScene(exitButton, "registration.fxml");
    }

    @FXML
    protected void getItem() {
        int index = dragonsTable.getSelectionModel().getSelectedIndex();
        System.out.println(index);


    }
}
