import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
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
import edu.ezip.ing1.pds.client.DeleteByClient;
import edu.ezip.ing1.pds.client.SelectClient;
import edu.ezip.ing1.pds.client.SelectInformation;
import edu.ezip.ing1.pds.client.SelectNutritionist;
import edu.ezip.ing1.pds.client.SelectRecipe;

public class DeleteValueFromDatabaseTest {
    
    @Test
    public void deleteClientFromDatabase() throws SQLException, InterruptedException, IOException {
        // Given
        String requestOrder = "DELETE_CLIENT";
        int id_client = 1;

        // When
        DeleteByClient.deleteValue(requestOrder, id_client);

        // Then
        Clients clients = SelectClient.getValue("SELECT_ALL_CLIENTS");
        boolean res = true;
        for (Client client : clients.getClients()) {
            if (client.getId_client() == 1) {
                res = false;
            }
        }
        assertTrue(res);
    }

    @Test
    public void deleteRecipeFromDatabase() throws SQLException, InterruptedException, IOException {
        // Given
        String requestOrder = "DELETE_RECETTE";
        int id_recette = 1;

        // When
        DeleteByClient.deleteValue(requestOrder, id_recette);

        // Then
        Recettes recettes = SelectRecipe.getValue("SELECT_ALL_RECETTES", null);
        boolean res = true;
        for (Recette recette : recettes.getRecettes()) {
            if (recette.getId_recette() == 1) {
                res = false;
            }
        }
        assertTrue(res);
    }

    @Test
    public void deleteInformationFromDatabase() throws SQLException, InterruptedException, IOException {
        // Given
        String requestOrder = "DELETE_INFORMATION";
        int id_info = 1;

        // When
        DeleteByClient.deleteValue(requestOrder, id_info);

        // Then
        Informations informations = SelectInformation.getValue("SELECT_ALL_INFORMATIONS");
        boolean res = true;
        for (Information information: informations.getInformations()) {
            if (information.getId_info() == 1) {
                res = false;
            }
        }
        assertTrue(res);
    }

    @Test
    public void deleteNutritionnisteFromDatabase() throws SQLException, InterruptedException, IOException {
        // Given
        String requestOrder = "DELETE_NUTRITIONNISTE";
        int id_nutritionniste = 1;

        // When
        DeleteByClient.deleteValue(requestOrder, id_nutritionniste);

        // Then
        Nutritionnistes nutritionnistes = SelectNutritionist.getValue("SELECT_ALL_NUTRITIONNISTES");
        boolean res = true;
        for (Nutritionniste nutritionniste : nutritionnistes.getNutritionnistes()) {
            if (nutritionniste.getId_nutritionniste() == 1) {
                res = false;
            }
        }
        assertTrue(res);
    }
}
