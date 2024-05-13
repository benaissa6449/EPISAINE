package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.client.CountTableRow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class NutritionistDashboardController extends NutritionistHeadController implements Initializable {
    @FXML
    private TextFlow nbClients, nbWomen, nbMen, nbNutritionists, nbRecipes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Text NA = new Text("N/A");
        try {
            Integer nbC = CountTableRow.getRow("COUNT_CLIENTS");
            nbClients.getChildren().clear();
            nbClients.getChildren().add(new Text(String.valueOf(nbC)));
        }
        catch (Exception e) {
            nbClients.getChildren().clear();
            nbClients.getChildren().add(NA);
        }

        try{
            Integer nbW = CountTableRow.getRow("COUNT_WOMEN");
            nbWomen.getChildren().clear();
            nbWomen.getChildren().add(new Text(String.valueOf(nbW)));
        }
        catch (Exception e) {
            nbWomen.getChildren().clear();
            nbWomen.getChildren().add(NA);
        }

        try {
            Integer nbM = CountTableRow.getRow("COUNT_MEN");
            nbMen.getChildren().clear();
            nbMen.getChildren().add(new Text(String.valueOf(nbM)));
        }
        catch (Exception e) {
            nbMen.getChildren().clear();
            nbMen.getChildren().add(NA);
        }

        try{
            Integer nbN = CountTableRow.getRow("COUNT_NUTRITIONNISTES");
            nbNutritionists.getChildren().clear();
            nbNutritionists.getChildren().add(new Text(String.valueOf(nbN)));
        }
        catch (Exception e) {
            nbNutritionists.getChildren().clear();
            nbNutritionists.getChildren().add(NA);
        }

        try {
            Integer nbR = CountTableRow.getRow("COUNT_RECETTES");
            nbRecipes.getChildren().clear();
            nbRecipes.getChildren().add(new Text(String.valueOf(nbR)));
        }
        catch (Exception e) {
            nbRecipes.getChildren().clear();
            nbRecipes.getChildren().add(NA);
        }
    }
}
