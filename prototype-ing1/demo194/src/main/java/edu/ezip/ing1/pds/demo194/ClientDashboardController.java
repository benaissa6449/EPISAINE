package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.client.CountTableRow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientDashboardController extends ClientHeadController implements Initializable {
    @FXML
    private TextFlow nbClients, nbWomen, nbMen, nbNutritionists, nbRecipes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
}
