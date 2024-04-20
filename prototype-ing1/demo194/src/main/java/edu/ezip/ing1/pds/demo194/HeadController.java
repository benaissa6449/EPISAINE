package edu.ezip.ing1.pds.demo194;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HeadController {

    @FXML
    private Button dashboardButton;

    @FXML
    private Button clientButton;

    @FXML
    private Button indicateurButton;

    @FXML
    private Button informationButton;

    @FXML
    private Button nutritionnisteButton;

    @FXML
    private Button recetteButton;

    @FXML
    private Button quitterButton;

    private Parent root;
    private Stage stage;
    private Scene scene;

    public void switchToDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("dashboard-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToClient(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("client-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToInformation(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("information-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRecette(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("recette-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToNutritionniste(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("nutritionniste-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToIndicateur(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("indicateur-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void closeButton(ActionEvent event) {
        stage = (Stage) quitterButton.getScene().getWindow();
        stage.close();
    }    
    
}
