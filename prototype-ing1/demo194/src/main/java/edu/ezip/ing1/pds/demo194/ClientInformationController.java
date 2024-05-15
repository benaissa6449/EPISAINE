package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.business.dto.Information;
import edu.ezip.ing1.pds.client.InsertByClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.CheckComboBox;

import java.util.List;

public class ClientInformationController extends ClientHeadController {
    @FXML
    private TextField nbDeRepasTextField, idTextField;
    @FXML
    private ComboBox<String> butComboBox;
    @FXML
    private CheckComboBox<String> allergieCheckComboBox;

    public void insertInformationData(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            Integer nbDeRepas = Integer.parseInt(nbDeRepasTextField.getText().trim());
            Integer idClient = Integer.parseInt(idTextField.getText().trim());
            String but = butComboBox.getValue().trim();
            List<String> allergies = allergieCheckComboBox.getCheckModel().getCheckedItems();

            insertInformationIntoTable(nbDeRepas, idClient, but, allergies);
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

    public void insertInformationIntoTable(Integer nbDeRepas, Integer idClient, String but, List<String> allergies) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            Information information = new Information(-1, idClient, but, allergies.toString(), nbDeRepas);
            InsertByClient.sendValue("INSERT_INFORMATION", information);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Insertion effectuée.");
        }
        catch (NullPointerException npe) {
            alert.setHeaderText("Les champs ne doivent pas être vides.");
            alert.showAndWait();
        }
        catch (NumberFormatException nfe) {
            alert.setHeaderText("Ne correspond pas à une valeur numérique");
            alert.showAndWait();
        }
        catch (Exception e) {
            alert.setHeaderText("Erreur système !\nVeuillez contacter un administrateur.");
            alert.showAndWait();
        }
    }

    public void cleanFields(ActionEvent actionEvent) {
        nbDeRepasTextField.clear();
        idTextField.clear();
        allergieCheckComboBox.getCheckModel().clearChecks();
        butComboBox.getSelectionModel().clearSelection();
    }
}
