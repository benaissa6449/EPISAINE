import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import edu.ezip.ing1.pds.business.dto.Update;
import edu.ezip.ing1.pds.client.SelectInformation;
import edu.ezip.ing1.pds.client.SelectNutritionist;
import edu.ezip.ing1.pds.client.SelectRecipe;
import edu.ezip.ing1.pds.client.SelectSpecificClient;
import edu.ezip.ing1.pds.client.UpdateByClient;

public class UpdateTableValueTest {
    
    @Test
    public void updateClientValueInDatabase() throws IOException, SQLException, InterruptedException {
        // Given
        String requestOrder = "UPDATE_CLIENT";
        String newValue = "NomTestUnitaire";
        Update update = new Update();
        update.setConditionColumn("id_client");
        update.setConditionValue("2");
        update.setNewColumn("nom_client");
        update.setNewValue(newValue);

        // When
        UpdateByClient.updateValue(requestOrder, update);

        // Then
        Client client = SelectSpecificClient.getValue("SELECT_SPECIFIC_CLIENT", "2");
        assertTrue(client.getNom_client().equals(newValue));
    }

    @Test
    public void updateNutritionistValueInDatabase() throws IOException, SQLException, InterruptedException {
        // Given
        String requestOrder = "UPDATE_NUTRITIONNISTE";
        String newValue = "NomTestUnitaire";
        Update update = new Update();
        update.setConditionColumn("id_nutritionniste");
        update.setConditionValue("2");
        update.setNewColumn("nom_n");
        update.setNewValue(newValue);

        // When
        UpdateByClient.updateValue(requestOrder, update);

        // Then
        Nutritionnistes nutritionnistes = SelectNutritionist.getValue("SELECT_ALL_NUTRITIONNISTES");
        for (Nutritionniste nutritionniste : nutritionnistes.getNutritionnistes()) {
            if (nutritionniste.getId_nutritionniste() == 2) {
                assertTrue(nutritionniste.getNom_N().equals(newValue));
                break;
            }
        }
    }

    @Test
    public void updateInformationValueInDatabase() throws IOException, SQLException, InterruptedException {
        // Given
        String requestOrder = "UPDATE_INFORMATION";
        String newValue = "perte de poids";
        Update update = new Update();
        update.setConditionColumn("id_info");
        update.setConditionValue("2");
        update.setNewColumn("but");
        update.setNewValue(newValue);

        // When
        UpdateByClient.updateValue(requestOrder, update);

        // Then
        Informations informations = SelectInformation.getValue("SELECT_ALL_INFORMATIONS");
        for (Information information : informations.getInformations()) {
            if (information.getId_info() == 2) {
                assertTrue(information.getBut().equals(newValue));
                break;
            }
        }
    }

    @Test
    public void updateRecipeValueInDatabase() throws IOException, SQLException, InterruptedException {
        // Given
        String requestOrder = "UPDATE_RECETTE";
        String newValue = "NomTestUnitaire";
        Update update = new Update();
        update.setConditionColumn("id_recette");
        update.setConditionValue("2");
        update.setNewColumn("nom_recette");
        update.setNewValue(newValue);

        // When
        UpdateByClient.updateValue(requestOrder, update);

        // Then
        Recettes recettes = SelectRecipe.getValue("SELECT_ALL_RECETTES", null);
        for (Recette recette : recettes.getRecettes()) {
            if (recette.getId_recette() == 2) {
                assertTrue(recette.getNom_recette().equals(newValue));
            }
        }
    }

}
