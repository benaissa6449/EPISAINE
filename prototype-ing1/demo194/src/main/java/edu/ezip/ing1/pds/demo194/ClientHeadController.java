package edu.ezip.ing1.pds.demo194;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ClientHeadController {

    @FXML
    Button dashboardButton, clientButton, informationButton, recetteButton, nutritionistButton, indicateurButton, quitterButton;

    @FXML
    void switchToDashboardView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("client-dashboard-view.fxml"));
        Stage stage = (Stage) dashboardButton.getScene().getWindow();
        stage.setScene(new Scene(root, 1280, 720));
    }
    @FXML
    void switchToClientView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("client-insert-view.fxml"));
        Stage stage = (Stage) clientButton.getScene().getWindow();
        stage.setScene(new Scene(root, 1280, 720));
    }

    @FXML
    void switchToInformationView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("client-information-view.fxml"));
        Stage stage = (Stage) informationButton.getScene().getWindow();
        stage.setScene(new Scene(root, 1280, 720));
    }

    @FXML
    void switchToRecetteView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("client-recette-view.fxml"));
        Stage stage = (Stage) recetteButton.getScene().getWindow();
        stage.setScene(new Scene(root, 1280, 720));
    }

    @FXML
    void switchToNutritionistView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("client-nutritionist-view.fxml"));
        Stage stage = (Stage) nutritionistButton.getScene().getWindow();
        stage.setScene(new Scene(root, 1280, 720));
    }
    @FXML
    void switchToIndicateurView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("client-indicateur-view.fxml"));
        Stage stage = (Stage) indicateurButton.getScene().getWindow();
        stage.setScene(new Scene(root, 1280, 720));
    }

    @FXML
    public void closeButton(ActionEvent event) {
        Stage stage = (Stage) quitterButton.getScene().getWindow();
        stage.close();
    }    
    
}
