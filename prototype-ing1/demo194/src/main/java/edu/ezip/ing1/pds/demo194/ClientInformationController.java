package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.business.dto.Information;
import edu.ezip.ing1.pds.business.dto.Recette;
import edu.ezip.ing1.pds.business.dto.Recettes;
import edu.ezip.ing1.pds.client.InsertByClient;
import edu.ezip.ing1.pds.client.SelectRecipe;
import edu.ezip.ing1.pds.client.SelectSpecificClient;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.CheckComboBox;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Year;
import java.util.Calendar;
import java.util.List;

public class ClientInformationController extends ClientHeadController {
    // this class is related to insert the client's information
    @FXML
    private TextField nbDeRepasTextField, idTextField;
    @FXML
    private ComboBox<String> butComboBox, regimeComboBox;
    @FXML
    private CheckComboBox<String> allergieCheckComboBox;
    @FXML
    private TableView<Recette> recipeTableView;
    @FXML
    private TableColumn<Recette, Integer> idRecetteColumn, idNutritionistColumn, caloriesColumn;
    @FXML
    private TableColumn<Recette, String> nomColumn, ingredientsColumn, instructionsColumn, regimeColumn;
    @FXML
    private TextField searchTextField;

    // this method check if every field content is correct, then call the insert method
    public void insertInformationData(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            Integer nbDeRepas = Integer.parseInt(nbDeRepasTextField.getText().trim());
            Integer idClient = Integer.parseInt(idTextField.getText().trim());
            String but = butComboBox.getValue().trim();
            List<String> allergies = allergieCheckComboBox.getCheckModel().getCheckedItems();

            insertInformationIntoTable(nbDeRepas, idClient, but, allergies);
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

    // this method insert the information into the sql table
    public void insertInformationIntoTable(Integer nbDeRepas, Integer idClient, String but, List<String> allergies) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            Information information = new Information(-1, idClient, but, allergies.toString(), nbDeRepas);
            InsertByClient.sendValue("INSERT_INFORMATION", information);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Insertion effectuée.");
        }
        catch (NullPointerException npe) {
            alert.setHeaderText("Les champs ne doivent pas être vides.");
            alert.showAndWait();
        }
        catch (NumberFormatException nfe) {
            alert.setHeaderText("Ne correspond pas à une valeur numérique");
            alert.showAndWait();
        }
        catch (Exception e) {
            alert.setHeaderText("Erreur système !\nVeuillez contacter un administrateur.");
            alert.showAndWait();
        }
    }

    // empty every field
    public void cleanFields(ActionEvent actionEvent) {
        nbDeRepasTextField.clear();
        idTextField.clear();
        allergieCheckComboBox.getCheckModel().clearChecks();
        butComboBox.getSelectionModel().clearSelection();
        regimeComboBox.getSelectionModel().clearSelection();
    }

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

            String id = idTextField.getText();
            Recettes recettes = new Recettes();
            if (!id.isEmpty()) {
                Client client = SelectSpecificClient.getValue("SELECT_SPECIFIC_CLIENT", id);

                String genre = client.getGenre();
                Date date = client.getDate_de_naissance_client();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int naissance = calendar.get(Calendar.YEAR);
                BigDecimal poids = client.getPoids();
                Integer taille = client.getTaille();

                // calories formula found here https://www.ericfavre.com/lifestyle/tableau-depenses-des-calories/
                int age = Year.now().getValue() - naissance;
                int kcal;
                if (genre.toLowerCase().equals("homme")) {
                    kcal = (int) (poids.intValue() * 10 + taille * 6.25 - age * 5 + 5);
                }
                else {
                    kcal = (int) (poids.intValue() * 10 + taille * 6.25 - age * 5 - 161);
                }

                switch (butComboBox.getValue()) {
                    case "gain de poids" :
                        int valSup = (kcal + 500);
                        recettes = SelectRecipe.getValue("SELECT_SPECIFIC_RECIPES_NOT_BELOW", String.valueOf(valSup));
                        break;
                    case "perte de poids" :
                        int valInf = (kcal - 500);
                        recettes = SelectRecipe.getValue("SELECT_SPECIFIC_RECIPES_NOT_ABOVE", String.valueOf(valInf));
                        break;
                    case "maintien de poids":
                        String valMin = String.valueOf(kcal - 1000);
                        String valMax = String.valueOf(kcal + 1000);
                        recettes = SelectRecipe.getValue("SELECT_SPECIFIC_RECIPES_BETWEEN", "\"" + valMin + "," + valMax + "\"");
                        break;
                    default:
                        recettes = SelectRecipe.getValue("SELECT_ALL_RECETTES", null);
                        break;
                }
            }
            else {
                recettes = SelectRecipe.getValue("SELECT_ALL_RECETTES", null);
            }

            idRecetteColumn.setCellValueFactory(new PropertyValueFactory<>("Id_recette"));
            idNutritionistColumn.setCellValueFactory(new PropertyValueFactory<>("Id_nutritionniste"));
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("Nom_recette"));
            caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre_de_calories"));
            ingredientsColumn.setCellValueFactory(new PropertyValueFactory<>("Ingredients"));
            instructionsColumn.setCellValueFactory(new PropertyValueFactory<>("Instructions"));
            regimeColumn.setCellValueFactory(new PropertyValueFactory<>("RegimeAlimentaire"));

            // display every recipe on the tableview
            for (Recette recette : recettes.getRecettes()) {
                ObservableList<String> allergies = allergieCheckComboBox.getCheckModel().getCheckedItems();
                Boolean isSafe = true;

                String regime = regimeComboBox.getValue();

                try {
                    if (!regime.isEmpty()) {
                        if (!recette.getRegimeAlimentaire().equals(regime)) {
                            isSafe = false;
                        }
                    }
                }
                catch (Exception e) {
                    isSafe = true;
                }


                for (String allergie : allergies) {
                    if (recette.getIngredients().toLowerCase().contains(allergie.toLowerCase())) {
                        isSafe = false;
                        break;
                    }
                }

                if (isSafe) {
                    recipeTableView.getItems().add(recette);
                }
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