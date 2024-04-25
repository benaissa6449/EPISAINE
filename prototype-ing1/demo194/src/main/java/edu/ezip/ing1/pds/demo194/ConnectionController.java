package edu.ezip.ing1.pds.demo194;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ConnectionController {
    @FXML
    Button clientButton, nutritionistButton;

    @FXML
    void switchToClientView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("client-dashboard-view.fxml"));
        Stage stage = (Stage) clientButton.getScene().getWindow();
        stage.setScene(new Scene(root, 1280, 720));
    }

    @FXML
    void switchToNutritionistView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("nutritionist-dashboard-view.fxml"));
        Stage stage = (Stage) nutritionistButton.getScene().getWindow();
        stage.setScene(new Scene(root, 1280, 720));
    }
}