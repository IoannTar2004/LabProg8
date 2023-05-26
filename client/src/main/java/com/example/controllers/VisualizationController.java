package com.example.controllers;

import com.example.grapghics.Animations;
import com.example.grapghics.ImageSelection;
import com.example.grapghics.NodeManager;
import com.example.modules.Connection;
import com.example.modules.DragonTable;
import com.example.modules.StaticData;
import com.example.modules.Validation;
import com.example.run.ProxyController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import org.example.collections.Dragon;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class VisualizationController implements Initializable {
    private long idBuffer;
    private Timestamp dateBuffer;

    @FXML
    private Label age;

    @FXML
    private Button cancel;

    @FXML
    private Label cave;

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
    private Button save;

    @FXML
    private Label type;

    @FXML
    private ChoiceBox<String> typeChoice;

    @FXML
    private TextField xField;

    @FXML
    private TextField yField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField caveField;

    @FXML
    private AnchorPane exit;

    @FXML
    private ChoiceBox<String> languages;

    @FXML
    private ImageView object;

    @FXML
    private Button back;

    @FXML
    private BorderPane background;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit.setVisible(false);
        ProxyController.setController(VisualizationController.class, this);
        ProxyController proxyController = new ProxyController(VisualizationController.class);
        idBuffer = proxyController.getField("idBuffer");
        dateBuffer = proxyController.getField("dateBuffer");

        new NodeManager().boxInitialize(VisualizationController.class,languages, colorChoice, characterChoice, typeChoice);
        colorChoice.setOnAction(new ImageSelection()::changeObjectImage);
//        typeChoice.setOnAction(new ImageSelection()::changeBackgroundImage);
    }

    @FXML
    void exitFromTable() {
        StaticData.getData().getConnection().close();
        DragonTable.getDragons().clear();
        Connection.stop();
        ProxyController.changeScene(exitButton, "registration.fxml");
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

    @FXML
    void saveClick() {
        try {
            Dragon dragon = new Validation().getDragon(idBuffer, VisualizationController.class);
            dragon.setCreation(dateBuffer);
            new Connection(StaticData.getData().getConnection().getSocket()).sendToServer("update", dragon);
        } catch (NullPointerException ignored) {} //Неверный ввод некоторых данных. Игнорирую
    }

    @FXML
    void enterAgain() {
        new NodeManager().enterAgain(VisualizationController.class);
    }

    private void getDragonData() {
        ProxyController controller = new ProxyController(TableController.class);
        nameField.setText(((TextField)controller.getField("nameField")).getText());
        xField.setText(((TextField)controller.getField("xField")).getText());
        yField.setText(((TextField)controller.getField("yField")).getText());
        ageField.setText(((TextField)controller.getField("ageField")).getText());
        caveField.setText(((TextField)controller.getField("caveField")).getText());


    }

}
