package com.example.controllers;

import com.example.grapghics.Translation;
import com.example.modules.Connection;
import com.example.modules.DragonTable;
import com.example.run.ProxyController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
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
    ChoiceBox<String> languages;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProxyController.setController(TableController.class, this);

        languages.setItems(Translation.getAllLanguages());
        languages.setValue(Translation.getLanguage());
        new Translation(TableController.class).changeLanguage(null);
        languages.setOnAction(new Translation(TableController.class)::changeLanguage);
    }

}
