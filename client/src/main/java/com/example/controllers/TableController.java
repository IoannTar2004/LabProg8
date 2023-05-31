package com.example.controllers;

import com.example.collections.Color;
import com.example.collections.Dragon;
import com.example.collections.DragonCharacter;
import com.example.collections.DragonType;
import com.example.grapghics.AnimatedDragon;
import com.example.grapghics.Animations;
import com.example.grapghics.NodeManager;
import com.example.misc.FlowText;
import com.example.modules.Connection;
import com.example.modules.DragonTable;
import com.example.modules.StaticData;
import com.example.modules.Validation;
import com.example.run.ClientMain;
import com.example.run.ProxyController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

public class TableController implements Initializable, CloseAction {
    private long idBuffer;
    private Timestamp dateBuffer;
    private ObservableList<Dragon> currentList;

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
    private TableColumn<Dragon, Timestamp> creation;

    @FXML
    private TableColumn<Dragon, Long> id;

    @FXML
    private TableColumn<Dragon, String> name;

    @FXML
    private TableColumn<Dragon, String> type;

    @FXML
    private TableColumn<Dragon, String> login;

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
    private Button clear;

    @FXML
    private Label loginText;

    @FlowText
    @FXML
    private Label reconnect;

    @FXML
    private Button visualize;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit.setVisible(false);
        ProxyController.setController(TableController.class, this);
        currentList = DragonTable.getDragons();
        loginText.setText(StaticData.getData().getLogin());

        new NodeManager().boxInitialize(TableController.class, languages, colorChoice, characterChoice, typeChoice);
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
        StaticData.getData().getConnection().close();
        DragonTable.getDragons().clear();
        Connection.stop();
        ProxyController.changeScene((Stage) exitButton.getScene().getWindow(), "registration.fxml");
    }

    @FXML
    protected void getItem(MouseEvent event) {
        int index = dragonsTable.getSelectionModel().getSelectedIndex();
        if (index < 0) {
            return;
        }
        Dragon dragon = currentList.get(index);
        if (!dragon.getLogin().equals(StaticData.getData().getLogin())) {
            return;
        }
        idBuffer = dragon.getId();
        nameField.setText(dragon.getName());
        ageField.setText(String.valueOf(dragon.getAge()));
        caveField.setText(String.valueOf(dragon.getCave()));

        colorChoice.getSelectionModel().select(Color.getEnumColor(dragon.getColor()).ordinal());
        typeChoice.getSelectionModel().select(DragonType.getEnumType(dragon.getType()).ordinal());
        characterChoice.getSelectionModel().select(DragonCharacter.getEnumCharacter(dragon.getCharacter()).ordinal());

        dateBuffer = dragon.getCreation();
     }

    @FXML
    protected void enterAgain() {
        new NodeManager().enterAgain(TableController.class);
    }

    @FXML
    protected void addClick() {
        try {
            Dragon dragon = new Validation().getDragon(0, TableController.class);
            dragon.setCreation(new Timestamp(new Date().getTime()));
            new Connection(StaticData.getData().getConnection().getSocket()).sendToServer("add", dragon);
        } catch (NullPointerException ignored) {} //Неверный ввод некоторых данных. Игнорирую
    }

    @FXML
    protected void updateClick()  {
        try {
            Dragon dragon = new Validation().getDragon(idBuffer, TableController.class);
            dragon.setCreation(dateBuffer);
            new Connection(StaticData.getData().getConnection().getSocket()).sendToServer("update", dragon);
        } catch (NullPointerException ignored) {} //Неверный ввод некоторых данных. Игнорирую
    }

    @FXML
    protected void removeClick() {
        try {
            Dragon dragon = new Validation().getDragon(idBuffer, TableController.class);
            dragon.setCreation(dateBuffer);
            new Connection(StaticData.getData().getConnection().getSocket()).sendToServer("remove", dragon);
        } catch (NullPointerException ignored) {} //Неверный ввод некоторых данных. Игнорирую
    }

    @FXML
    protected void filterClick() {
        List<Dragon> filtered = new LinkedList<>(DragonTable.getDragons());
        if (idBuffer > 0) {
            filtered = filtered.stream().filter(d -> d.getId() == idBuffer).toList();
        }
        if (nameField.getText().length() > 0) {
            filtered = filtered.stream().filter(d -> d.getName().equals(nameField.getText())).toList();
        }
        if (ageField.getText().length() > 0) {
            filtered = filtered.stream().filter(d -> d.getAge() == Integer.parseInt(ageField.getText())).toList();
        }
        if (caveField.getText().length() > 0) {
            filtered = filtered.stream().filter(d -> d.getCave() == Double.parseDouble(caveField.getText())).toList();
        }
        if (colorChoice.getValue() != null) {
            filtered = filtered.stream().filter(d -> d.getColor().equals(
                    Color.values()[colorChoice.getSelectionModel().getSelectedIndex()].getColor())).toList();
        }
        if (typeChoice.getValue() != null) {
            filtered = filtered.stream().filter(d -> d.getType().equals(
                    DragonType.values()[typeChoice.getSelectionModel().getSelectedIndex()].getType())).toList();
        }
        if (characterChoice.getValue() != null) {
            filtered = filtered.stream().filter(d -> d.getCharacter().equals(
                    DragonCharacter.values()[characterChoice.getSelectionModel().getSelectedIndex()].getCharacter())).toList();
        }
        currentList = FXCollections.observableArrayList(filtered);
        dragonsTable.setItems(currentList);
    }

    @FXML
    protected void clearClick() {
        TextField[] fields = {nameField, ageField, caveField};
        for (TextField field: fields) {
            field.setText("");
        }
        colorChoice.setValue(null);
        typeChoice.setValue(null);
        characterChoice.setValue(null);
        idBuffer = 0;
        currentList = DragonTable.getDragons();
        dragonsTable.setItems(currentList);
    }

    @FXML
    protected void visualize() {
        ProxyController.changeScene(new Stage(), "visualization.fxml");
    }

    private final EventHandler<WindowEvent> closeEvent = event -> {
        StaticData.getData().getConnection().close();
        Connection.stop();
    };

    @Override
    public EventHandler<WindowEvent> close() {
        return closeEvent;
    }
}
