package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.business.dto.*;
import edu.ezip.ing1.pds.client.DeleteByClient;
import edu.ezip.ing1.pds.client.SelectInformation;
import edu.ezip.ing1.pds.client.UpdateByClient;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class NutritionistInformationController extends NutritionistHeadController {
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Information> informationTableView;
    @FXML
    private TableColumn<Information, Integer> idInformationColumn, idClientColumn, repasColumn ;
    @FXML
    private  TableColumn<Information, String> butColumn, allergieColumn;

    public void selectInformationData(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            informationTableView.getSelectionModel().setCellSelectionEnabled(true);
            informationTableView.setOnMouseClicked((MouseEvent event) -> {
                TablePosition tablePosition = informationTableView.getSelectionModel().getSelectedCells().getFirst();
                int row = tablePosition.getRow();

                Information information = informationTableView.getItems().get(row);
                TableColumn tableColumn = tablePosition.getTableColumn();

                String data = tableColumn.getCellObservableValue(information).getValue().toString();

                alert.setHeaderText(tableColumn.getText() + " : " + data);
                alert.showAndWait();
            });
            Informations informations = SelectInformation.getValue("SELECT_ALL_INFORMATIONS");
            idInformationColumn.setCellValueFactory(new PropertyValueFactory<>("Id_info"));
            idClientColumn.setCellValueFactory(new PropertyValueFactory<>("Id_Client"));
            repasColumn.setCellValueFactory(new PropertyValueFactory<>("NbDeRepas"));
            butColumn.setCellValueFactory(new PropertyValueFactory<>("But"));
            allergieColumn.setCellValueFactory(new PropertyValueFactory<>("Allergie"));

            informationTableView.getItems().clear();

            for (Information information : informations.getInformations()) {
                informationTableView.getItems().add(information);
            }

            FilteredList<Information> filteredList = new FilteredList<>(informationTableView.getItems(), p->true);

            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(information -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (String.valueOf(information.getAllergie()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(information.getBut()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(information.getId_Client()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(information.getId_info()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(information.getNbDeRepas()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else return false;
                });
            });

            SortedList<Information> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(informationTableView.comparatorProperty());
            informationTableView.setItems(sortedList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateValue(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        TablePosition tablePosition = informationTableView.getSelectionModel().getSelectedCells().getFirst();

        int row = tablePosition.getRow();

        Information information = informationTableView.getItems().get(row);

        TableColumn tableColumn = tablePosition.getTableColumn();

        String data = tableColumn.getCellObservableValue(information).getValue().toString();

        TextInputDialog textInputDialog = new TextInputDialog("Nouvelle valeur :");
        textInputDialog.setHeaderText("Ancienne valeur : " + data);
        textInputDialog.setTitle("Modifier");
        textInputDialog.showAndWait();

        String columnName = tableColumn.getText();

        Update update;
        try {
            switch (columnName) {
                case "ID Client":
                    update = new Update();
                    update.setNewColumn("id_client");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_info");
                    update.setConditionValue(String.valueOf(information.getId_info()));
                    UpdateByClient.updateValue("UPDATE_INFORMATION", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                case "But":
                    update = new Update();
                    update.setNewColumn("but");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_info");
                    update.setConditionValue(String.valueOf(information.getId_info()));
                    UpdateByClient.updateValue("UPDATE_INFORMATION", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                case "Allergies":
                    update = new Update();
                    update.setNewColumn("allergie");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_info");
                    update.setConditionValue(String.valueOf(information.getId_info()));
                    UpdateByClient.updateValue("UPDATE_INFORMATION", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                case "Nombre de repas":
                    update = new Update();
                    update.setNewColumn("nbderepas");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_info");
                    update.setConditionValue(String.valueOf(information.getId_info()));
                    UpdateByClient.updateValue("UPDATE_INFORMATION", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                default:
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setHeaderText("Valeur non modifiable.");
                    break;
            }
        }
        catch (Exception e) {
            System.out.println("...");
        }
    }

    public void deleteValue(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        TablePosition tablePosition = informationTableView.getSelectionModel().getSelectedCells().getFirst();

        int row = tablePosition.getRow();

        Information information = informationTableView.getItems().get(row);

        TableColumn tableColumn = tablePosition.getTableColumn();

        String data = tableColumn.getCellObservableValue(information).getValue().toString();

        alert.setHeaderText("Etes-vous sûr de vouloir supprimer cette information ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                DeleteByClient.deleteValue("DELETE_INFORMATION", information.getId_info());
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Suppression effectuée.");
                alert.showAndWait();
            }
            catch (Exception e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText("Erreur de suppression.");
                alert.showAndWait();
            }
        }
    }
}
