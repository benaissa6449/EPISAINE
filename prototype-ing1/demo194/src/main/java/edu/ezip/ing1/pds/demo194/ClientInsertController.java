package edu.ezip.ing1.pds.demo194;

import java.math.BigDecimal;
import java.sql.Date;

import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.client.InsertByClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ClientInsertController extends ClientHeadController {
    @FXML
    private TextField prenomClient, nomClient, poidsClient, tailleClient, numClient, mailClient, villeClient, adresseClient, codePostalClient;

    @FXML
    private ComboBox<String> genreClient;

    @FXML
    private DatePicker dateClient;

    @FXML
    private Button insertButton;

    public void insertClientData(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        // to make sure each parameter is not empty or null
        try {
            String nom = nomClient.getText().trim();
            String prenom = prenomClient.getText().trim();
            Date date = Date.valueOf(dateClient.getValue());
            BigDecimal poids = new BigDecimal(Integer.parseInt(poidsClient.getText()));
            String genre = genreClient.getValue().trim();
            Integer taille = Integer.parseInt(tailleClient.getText().trim());
            String numero = numClient.getText().trim();
            String mail = mailClient.getText().trim();
            String ville = villeClient.getText().trim();
            String adresse = adresseClient.getText().trim();
            String codePostal = codePostalClient.getText().trim();

            insertClientIntoTable(nom, prenom, date, poids, genre, taille, numero, mail, ville, adresse, codePostal);
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

    public void insertClientIntoTable(String nom, String prenom, Date date, BigDecimal poids, String genre, Integer taille, String numero, String mail, String ville, String adresse, String codePostal) {
        // if true, then insert the value
        Alert alert = new Alert(Alert.AlertType.ERROR);

        // check genre
        boolean genreBoolean = true;
        if (!genre.equals("Homme") && !genre.equals("Femme")) {
            genreBoolean = false;
            alert.setHeaderText("Genre incorrect");
            alert.showAndWait();
        }

        // check mail
        boolean mailBoolean = true;
        String mailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (!mail.matches(mailPattern)) {
            mailBoolean = false;
            alert.setHeaderText("Format de mail incorrect");
            alert.showAndWait();
        }

        // check phone number
        boolean numberBoolean = true;
        String numberPattern = "(0|\\\\+33|0033)[1-9][0-9]{8}";
        if (!numero.matches(numberPattern)) {
            numberBoolean = false;
            alert.setHeaderText("Format de numéro de téléphone incorrect");
            alert.showAndWait();
        }

        // check code postal
        boolean postalBoolean = true;
        String postalPattern = "[0-8][0-9]{4}|9[0-8][0-9]{3}";
        if (!codePostal.matches(postalPattern)) {
            postalBoolean = false;
            alert.setHeaderText("Format de code postal incorrect");
            alert.showAndWait();
        }

        // if every value is correct then insert
        if (genreBoolean && mailBoolean && numberBoolean && postalBoolean) {
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
    public boolean assertNotNull(String ... values) {
        boolean res = true;
        for (String value : values) {
            if (value == null || value.isEmpty()) {
                res = false;
            }
        }
        return res;
    }
}
