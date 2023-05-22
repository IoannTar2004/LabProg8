package com.example.controllers;

import com.example.modules.Connection;
import com.example.modules.DragonTable;
import com.example.run.ProxyController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.collections.*;

import java.net.Socket;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProxyController.setController(TableController.class, this);
    }

    public TableView<Dragon> getDragonsTable() {
        return dragonsTable;
    }

    public TableColumn<Dragon, Integer> getAge() {
        return age;
    }

    public TableColumn<Dragon, Double> getCave() {
        return cave;
    }

    public TableColumn<Dragon, String> getCharacter() {
        return character;
    }

    public TableColumn<Dragon, String> getColor() {
        return color;
    }

    public TableColumn<Dragon, Timestamp> getCreationDate() {
        return creationDate;
    }

    public TableColumn<Dragon, Long> getId() {
        return id;
    }

    public TableColumn<Dragon, String> getName() {
        return name;
    }

    public TableColumn<Dragon, String> getType() {
        return type;
    }

    public TableColumn<Dragon, String> getCoordinates() {
        return coordinates;
    }

    public TableColumn<Dragon, String> getUser() {
        return user;
    }
}
