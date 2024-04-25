package edu.ezip.ing1.pds.demo194;


import edu.ezip.ing1.pds.business.dto.Recette;
import edu.ezip.ing1.pds.business.dto.Recettes;
import edu.ezip.ing1.pds.client.SelectRecipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;

public class ClientRecipeController extends ClientHeadController {
    @FXML
    private Button selectButton;

    @FXML
    private TableView<Recette> recipeTableView;

    @FXML
    private TableColumn<Recette, Integer> idRecetteColumn, idNutritionistColumn, caloriesColumn;

    @FXML
    private  TableColumn<Recette, String> nomColumn, ingredientsColumn, instructionsColumn, regimeColumn;

    public void selectRecipeData(ActionEvent actionEvent) {
        try {
            Recettes recettes = SelectRecipe.getValue("SELECT_ALL_RECETTES");
            idRecetteColumn.setCellValueFactory(new PropertyValueFactory<>("Id_recette"));
            idNutritionistColumn.setCellValueFactory(new PropertyValueFactory<>("Id_nutritionniste"));
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("Nom_recette"));
            caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre_de_calories"));
            ingredientsColumn.setCellValueFactory(new PropertyValueFactory<>("Ingredients"));
            instructionsColumn.setCellValueFactory(new PropertyValueFactory<>("Instructions"));
            regimeColumn.setCellValueFactory(new PropertyValueFactory<>("RegimeAlimentaire"));

            recipeTableView.getItems().clear();

            for (Recette recette : recettes.getRecettes()) {
                recipeTableView.getItems().add(recette);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
