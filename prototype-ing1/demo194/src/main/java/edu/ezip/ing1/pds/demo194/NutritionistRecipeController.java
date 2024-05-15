package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.business.dto.Information;
import edu.ezip.ing1.pds.business.dto.Recette;
import edu.ezip.ing1.pds.business.dto.Recettes;
import edu.ezip.ing1.pds.business.dto.Update;
import edu.ezip.ing1.pds.client.DeleteByClient;
import edu.ezip.ing1.pds.client.InsertByClient;
import edu.ezip.ing1.pds.client.SelectRecipe;
import edu.ezip.ing1.pds.client.UpdateByClient;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.CheckComboBox;

import java.util.List;
import java.util.Optional;

public class NutritionistRecipeController extends NutritionistHeadController {
    @FXML
    private TextField nomTextField, calorieTextField, instructionsTextField, ingredientsTextField, idNutritionistTextField;
    @FXML
    private ComboBox<String> regimeComboBox;
    @FXML
    private TableView<Recette> recipeTableView;
    @FXML
    private TableColumn<Recette, Integer> idRecetteColumn, idNutritionistColumn, caloriesColumn;
    @FXML
    private TableColumn<Recette, String> nomColumn, ingredientsColumn, instructionsColumn, regimeColumn;
    @FXML
    private TextField searchTextField;

