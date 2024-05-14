package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.business.dto.*;
import edu.ezip.ing1.pds.client.DeleteByClient;
import edu.ezip.ing1.pds.client.SelectClient;
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

public class NutritionistClientController extends NutritionistHeadController {
    @FXML
    private Button selectButton;
    @FXML
    private TableColumn<String,String> idClientColumn, prenomClientColumn, nomClientColumn, dateClientColumn, poidsClientColumn, genreClientColumn, tailleClientColumn, telephoneClientColumn, mailClientColumn, villeClientColumn, adresseClientColumn, codePostalClientColumn;
    @FXML
    private TableView<Client> clientTableView;
    @FXML
    private TextField searchTextField;

    @FXML
    private void selectClientData(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            clientTableView.getSelectionModel().setCellSelectionEnabled(true);
            clientTableView.setOnMouseClicked((MouseEvent event) -> {
                TablePosition tablePosition = clientTableView.getSelectionModel().getSelectedCells().getFirst();
                int row = tablePosition.getRow();

                Client client = clientTableView.getItems().get(row);
                TableColumn tableColumn = tablePosition.getTableColumn();

                String data = tableColumn.getCellObservableValue(client).getValue().toString();

                alert.setHeaderText(tableColumn.getText() + " : " + data);
                alert.showAndWait();
            });

            Clients clients = (Clients) SelectClient.getValue("SELECT_ALL_CLIENTS");

            idClientColumn.setCellValueFactory(new PropertyValueFactory<>("Id_client"));
            nomClientColumn.setCellValueFactory(new PropertyValueFactory<>("Nom_client"));
            prenomClientColumn.setCellValueFactory(new PropertyValueFactory<>("Prenom_client"));
            dateClientColumn.setCellValueFactory(new PropertyValueFactory<>("Date_de_naissance_client"));
            poidsClientColumn.setCellValueFactory(new PropertyValueFactory<>("Poids"));
            genreClientColumn.setCellValueFactory(new PropertyValueFactory<>("Genre"));
            tailleClientColumn.setCellValueFactory(new PropertyValueFactory<>("Taille"));
            telephoneClientColumn.setCellValueFactory(new PropertyValueFactory<>("Numero_de_telephone_client"));
            mailClientColumn.setCellValueFactory(new PropertyValueFactory<>("Mail_client"));
            villeClientColumn.setCellValueFactory(new PropertyValueFactory<>("Ville"));
            adresseClientColumn.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
            codePostalClientColumn.setCellValueFactory(new PropertyValueFactory<>("Code_Postal"));

            clientTableView.getItems().clear();

            for (Client client : clients.getClients()) {
                clientTableView.getItems().add(client);
            }

            FilteredList<Client> filteredList = new FilteredList<>(clientTableView.getItems(), p->true);

            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(client -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (String.valueOf(client.getId_client()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(client.getNom_client()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(client.getPrenom_client()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(client.getDate_de_naissance_client()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(client.getGenre()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(client.getTaille()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(client.getMail_client()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(client.getCode_Postal()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(client.getVille()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(client.getPoids()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(client.getNumero_de_telephone_client()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else return false;
                });
            });

            SortedList<Client> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(clientTableView.comparatorProperty());
            clientTableView.setItems(sortedList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateValue(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        TablePosition tablePosition = clientTableView.getSelectionModel().getSelectedCells().getFirst();

        int row = tablePosition.getRow();

        Client client = clientTableView.getItems().get(row);

        TableColumn tableColumn = tablePosition.getTableColumn();

        String data = tableColumn.getCellObservableValue(client).getValue().toString();

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
                    update.setNewColumn("nom_client");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_client");
                    update.setConditionValue(String.valueOf(client.getId_client()));
                    UpdateByClient.updateValue("UPDATE_CLIENT", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                case "Prénom":
                    update = new Update();
                    update.setNewColumn("prenom_client");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_client");
                    update.setConditionValue(String.valueOf(client.getId_client()));
                    UpdateByClient.updateValue("UPDATE_CLIENT", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                case "Date de naissance":
                    update = new Update();
                    update.setNewColumn("date_de_naissance_client");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_client");
                    update.setConditionValue(String.valueOf(client.getId_client()));
                    UpdateByClient.updateValue("UPDATE_CLIENT", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                case "Poids":
                    update = new Update();
                    update.setNewColumn("poids");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_client");
                    update.setConditionValue(String.valueOf(client.getId_client()));
                    UpdateByClient.updateValue("UPDATE_CLIENT", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                case "Genre":
                    update = new Update();
                    update.setNewColumn("genre");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_client");
                    update.setConditionValue(String.valueOf(client.getId_client()));
                    UpdateByClient.updateValue("UPDATE_CLIENT", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                case "Taille":
                    update = new Update();
                    update.setNewColumn("taille");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_client");
                    update.setConditionValue(String.valueOf(client.getId_client()));
                    UpdateByClient.updateValue("UPDATE_CLIENT", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                case "Numéro de téléphone":
                    update = new Update();
                    update.setNewColumn("numero_de_telephone_client");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_client");
                    update.setConditionValue(String.valueOf(client.getId_client()));
                    UpdateByClient.updateValue("UPDATE_CLIENT", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                case "Mail":
                    update = new Update();
                    update.setNewColumn("mail_client");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_client");
                    update.setConditionValue(String.valueOf(client.getId_client()));
                    UpdateByClient.updateValue("UPDATE_CLIENT", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                case "Ville":
                    update = new Update();
                    update.setNewColumn("ville");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_client");
                    update.setConditionValue(String.valueOf(client.getId_client()));
                    UpdateByClient.updateValue("UPDATE_CLIENT", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                case "Adresse":
                    update = new Update();
                    update.setNewColumn("adresse");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_client");
                    update.setConditionValue(String.valueOf(client.getId_client()));
                    UpdateByClient.updateValue("UPDATE_CLIENT", update);
                    alert.setHeaderText("Modification effectuée.");
                    alert.showAndWait();
                    break;
                case "Code Postal":
                    update = new Update();
                    update.setNewColumn("code_postal");
                    update.setNewValue(textInputDialog.getEditor().getText());
                    update.setConditionColumn("id_client");
                    update.setConditionValue(String.valueOf(client.getId_client()));
                    UpdateByClient.updateValue("UPDATE_CLIENT", update);
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
        TablePosition tablePosition = clientTableView.getSelectionModel().getSelectedCells().getFirst();

        int row = tablePosition.getRow();

        Client client = clientTableView.getItems().get(row);

        TableColumn tableColumn = tablePosition.getTableColumn();

        String data = tableColumn.getCellObservableValue(client).getValue().toString();

        alert.setHeaderText("Etes-vous sûr de vouloir supprimer ce client ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                DeleteByClient.deleteValue("DELETE_CLIENT", client.getId_client());
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
