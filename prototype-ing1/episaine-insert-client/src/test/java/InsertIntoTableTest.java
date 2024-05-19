import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.business.dto.Clients;
import edu.ezip.ing1.pds.business.dto.Information;
import edu.ezip.ing1.pds.business.dto.Informations;
import edu.ezip.ing1.pds.business.dto.Nutritionniste;
import edu.ezip.ing1.pds.business.dto.Nutritionnistes;
import edu.ezip.ing1.pds.business.dto.Recette;
import edu.ezip.ing1.pds.business.dto.Recettes;
import edu.ezip.ing1.pds.client.InsertByClient;
import edu.ezip.ing1.pds.client.SelectClient;
import edu.ezip.ing1.pds.client.SelectInformation;
import edu.ezip.ing1.pds.client.SelectNutritionist;
import edu.ezip.ing1.pds.client.SelectRecipe;

public class InsertIntoTableTest {
    
    @Test
    public void insertClientIntoTable() throws IOException, SQLException, InterruptedException {
        // Given
        String requestOrder = "INSERT_CLIENT";
        Date date = new Date(System.currentTimeMillis());
        Client client = new Client(1, "nomTestUnitaire", "prenomTestUnitaire", date, new BigDecimal(55), "Homme", 170, "0606060606", "mail@mail.fr", "villeTestUnitaire", "adresseTestUnitaire", "94000");
        
        // When
        InsertByClient.sendValue(requestOrder, client);

        // Then
        Clients clients = SelectClient.getValue("SELECT_ALL_CLIENTS");
        for (Client client2 : clients.getClients()) {
            if (client2.getId_client() == client.getId_client()) {
                assertEquals(client, client2);
                break;
            }
        }
    }
    @Test
    public void insertNutritionistIntoTable() throws IOException, SQLException, InterruptedException {
        // Given
        String requestOrder = "INSERT_NUTRITIONNISTE";
        Nutritionniste nutritionniste = new Nutritionniste(1, "nomTestUnitaire", "prenomTestUnitaire", "0606060606", "mail_n");
        
        // When
        InsertByClient.sendValue(requestOrder, nutritionniste);

        // Then
        Nutritionnistes nutritionnistes = SelectNutritionist.getValue("SELECT_ALL_NUTRITIONNISTES");
        for (Nutritionniste nutritionniste2 : nutritionnistes.getNutritionnistes()) {
            if (nutritionniste2.getId_nutritionniste() == nutritionniste.getId_nutritionniste()) {
                assertEquals(nutritionniste2, nutritionniste);
                break;
            }
        }
    }
    @Test
    public void insertRecipeIntoTable() throws IOException, SQLException, InterruptedException {
        // Given
        String requestOrder = "INSERT_RECETTE";
        Recette recette = new Recette(1, 1, "nomTestUnitaire", 2000, "ingredientsTestUnitaire", "instructionsTestUnitaire", "normal");
        
        // When
        InsertByClient.sendValue(requestOrder, recette);

        // Then
        Recettes recettes = SelectRecipe.getValue("SELECT_ALL_RECETTES", null);
        for (Recette recette2 : recettes.getRecettes()) {
            if (recette2.getId_recette() == recette.getId_recette()) {
                assertEquals(recette2, recette);
                break;
            }
        }
    }


    @Test
    public void insertInformationIntoTable() throws IOException, SQLException, InterruptedException {
        // Given
        String requestOrder = "INSERT_INFORMATION";
        Information information = new Information(1, 2, "perte de poids", "oeuf", 5);
        
        // When
        InsertByClient.sendValue(requestOrder, information);

        // Then
        Informations informations = SelectInformation.getValue("SELECT_ALL_INFORMATIONS");
        for (Information information2 : informations.getInformations()) {
            if (information2.getId_info() == information.getId_info()) {
                assertEquals(information2, information);
                break;
            }
        }
    }
}
