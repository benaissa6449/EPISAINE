package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.business.dto.Recette;
import edu.ezip.ing1.pds.business.dto.Recettes;
import edu.ezip.ing1.pds.client.SelectRecipe;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ClientRecipeController extends ClientHeadController {
    @FXML
    private TableView<Recette> recipeTableView;
    @FXML
    private TableColumn<Recette, Integer> idRecetteColumn, idNutritionistColumn, caloriesColumn;
    @FXML
    private TableColumn<Recette, String> nomColumn, ingredientsColumn, instructionsColumn, regimeColumn;
    @FXML
    private TextField searchTextField;

    public void selectRecipeData(ActionEvent actionEvent) {
        // this method select the recipes from the database and display them on the panel
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            recipeTableView.getSelectionModel().setCellSelectionEnabled(true);
            recipeTableView.setOnMouseClicked((MouseEvent event) -> {
                TablePosition tablePosition = recipeTableView.getSelectionModel().getSelectedCells().getFirst();
                int row = tablePosition.getRow();

                Recette recette = recipeTableView.getItems().get(row);
                TableColumn tableColumn = tablePosition.getTableColumn();

                String data = tableColumn.getCellObservableValue(recette).getValue().toString();

                alert.setHeaderText(tableColumn.getText() + " : " + data);
                alert.showAndWait();
            });

            recipeTableView.getItems().clear();

            Recettes recettes = SelectRecipe.getValue("SELECT_ALL_RECETTES", null);
            idRecetteColumn.setCellValueFactory(new PropertyValueFactory<>("Id_recette"));
            idNutritionistColumn.setCellValueFactory(new PropertyValueFactory<>("Id_nutritionniste"));
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("Nom_recette"));
            caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre_de_calories"));
            ingredientsColumn.setCellValueFactory(new PropertyValueFactory<>("Ingredients"));
            instructionsColumn.setCellValueFactory(new PropertyValueFactory<>("Instructions"));
            regimeColumn.setCellValueFactory(new PropertyValueFactory<>("RegimeAlimentaire"));

            // display every recipe on the tableview
            for (Recette recette : recettes.getRecettes()) {
                recipeTableView.getItems().add(recette);
            }

            // this part is used to filter the tableview with the search bar
            FilteredList<Recette> filteredList = new FilteredList<>(recipeTableView.getItems(), p->true);

            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(recette -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (String.valueOf(recette.getId_recette()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(recette.getId_nutritionniste()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(recette.getIngredients()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(recette.getNom_recette()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(recette.getInstructions()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(recette.getNombre_de_calories()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(recette.getRegimeAlimentaire()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else return false;
                });
            });

            SortedList<Recette> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(recipeTableView.comparatorProperty());
            recipeTableView.setItems(sortedList);
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez raffraichir la page.");
            alert.showAndWait();
        }
    }
}
