package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.business.dto.Clients;
import edu.ezip.ing1.pds.client.SelectClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class NutritionistClientController extends NutritionistHeadController {

    @FXML
    private Button selectButton;
    @FXML
    private TableColumn<String,String> idClientColumn, prenomClientColumn, nomClientColumn, dateClientColumn, poidsClientColumn, genreClientColumn, tailleClientColumn, telephoneClientColumn, mailClientColumn, villeClientColumn, adresseClientColumn, codePostalClientColumn;

    @FXML
    private TableView<Client> clientTableView;

    @FXML
    private void selectClientData(ActionEvent actionEvent) {
        try {
            Clients clients = SelectClient.getValue("SELECT_ALL_CLIENTS");

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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
