package edu.ezip.ing1.pds.demo194;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.client.InsertByClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ClientInsertController extends ClientHeadController {
    // this class is related to insert the client into the sql table

    @FXML
    private TextField prenomClient, nomClient, poidsClient, tailleClient, numClient, mailClient, villeClient, adresseClient, codePostalClient;
    @FXML
    private ComboBox<String> genreClient;
    @FXML
    private DatePicker dateClient;

    // this method check if every field content is correct, then call the insert method
    public void insertClientData(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
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
    // this method insert the client into the sql table
    public void insertClientIntoTable(String nom, String prenom, Date date, BigDecimal poids, String genre, Integer taille, String numero, String mail, String ville, String adresse, String codePostal) {
        // if true, then insert the value
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");

        // check not null
        boolean notNullBoolean = assertNotNull(nom, prenom, genre, numero, mail, ville, adresse, codePostal);

        // check date de naissance
        boolean naissanceBoolean = true;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(date.toLocalDate().atStartOfDay())) {
            naissanceBoolean = false;
            alert.setHeaderText("Date de naissance incorrect");
            alert.showAndWait();
        }
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
        if (genreBoolean && mailBoolean && numberBoolean && postalBoolean && notNullBoolean && naissanceBoolean) {
            Client client = new Client(-1, nom, prenom, date, poids, genre, taille, numero, mail, ville, adresse, codePostal);
            try {
                InsertByClient.sendValue("INSERT_CLIENT", client);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Insertion effectuée.");
                alert.showAndWait();
            }
            catch (Exception e) {
                alert.setHeaderText("Erreur système !\nVeuillez contacter un administrateur.");
                alert.showAndWait();
            }
        }
    }

    // check if every string is not empty or not defined
    public boolean assertNotNull(String ... values) {
        boolean res = true;
        for (String value : values) {
            if (value == null || value.isEmpty()) {
                res = false;
            }
        }
        return res;
    }

    // empty every field
    public void cleanFields(ActionEvent actionEvent) {
        prenomClient.clear();
        nomClient.clear();
        dateClient.getEditor().clear();
        poidsClient.clear();
        genreClient.getSelectionModel().clearSelection();
        tailleClient.clear();
        numClient.clear();
        mailClient.clear();
        villeClient.clear();
        adresseClient.clear();
        codePostalClient.clear();
    }
}
