package com.example.controllers;

import com.example.grapghics.Animations;
import com.example.grapghics.Translation;
import com.example.modules.StaticData;
import com.example.modules.Validation;
import com.example.run.ProxyController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.example.collections.*;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;
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
        exit.setVisible(false);
        ProxyController.setController(TableController.class, this);

        languages.setItems(Translation.getAllLanguages());
        languages.setValue(Translation.getLanguage());
        new Translation(TableController.class).changeLanguage(null);

        colorChoice.getSelectionModel().selectFirst();
        typeChoice.getSelectionModel().selectFirst();
        characterChoice.getSelectionModel().selectFirst();

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
    }

    @FXML
    protected void addClick() {
        Object[] elements = new Object[5];
        Validation validation = new Validation();
        elements[0] = validation.string(nameField, "");
        elements[1] = validation.integer(xField, "");
        elements[2] = validation.along(yField, "");
        elements[3] = validation.integer(ageField, "");
        elements[4] = validation.cave(caveField, "");

        try {
            Arrays.stream(elements).filter(Objects::isNull).findFirst();
        } catch (NullPointerException e) {
            return;
        }

        Dragon dragon = new Dragon(0L, StaticData.getData().getLogin(), (String) elements[0],
                new Coordinates((int) elements[1], (long) elements[2]), (int) elements[3],
                Color.values()[colorChoice.getSelectionModel().getSelectedIndex()],
                DragonType.values()[typeChoice.getSelectionModel().getSelectedIndex()],
                DragonCharacter.values()[typeChoice.getSelectionModel().getSelectedIndex()],
                (DragonCave) elements[4]);

        //Long id = StaticData.getData().getConnection().<Dragon, Long>exchange("add", null, )
    }
}
