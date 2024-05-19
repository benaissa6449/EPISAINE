import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.ezip.ing1.pds.business.dto.Recette;
import edu.ezip.ing1.pds.business.dto.Recettes;
import edu.ezip.ing1.pds.client.SelectRecipe;

public class SelectRecipesTest {
    @Test
    public void assertSelectRecipesAboveKCal(){
        // Given
        String requestOrder = "SELECT_SPECIFIC_RECIPES_NOT_BELOW";
        String kcal = "1500";

        // When
        Recettes recettes = SelectRecipe.getValue(requestOrder, kcal);

        // Then
        for (Recette recette : recettes.getRecettes()) {
            assertTrue(recette.getNombre_de_calories() > Integer.valueOf(kcal));
        }
    }

    @Test
    public void assertSelectRecipesBelowCal(){
        // Given
        String requestOrder = "SELECT_SPECIFIC_RECIPES_NOT_ABOVE";
        String kcal = "1500";

        // When
        Recettes recettes = SelectRecipe.getValue(requestOrder, kcal);

        // Then
        for (Recette recette : recettes.getRecettes()) {
            assertTrue(recette.getNombre_de_calories() < Integer.valueOf(kcal));
        }
    }

    @Test
    public void assertSelectRecipesBetweenKCals(){
        // Given
        String requestOrder = "SELECT_SPECIFIC_RECIPES_BETWEEN";
        String valInf = "1000";
        String valSup = "2500";
        String requestCondition = "\"" + valInf + "," + valSup + "\"";

        // When
        Recettes recettes = SelectRecipe.getValue(requestOrder,requestCondition);

        // Then
        for (Recette recette : recettes.getRecettes()) {
            assertTrue(recette.getNombre_de_calories() > Integer.valueOf(valInf) && recette.getNombre_de_calories() < Integer.valueOf(valSup));
        }
    }
}
