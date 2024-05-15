package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.business.dto.Nutritionniste;
import edu.ezip.ing1.pds.client.InsertByClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class NutritionistInsertController extends NutritionistHeadController {
    @FXML
    private TextField prenomTextField, nomTextField, numeroTextField, mailTextField;

    // insert the nutritionist data into the database
    public void insertNutritionistData(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            String nom = nomTextField.getText().trim();
            String prenom = prenomTextField.getText().trim();
            String mail = mailTextField.getText().trim();
            String numero = numeroTextField.getText().trim();

            insertNutritionistIntoTable(nom, prenom, mail, numero);
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

    public void insertNutritionistIntoTable(String nom, String prenom, String numero, String mail) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            if (assertNotNull(nom, prenom, numero, mail)) {
                try {
                    Nutritionniste nutritionniste = new Nutritionniste(-1,nom,prenom,numero,mail);
                    InsertByClient.sendValue("INSERT_NUTRITIONNISTE", nutritionniste);
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

    // clean every field
    public void cleanFields(ActionEvent actionEvent) {
        prenomTextField.clear();
        nomTextField.clear();
        numeroTextField.clear();
        mailTextField.clear();
    }
}
