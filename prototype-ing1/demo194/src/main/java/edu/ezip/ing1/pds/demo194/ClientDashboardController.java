package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.client.CountTableRow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClientDashboardController extends ClientHeadController implements Initializable {
    @FXML
    private TextFlow nbClients, nbWomen, nbMen, nbNutritionists, nbRecipes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // initialize the values shown in each box, related to the information about the application's user
        try {
            Integer nbC = CountTableRow.getRow("COUNT_CLIENTS");
            Integer nbW = CountTableRow.getRow("COUNT_WOMEN");
            Integer nbM = CountTableRow.getRow("COUNT_MEN");
            Integer nbN = CountTableRow.getRow("COUNT_NUTRITIONNISTES");
            Integer nbR = CountTableRow.getRow("COUNT_RECETTES");

            nbClients.getChildren().clear();
            nbClients.getChildren().add(new Text(String.valueOf(nbC)));

            nbWomen.getChildren().clear();
            nbWomen.getChildren().add(new Text(String.valueOf(nbW)));

            nbMen.getChildren().clear();
            nbMen.getChildren().add(new Text(String.valueOf(nbM)));

            nbNutritionists.getChildren().clear();
            nbNutritionists.getChildren().add(new Text(String.valueOf(nbN)));

            nbRecipes.getChildren().clear();
            nbRecipes.getChildren().add(new Text(String.valueOf(nbR)));
        }
        catch (Exception e) {
            System.out.println("Erreur");
        }
    }

    // methods to open the hyperlink related to the button's icon
    public void leclercHyperlink(ActionEvent actionEvent) {
        try {
            URI url = new URI("https://www.e.leclerc");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Voulez-vous ouvrir la page " + url.toString());
            Optional<ButtonType> res = alert.showAndWait();
            if (res.get() == ButtonType.OK) {
                Desktop.getDesktop().browse(url);
            }
        }
        catch (URISyntaxException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur système");
            alert.setHeaderText("Veuillez contacter un administrateur.");
        }
    }

    public void superuHyperlink(ActionEvent actionEvent) {
        try {
            URI url = new URI("https://www.magasins-u.com");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Voulez-vous ouvrir la page " + url.toString());
            Optional<ButtonType> res = alert.showAndWait();
            if (res.get() == ButtonType.OK) {
                Desktop.getDesktop().browse(url);
            }
        }
        catch (URISyntaxException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur système");
            alert.setHeaderText("Veuillez contacter un administrateur.");
        }
    }

    public void intermarcheHyperlink(ActionEvent actionEvent) {
        try {
            URI url = new URI("https://www.intermarche.com");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Voulez-vous ouvrir la page " + url.toString());
            Optional<ButtonType> res = alert.showAndWait();
            if (res.get() == ButtonType.OK) {
                Desktop.getDesktop().browse(url);
            }
        }
        catch (URISyntaxException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur système");
            alert.setHeaderText("Veuillez contacter un administrateur.");
        }
    }

    public void auchanHyperlink(ActionEvent actionEvent) {
        try {
            URI url = new URI("https://www.auchan.fr");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Voulez-vous ouvrir la page " + url.toString());
            Optional<ButtonType> res = alert.showAndWait();
            if (res.get() == ButtonType.OK) {
                Desktop.getDesktop().browse(url);
            }
        }
        catch (URISyntaxException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur système");
            alert.setHeaderText("Veuillez contacter un administrateur.");
        }
    }

    public void franprixHyperlink(ActionEvent actionEvent) {
        try {
            URI url = new URI("https://www.franprix.fr");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Voulez-vous ouvrir la page " + url.toString());
            Optional<ButtonType> res = alert.showAndWait();
            if (res.get() == ButtonType.OK) {
                Desktop.getDesktop().browse(url);
            }
        }
        catch (URISyntaxException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur système");
            alert.setHeaderText("Veuillez contacter un administrateur.");
        }
    }

    public void casinoHyperlink(ActionEvent actionEvent) {
        try {
            URI url = new URI("https://www.supercasino.fr");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Voulez-vous ouvrir la page " + url.toString());
            Optional<ButtonType> res = alert.showAndWait();
            if (res.get() == ButtonType.OK) {
                Desktop.getDesktop().browse(url);
            }
        }
        catch (URISyntaxException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur système");
            alert.setHeaderText("Veuillez contacter un administrateur.");
        }
    }
}
