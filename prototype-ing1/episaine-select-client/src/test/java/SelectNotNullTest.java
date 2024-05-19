import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.ezip.ing1.pds.business.dto.Clients;
import edu.ezip.ing1.pds.business.dto.Informations;
import edu.ezip.ing1.pds.business.dto.Nutritionnistes;
import edu.ezip.ing1.pds.business.dto.Recettes;
import edu.ezip.ing1.pds.client.SelectClient;
import edu.ezip.ing1.pds.client.SelectInformation;
import edu.ezip.ing1.pds.client.SelectNutritionist;
import edu.ezip.ing1.pds.client.SelectRecipe;

public class SelectNotNullTest {
    
    @Test
    public void selectClientIsNotNull() {
        // Given
        String requestOrder = "SELECT_ALL_CLIENTS";

        // When
        Clients clients = SelectClient.getValue(requestOrder);
        
        // Then
        assertNotNull(clients);
    }

    @Test
    public void selectInformationIsNotNull() {
        // Given
        String requestOrder = "SELECT_ALL_INFORMATIONS";

        // When
        Informations informations = SelectInformation.getValue(requestOrder);
        
        // Then
        assertNotNull(informations);
    }

    @Test
    public void selectNutritionistIsNotNull() {
        // Given
        String requestOrder = "SELECT_ALL_NUTRITIONNISTES";

        // When
        Nutritionnistes nutritionnistes = SelectNutritionist.getValue(requestOrder);
        
        // Then
        assertNotNull(nutritionnistes);
    }

    @Test
    public void selectRecipeIsNotNull() {
        // Given
        String requestOrder = "SELECT_ALL_RECETTES";

        // When
        Recettes recettes = SelectRecipe.getValue(requestOrder, null);
        
        // Then
        assertNotNull(recettes);
    }
}
