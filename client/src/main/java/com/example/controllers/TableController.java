package com.example.controllers;

import com.example.grapghics.Animations;
import com.example.grapghics.Translation;
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
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.example.collections.*;

import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

public class TableController implements Initializable {
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
    private TableColumn<Dragon, String> coordinates;

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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit.setVisible(false);
        ProxyController.setController(TableController.class, this);
        currentList = DragonTable.getDragons();

        System.out.println(colorChoice.getValue());
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
        StaticData.getData().getConnection().close();
        DragonTable.getDragons().clear();
        Connection.stop();
        ProxyController.changeScene(exitButton, "registration.fxml");
    }

    @FXML
    protected void getItem() {
        int index = dragonsTable.getSelectionModel().getSelectedIndex();
        if (index < 0) {
            return;
        }
        Dragon dragon = currentList.get(index);
        idBuffer = dragon.getId();
        nameField.setText(dragon.getName());
        xField.setText(dragon.getCoordinates().split("; ")[0]);
        yField.setText(dragon.getCoordinates().split("; ")[1]);
        ageField.setText(String.valueOf(dragon.getAge()));
        caveField.setText(String.valueOf(dragon.getCave()));

        colorChoice.getSelectionModel().select(Color.getEnumColor(dragon.getColor()).ordinal());
        typeChoice.getSelectionModel().select(DragonType.getEnumType(dragon.getType()).ordinal());
        characterChoice.getSelectionModel().select(DragonCharacter.getEnumCharacter(dragon.getCharacter()).ordinal());

        dateBuffer = dragon.getCreation();

    }

    @FXML
    protected void enterAgain() {
        ProxyController proxyController = new ProxyController(TableController.class);
        String[] fields = {"nameField", "xField", "yField", "ageField", "caveField"};
        for (String field: fields) {
            TextField textField = proxyController.getField(field);
            textField.setStyle("");
            textField.setPromptText(ResourceBundle.getBundle("properties.Table", Translation.getLocale()).getString(field));
        }
    }

    @FXML
    protected void addClick() {
        try {
            Dragon dragon = new Validation().getDragon(0);
            dragon.setCreation(new Timestamp(new Date().getTime()));
            new Connection(StaticData.getData().getConnection().getSocket()).sendToServer("add", dragon);
        } catch (NullPointerException ignored) {} //Неверный ввод некоторых данных. Игнорирую
    }

    @FXML
    protected void updateClick()  {
        try {
            Dragon dragon = new Validation().getDragon(idBuffer);
            dragon.setCreation(dateBuffer);
            new Connection(StaticData.getData().getConnection().getSocket()).sendToServer("update", dragon);
        } catch (NullPointerException ignored) {} //Неверный ввод некоторых данных. Игнорирую
    }

    @FXML
    protected void removeClick() {
        try {
            Dragon dragon = new Validation().getDragon(idBuffer);
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

        currentList = DragonTable.getDragons();
        dragonsTable.setItems(currentList);
    }
}
