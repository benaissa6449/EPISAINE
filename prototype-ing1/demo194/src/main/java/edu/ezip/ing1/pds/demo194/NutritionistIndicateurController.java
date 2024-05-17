package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.client.IndicateurDoubleSelect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class NutritionistIndicateurController extends NutritionistHeadController implements Initializable {
    @FXML
    private TextFlow allergieTextFlow, calorieTextFlow, regimeTextFlow;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String regimeTempString = IndicateurDoubleSelect.getRow("COUNT_REGIME");
            String regime = regimeTempString.split(",")[0];
            String nbRegime = regimeTempString.split(",")[1];
            Text regimeText = new Text(regime + " : " + nbRegime + "%");
            regimeTextFlow.getChildren().clear();
            regimeTextFlow.getChildren().add(regimeText);

            String allergieTempString = IndicateurDoubleSelect.getRow("AVG_ALLERGIE");
            String allergie = allergieTempString.split(",")[0];
            String nbAllergieTemp = allergieTempString.split(",")[1];
            String nbAllergie = nbAllergieTemp.substring(0,nbAllergieTemp.indexOf(".")+3);
            Text allergieText = new Text(allergie + " : " + nbAllergie + "%");
            allergieTextFlow.getChildren().clear();
            allergieTextFlow.getChildren().add(allergieText);

            String calorieStringTemp = IndicateurDoubleSelect.getRow("AVG_CALORIE");
            String calorieString = calorieStringTemp.substring(0, calorieStringTemp.indexOf(".")+3);
            Text calorie = new Text(calorieString);
            calorieTextFlow.getChildren().clear();
            calorieTextFlow.getChildren().add(calorie);
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERREUR");
            alert.setHeaderText("Veuillez contacter un administrateur");
            alert.showAndWait();
        }
    }
}