    public void insertRecipeData(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            String nom = nomTextField.getText().trim();
            Integer calorie = Integer.parseInt(calorieTextField.getText().trim());
            String instructions = instructionsTextField.getText().trim();
            String ingredients = ingredientsTextField.getText().trim();
            String regime = regimeComboBox.getValue().trim();
            Integer idNutritionist = Integer.parseInt(idNutritionistTextField.getText().trim());

            insertRecipeIntoTable(nom, calorie, instructions, ingredients, regime, idNutritionist);
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

    public void insertRecipeIntoTable(String nom, Integer calorie, String instructions, String ingredients, String regime, Integer idNutritionist) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            if (assertNotNull(nom, instructions, ingredients, regime)) {
                try {
                    Recette recette = new Recette(-1, idNutritionist, nom, calorie, ingredients, instructions, regime);
                    InsertByClient.sendValue("INSERT_RECETTE", recette);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Insertion effectuée.");
                } catch (Exception e) {
                    alert.setHeaderText("Erreur système !\nVeuillez contacter un administrateur.");
                    alert.showAndWait();
                }
            }
        }
        catch (NullPointerException npe) {
            alert.setHeaderText("Les champs ne doivent pas être vides.");
            alert.showAndWait();
        }
        catch (NumberFormatException nfe) {
            alert.setHeaderText("Ne correspond pas à une valeur numérique");
            alert.showAndWait();
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

    public void cleanFields(ActionEvent actionEvent) {
        idNutritionistTextField.clear();
        nomTextField.clear();
        calorieTextField.clear();
        instructionsTextField.clear();
        ingredientsTextField.clear();
        regimeComboBox.getSelectionModel().clearSelection();
    }

    public void selectRecipeData(ActionEvent actionEvent) {
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

            Recettes recettes = SelectRecipe.getValue("SELECT_ALL_RECETTES");
            idRecetteColumn.setCellValueFactory(new PropertyValueFactory<>("Id_recette"));
            idNutritionistColumn.setCellValueFactory(new PropertyValueFactory<>("Id_nutritionniste"));
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("Nom_recette"));
            caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre_de_calories"));
            ingredientsColumn.setCellValueFactory(new PropertyValueFactory<>("Ingredients"));
            instructionsColumn.setCellValueFactory(new PropertyValueFactory<>("Instructions"));
            regimeColumn.setCellValueFactory(new PropertyValueFactory<>("RegimeAlimentaire"));


            for (Recette recette : recettes.getRecettes()) {
                recipeTableView.getItems().add(recette);
            }

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
            e.printStackTrace();
        }
    }

    public void updateValue(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        TablePosition tablePosition = recipeTableView.getSelectionModel().getSelectedCells().getFirst();

        int row = tablePosition.getRow();

        Recette recette = recipeTableView.getItems().get(row);

        TableColumn tableColumn = tablePosition.getTableColumn();

        String data = tableColumn.getCellObservableValue(recette).getValue().toString();

        TextInputDialog textInputDialog = new TextInputDialog("Nouvelle valeur :");
        textInputDialog.setHeaderText("Ancienne valeur : " + data);
        textInputDialog.setTitle("Modifier");
        Optional<String> result = textInputDialog.showAndWait();

        if (result.isPresent()) {
            String columnName = tableColumn.getText();

            Update update;
            try {
                switch (columnName) {
                    case "Nom de Recette":
                        update = new Update();
                        update.setNewColumn("nom_recette");
                        update.setNewValue(textInputDialog.getEditor().getText());
                        update.setConditionColumn("id_recette");
                        update.setConditionValue(String.valueOf(recette.getId_recette()));
                        UpdateByClient.updateValue("UPDATE_RECETTE", update);
                        alert.setHeaderText("Modification effectuée.");
                        alert.showAndWait();
                        break;
                    case "Nombre de calories":
                        update = new Update();
                        update.setNewColumn("nombre_de_calories");
                        update.setNewValue(textInputDialog.getEditor().getText());
                        update.setConditionColumn("id_recette");
                        update.setConditionValue(String.valueOf(recette.getId_recette()));
                        UpdateByClient.updateValue("UPDATE_RECETTE", update);
                        alert.setHeaderText("Modification effectuée.");
                        alert.showAndWait();
                        break;
                    case "Ingrédients":
                        update = new Update();
                        update.setNewColumn("ingredients");
                        update.setNewValue(textInputDialog.getEditor().getText());
                        update.setConditionColumn("id_recette");
                        update.setConditionValue(String.valueOf(recette.getId_recette()));
                        UpdateByClient.updateValue("UPDATE_RECETTE", update);
                        alert.setHeaderText("Modification effectuée.");
                        alert.showAndWait();
                        break;
                    case "Instructions":
                        update = new Update();
                        update.setNewColumn("instructions");
                        update.setNewValue(textInputDialog.getEditor().getText());
                        update.setConditionColumn("id_recette");
                        update.setConditionValue(String.valueOf(recette.getId_recette()));
                        UpdateByClient.updateValue("UPDATE_RECETTE", update);
                        alert.setHeaderText("Modification effectuée.");
                        alert.showAndWait();
                        break;
                    case "Régime alimentaire":
                        update = new Update();
                        update.setNewColumn("regimealimentaire");
                        update.setNewValue(textInputDialog.getEditor().getText());
                        update.setConditionColumn("id_recette");
                        update.setConditionValue(String.valueOf(recette.getId_recette()));
                        UpdateByClient.updateValue("UPDATE_RECETTE", update);
                        alert.setHeaderText("Modification effectuée.");
                        alert.showAndWait();
                        break;
                    case "ID Nutritionniste":
                        update = new Update();
                        update.setNewColumn("id_nutritionniste");
                        update.setNewValue(textInputDialog.getEditor().getText());
                        update.setConditionColumn("id_recette");
                        update.setConditionValue(String.valueOf(recette.getId_recette()));
                        UpdateByClient.updateValue("UPDATE_RECETTE", update);
                        alert.setHeaderText("Modification effectuée.");
                        alert.showAndWait();
                        break;
                    default:
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setHeaderText("Valeur non modifiable.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("...");
            }
        }
    }

    public void deleteValue(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        TablePosition tablePosition = recipeTableView.getSelectionModel().getSelectedCells().getFirst();

        int row = tablePosition.getRow();

        Recette recette = recipeTableView.getItems().get(row);

        TableColumn tableColumn = tablePosition.getTableColumn();

        String data = tableColumn.getCellObservableValue(recette).getValue().toString();

        alert.setHeaderText("Etes-vous sûr de vouloir supprimer cette recette ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                DeleteByClient.deleteValue("DELETE_RECETTE", recette.getId_recette());
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Suppression effectuée.");
                alert.showAndWait();
            }
            catch (Exception e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText("Erreur de suppression.");
                alert.showAndWait();
            }
        }
    }
}
