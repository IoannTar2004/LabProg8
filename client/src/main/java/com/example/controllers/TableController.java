package com.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    ObservableList<Dragon> list = FXCollections.observableArrayList();

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.add(new Dragon(23, "ivan", "Vova", new Coordinates(34,34L), 45, Color.BLUE, DragonType.WATER,
                DragonCharacter.CUNNING, new DragonCave(45.4)));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        coordinates.setCellValueFactory(new PropertyValueFactory<>("coordinates"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        character.setCellValueFactory(new PropertyValueFactory<>("character"));
        cave.setCellValueFactory(new PropertyValueFactory<>("cave"));
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

        dragonsTable.setItems(list);

    }
}
