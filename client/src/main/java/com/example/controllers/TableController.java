package com.example.controllers;

import com.example.grapghics.Animations;
import com.example.grapghics.NodeManager;
import com.example.misc.FlowText;
import com.example.modules.Connection;
import com.example.modules.DragonTable;
import com.example.modules.StaticData;
import com.example.modules.Validation;
import com.example.run.ProxyController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.example.collections.*;

import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

public class TableController implements Initializable {
    private long idBuffer;
    private Timestamp dateBuffer;
    private ObservableList<ProxyDragon> currentList;

    @FXML
    private TableView<ProxyDragon> dragonsTable;

    @FXML
    private TableColumn<ProxyDragon, Integer> age;

    @FXML
    private TableColumn<ProxyDragon, Double> cave;

    @FXML
    private TableColumn<ProxyDragon, String> character;

    @FXML
    private TableColumn<ProxyDragon, String> color;

    @FXML
    private TableColumn<ProxyDragon, Timestamp> creation;

    @FXML
    private TableColumn<ProxyDragon, Long> id;

    @FXML
    private TableColumn<ProxyDragon, String> name;

    @FXML
    private TableColumn<ProxyDragon, String> type;

    @FXML
    private TableColumn<ProxyDragon, String> coordinates;

    @FXML
    private TableColumn<ProxyDragon, String> login;

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
    private Button clear;

    @FXML
    private Label loginText;

    @FlowText
    @FXML
    private Label reconnect;

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
        ProxyController.changeScene(exitButton, "registration.fxml");
    }

    @FXML
    protected void getItem(MouseEvent event) {
        int index = dragonsTable.getSelectionModel().getSelectedIndex();
        if (index < 0) {
            return;
        }
        ProxyDragon proxyDragon = currentList.get(index);
        idBuffer = proxyDragon.getId();
        nameField.setText(proxyDragon.getName());
        xField.setText(proxyDragon.getCoordinates().split("; ")[0]);
        yField.setText(proxyDragon.getCoordinates().split("; ")[1]);
        ageField.setText(String.valueOf(proxyDragon.getAge()));
        caveField.setText(String.valueOf(proxyDragon.getCave()));

        colorChoice.getSelectionModel().select(Color.getEnumColor(proxyDragon.getColor()).ordinal());
        typeChoice.getSelectionModel().select(DragonType.getEnumType(proxyDragon.getType()).ordinal());
        characterChoice.getSelectionModel().select(DragonCharacter.getEnumCharacter(proxyDragon.getCharacter()).ordinal());

        dateBuffer = proxyDragon.getCreation();

        if (event.getClickCount() > 1) {
            ProxyController.changeScene(dragonsTable, "visualization.fxml");
        }
     }

    @FXML
    protected void enterAgain() {
        new NodeManager().enterAgain(TableController.class);
    }

    @FXML
    protected void addClick() {
        try {
            ProxyDragon proxyDragon = new Validation().getDragon(0, TableController.class);
            proxyDragon.setCreation(new Timestamp(new Date().getTime()));
            new Connection(StaticData.getData().getConnection().getSocket()).sendToServer("add", proxyDragon);
        } catch (NullPointerException ignored) {} //Неверный ввод некоторых данных. Игнорирую
    }

    @FXML
    protected void updateClick()  {
        try {
            ProxyDragon proxyDragon = new Validation().getDragon(idBuffer, TableController.class);
            proxyDragon.setCreation(dateBuffer);
            new Connection(StaticData.getData().getConnection().getSocket()).sendToServer("update", proxyDragon);
        } catch (NullPointerException ignored) {} //Неверный ввод некоторых данных. Игнорирую
    }

    @FXML
    protected void removeClick() {
        try {
            ProxyDragon proxyDragon = new Validation().getDragon(idBuffer, TableController.class);
            proxyDragon.setCreation(dateBuffer);
            new Connection(StaticData.getData().getConnection().getSocket()).sendToServer("remove", proxyDragon);
        } catch (NullPointerException ignored) {} //Неверный ввод некоторых данных. Игнорирую
    }

    @FXML
    protected void filterClick() {
        List<ProxyDragon> filtered = new LinkedList<>(DragonTable.getDragons());
        if (idBuffer > 0) {
            filtered = filtered.stream().filter(d -> d.getId() == idBuffer).toList();
        }
        if (nameField.getText().length() > 0) {
            filtered = filtered.stream().filter(d -> d.getName().equals(nameField.getText())).toList();
        }
        if (xField.getText().length() > 0) {
            filtered = filtered.stream().filter(d -> d.getCoordinates().split("; ")[0]
                    .equals(xField.getText())).toList();
        }
        if (yField.getText().length() > 0) {
            filtered = filtered.stream().filter(d -> d.getCoordinates().split("; ")[1]
                    .equals(yField.getText())).toList();
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
        TextField[] fields = {nameField, xField, yField, ageField, caveField};
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
}
