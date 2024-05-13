package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.business.dto.Information;
import edu.ezip.ing1.pds.business.dto.Recette;
import edu.ezip.ing1.pds.client.InsertByClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.CheckComboBox;

import java.util.List;

public class NutritionistRecipeController extends NutritionistHeadController {
    @FXML
    private TextField nomTextField, calorieTextField, instructionsTextField, ingredientsTextField, idNutritionistTextField;

    @FXML
    private ComboBox<String> regimeComboBox;

    public void insertRecipeData(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            String nom = nomTextField.getText().trim();
            Integer calorie = Integer.parseInt(calorieTextField.getText().trim());
            String instructions = instructionsTextField.getText().trim();
            String ingredients = ingredientsTextField.getText().trim();
            String regime = regimeComboBox.getValue().trim();
            Integer idNutritionist = Integer.parseInt(idNutritionistTextField.getText().trim());

            insertRecipeIntoTable(nom, calorie, instructions, ingredients, regime, idNutritionist);
        }
        catch (NullPointerException npe) {
            alert.setHeaderText("Les champs ne doivent pas être vides.");
            alert.showAndWait();
        }
        catch (NumberFormatException nfe) {
            alert.setHeaderText("Ne correspond pas à une valeur numérique.");
            alert.showAndWait();
        }
    }

    public void insertRecipeIntoTable(String nom, Integer calorie, String instructions, String ingredients, String regime, Integer idNutritionist) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            if (assertNotNull(nom, instructions, ingredients, regime)) {
                try {
                    Recette recette = new Recette(-1, idNutritionist, nom, calorie, ingredients, instructions, regime);
                    InsertByClient.sendValue("INSERT_RECETTE", recette);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Insertion effectuée.");
                } catch (Exception e) {
                    alert.setHeaderText("Erreur système !\nVeuillez contacter un administrateur.");
                    alert.showAndWait();
                }
            }
        }
        catch (NullPointerException npe) {
            alert.setHeaderText("Les champs ne doivent pas être vides.");
            alert.showAndWait();
        }
        catch (NumberFormatException nfe) {
            alert.setHeaderText("Ne correspond pas à une valeur numérique");
            alert.showAndWait();
        }
    }

    public boolean assertNotNull(String ... values) {
        boolean res = true;
        for (String value : values) {
            if (value == null || value.isEmpty()) {
                res = false;
            }
        }
        return res;
    }

    public void cleanFields(ActionEvent actionEvent) {
        idNutritionistTextField.clear();
        nomTextField.clear();
        calorieTextField.clear();
        instructionsTextField.clear();
        ingredientsTextField.clear();
        regimeComboBox.getSelectionModel().clearSelection();
    }
}
