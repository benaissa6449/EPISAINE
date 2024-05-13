package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.business.dto.Clients;
import edu.ezip.ing1.pds.business.dto.Nutritionniste;
import edu.ezip.ing1.pds.client.SelectClient;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class NutritionistClientController extends NutritionistHeadController implements Initializable {
    @FXML
    private Button selectButton;
    @FXML
    private TableColumn<String,String> idClientColumn, prenomClientColumn, nomClientColumn, dateClientColumn, poidsClientColumn, genreClientColumn, tailleClientColumn, telephoneClientColumn, mailClientColumn, villeClientColumn, adresseClientColumn, codePostalClientColumn;
    @FXML
    private TableView<Client> clientTableView;
    @FXML
    private TextField searchTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        clientTableView.getSelectionModel().setCellSelectionEnabled(true);
        clientTableView.setOnMouseClicked((MouseEvent event) -> {
            TablePosition tablePosition = clientTableView.getSelectionModel().getSelectedCells().getFirst();
            int row = tablePosition.getRow();

            Client client = clientTableView.getItems().get(row);
            TableColumn tableColumn = tablePosition.getTableColumn();

            String data = tableColumn.getCellObservableValue(client).getValue().toString();

            alert.setHeaderText(data);
            alert.showAndWait();
        });
    }

    @FXML
    private void selectClientData(ActionEvent actionEvent) {
        try {
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
}
