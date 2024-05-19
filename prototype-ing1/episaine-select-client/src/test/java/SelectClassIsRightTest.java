import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.ezip.ing1.pds.business.dto.Clients;
import edu.ezip.ing1.pds.business.dto.Informations;
import edu.ezip.ing1.pds.business.dto.Nutritionnistes;
import edu.ezip.ing1.pds.business.dto.Recettes;
import edu.ezip.ing1.pds.client.SelectClient;
import edu.ezip.ing1.pds.client.SelectInformation;
import edu.ezip.ing1.pds.client.SelectNutritionist;
import edu.ezip.ing1.pds.client.SelectRecipe;

public class SelectClassIsRightTest {
    
    @Test
    public void selectClientHasClassClients() {
        // Given
        String requestOrder = "SELECT_ALL_CLIENTS";

        // When
        Clients clients = SelectClient.getValue(requestOrder);

        // Then
        assertTrue(clients instanceof Clients);
    }

    @Test
    public void selectInformationHasClassInformaitons() {
        // Given
        String requestOrder = "SELECT_ALL_INFORMATIONS";

        // When
        Informations informations = SelectInformation.getValue(requestOrder);

        // Then
        assertTrue(informations instanceof Informations);
    }

    @Test
    public void selectRecipeHasClassRecipes() {
        // Given
        String requestOrder = "SELECT_ALL_RECETTES";

        // When
        Recettes recettes = SelectRecipe.getValue(requestOrder, null);

        // Then
        assertTrue(recettes instanceof Recettes);
    }

    @Test
    public void selectNutritionistHasClassNutritionists() {
        // Given
        String requestOrder = "SELECT_ALL_NUTRITIONNISTES";

        // When
        Nutritionnistes nutritionnistes = SelectNutritionist.getValue(requestOrder);

        // Then
        assertTrue(nutritionnistes instanceof Nutritionnistes);
    }
}
