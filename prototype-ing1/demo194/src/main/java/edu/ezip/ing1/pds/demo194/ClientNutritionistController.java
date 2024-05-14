package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.business.dto.*;
import edu.ezip.ing1.pds.client.DeleteByClient;
import edu.ezip.ing1.pds.client.SelectClient;
import edu.ezip.ing1.pds.client.SelectNutritionist;
import edu.ezip.ing1.pds.client.UpdateByClient;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClientNutritionistController extends ClientHeadController {
    @FXML
    private Button selectButton;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Nutritionniste> nutritionistTableView;
    @FXML
    private TableColumn<Nutritionniste, Integer> idNutritionistColumn;
    @FXML
    private TableColumn<Nutritionniste, String> prenomColumn, nomColumn, telephoneColumn, mailColumn;

    @FXML
    private void selectNutritionistData(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            nutritionistTableView.getSelectionModel().setCellSelectionEnabled(true);
            nutritionistTableView.setOnMouseClicked((MouseEvent event) -> {
                TablePosition tablePosition = nutritionistTableView.getSelectionModel().getSelectedCells().getFirst();
                int row = tablePosition.getRow();

                Nutritionniste nutritionniste = nutritionistTableView.getItems().get(row);
                TableColumn tableColumn = tablePosition.getTableColumn();

                String data = tableColumn.getCellObservableValue(nutritionniste).getValue().toString();

                alert.setHeaderText(tableColumn.getText() + " : " + data);
                alert.showAndWait();
            });

            Nutritionnistes nutritionnistes = SelectNutritionist.getValue("SELECT_ALL_NUTRITIONNISTES");

            idNutritionistColumn.setCellValueFactory(new PropertyValueFactory<>("Id_nutritionniste"));
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("Nom_N"));
            prenomColumn.setCellValueFactory(new PropertyValueFactory<>("Prenom_N"));
            telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("Numero_de_telephone_N"));
            mailColumn.setCellValueFactory(new PropertyValueFactory<>("Mail_N"));

            nutritionistTableView.getItems().clear();

            for (Nutritionniste nutritionniste : nutritionnistes.getNutritionnistes()) {
                nutritionistTableView.getItems().add(nutritionniste);
            }

            FilteredList<Nutritionniste> filteredList = new FilteredList<>(nutritionistTableView.getItems(), p->true);

            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(nutritionniste -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (String.valueOf(nutritionniste.getId_nutritionniste()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(nutritionniste.getNom_N()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(nutritionniste.getPrenom_N()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(nutritionniste.getMail_N()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(nutritionniste.getNumero_de_telephone_N()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else return false;
                });
            });

            SortedList<Nutritionniste> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(nutritionistTableView.comparatorProperty());
            nutritionistTableView.setItems(sortedList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateValue(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        TablePosition tablePosition = nutritionistTableView.getSelectionModel().getSelectedCells().getFirst();

        int row = tablePosition.getRow();

        Nutritionniste nutritionniste = nutritionistTableView.getItems().get(row);

        TableColumn tableColumn = tablePosition.getTableColumn();

        String data = tableColumn.getCellObservableValue(nutritionniste).getValue().toString();

        TextInputDialog textInputDialog = new TextInputDialog("Nouvelle valeur :");
        textInputDialog.setHeaderText("Ancienne valeur : " + data);
        textInputDialog.setTitle("Modifier");
        textInputDialog.showAndWait();

        String columnName = tableColumn.getText();

        Update update;
        try {
            switch (columnName) {
                case "Nom":
                    update = new Update();
                    update.setNewColumn("nom_n");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_nutritionniste");
                    update.setConditionValue(String.valueOf(nutritionniste.getId_nutritionniste()));
                    UpdateByClient.updateValue("UPDATE_NUTRITIONNISTE", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                case "Prénom":
                    update = new Update();
                    update.setNewColumn("prenom_n");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_nutritionniste");
                    update.setConditionValue(String.valueOf(nutritionniste.getId_nutritionniste()));
                    UpdateByClient.updateValue("UPDATE_NUTRITIONNISTE", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                case "Numéro de téléphone":
                    update = new Update();
                    update.setNewColumn("numero_de_telephone_n");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_nutritionniste");
                    update.setConditionValue(String.valueOf(nutritionniste.getId_nutritionniste()));
                    UpdateByClient.updateValue("UPDATE_NUTRITIONNISTE", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                case "Adresse mail":
                    update = new Update();
                    update.setNewColumn("mail_n");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_nutritionniste");
                    update.setConditionValue(String.valueOf(nutritionniste.getId_nutritionniste()));
                    UpdateByClient.updateValue("UPDATE_NUTRITIONNISTE", update);
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
        TablePosition tablePosition = nutritionistTableView.getSelectionModel().getSelectedCells().getFirst();

        int row = tablePosition.getRow();

        Nutritionniste nutritionniste = nutritionistTableView.getItems().get(row);

        TableColumn tableColumn = tablePosition.getTableColumn();

        String data = tableColumn.getCellObservableValue(nutritionniste).getValue().toString();

        alert.setHeaderText("Etes-vous sûr de vouloir supprimer cet nutritionniste ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                DeleteByClient.deleteValue("DELETE_NUTRITIONNISTE", nutritionniste.getId_nutritionniste());
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
