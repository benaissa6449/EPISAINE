package edu.ezip.ing1.pds.demo194;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    // launch the application

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("connection-view.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("Episaine");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}