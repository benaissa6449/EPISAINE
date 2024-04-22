package edu.ezip.ing1.pds.demo194;

import java.math.BigDecimal;
import java.sql.Date;

import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.business.dto.Clients;
import edu.ezip.ing1.pds.client.InsertByClient;
import edu.ezip.ing1.pds.client.SelectByClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientController extends HeadController {
    @FXML
    private TextField prenomClient;

    @FXML
    private TextField nomClient;

    @FXML
    private DatePicker dateClient;

    @FXML
    private TextField poidsClient;
    
    @FXML
    private TextField genreClient;

    @FXML
    private TextField tailleClient;

    @FXML
    private TextField numClient;

    @FXML
    private TextField mailClient;

    @FXML
    private TextField villeClient;

    @FXML
    private TextField adresseClient;

    @FXML
    private TextField codePostalClient;

    @FXML
    private Button insertButton;

    @FXML
    private TableView<Client> clientTableView;

    @FXML
    private Button selectButton;

    @FXML
    private TableColumn<String,String> idClientColumn;
    @FXML
    private TableColumn<String,String> prenomClientColumn;
    @FXML
    private TableColumn<String,String> nomClientColumn;
    @FXML
    private TableColumn<String,String> dateClientColumn;
    @FXML
    private TableColumn<String,String> poidsClientColumn;
    @FXML
    private TableColumn<String,String> genreClientColumn;
    @FXML
    private TableColumn<String,String> tailleClientColumn;
    @FXML
    private TableColumn<String,String> telephoneClientColumn;
    @FXML
    private TableColumn<String,String> mailClientColumn;
    @FXML
    private TableColumn<String,String> villeClientColumn;
    @FXML
    private TableColumn<String,String> adresseClientColumn;
    @FXML
    private TableColumn<String,String> codePostalClientColumn;


    public void insertClientData(ActionEvent actionEvent) {
        // if true, then insert the value
        Boolean insertBoolean = true;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        
        // to make sure each parameter is not empty or null
        try {
            String prenom = prenomClient.getText();
            String nom = nomClient.getText();
            Date date = Date.valueOf(dateClient.getValue());
            BigDecimal poids = new BigDecimal(Integer.parseInt(poidsClient.getText()));
            String genre = genreClient.getText();
            Integer taille = Integer.parseInt(tailleClient.getText());
            String numero = numClient.getText();
            String mail = mailClient.getText();
            String ville = villeClient.getText();
            String adresse = adresseClient.getText();
            String codePostal = codePostalClient.getText();

            if (!genre.toLowerCase().equals("homme") && !genre.toUpperCase().equals("femme")) {
                insertBoolean = false;
                alert.setHeaderText("Genre incorrect");
                alert.showAndWait();
            }

            if (insertBoolean) {
                Client client = new Client(-1, nom, prenom, date, poids, genre, taille, numero, mail, ville, adresse, codePostal);
                try {
                    InsertByClient.sendValue("INSERT_CLIENT", client);
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Insertion effectuée.");
                    alert.showAndWait();
                }
                catch (Exception e) {
                    alert.setHeaderText("Erreur système !\nVeuillez contacter un administrateur.");
                    alert.showAndWait();
                }
            }
        }
        catch (NullPointerException npe) {
            insertBoolean = false;
            alert.setHeaderText("Les champs ne doivent pas être vides.");
            alert.showAndWait();
        }
        catch (NumberFormatException nfe) {
            insertBoolean = false;
            alert.setHeaderText("Ne correspond pas à une valeur numérique.");
            alert.showAndWait();
        }
    }

    public boolean assertNotNull(String ... values){
        boolean res = true;
        for (String value : values) {
            if (value == null || value.isEmpty()) {
                res = false;
            }
        }
        return res;
    }

    public void selectClientData(ActionEvent actionEvent) {
        try {
            Clients clients = SelectByClient.getValue("SELECT_ALL_CLIENTS");

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
