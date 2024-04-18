package edu.ezip.ing1.pds.demo194;


import java.math.BigDecimal;
import java.sql.Date;

import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.client.InsertByClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ClientController {
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
                InsertByClient insertByClient = new InsertByClient();
                try {
                    insertByClient.sendValue("INSERT_CLIENT", client);
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

}
