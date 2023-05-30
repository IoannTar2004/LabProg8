package com.example.run;

import com.example.grapghics.Translation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("table.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Dragon Editor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Translation.setLanguage("Русский");
        launch();
    }
}