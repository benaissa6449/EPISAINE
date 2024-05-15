package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.business.dto.*;
import edu.ezip.ing1.pds.client.SelectNutritionist;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ClientNutritionistController extends ClientHeadController {
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Nutritionniste> nutritionistTableView;
    @FXML
    private TableColumn<Nutritionniste, Integer> idNutritionistColumn;
    @FXML
    private TableColumn<Nutritionniste, String> prenomColumn, nomColumn, telephoneColumn, mailColumn;

    @FXML
    private void selectNutritionistData(ActionEvent actionEvent) {
        // this method select the nutritionists from the database and display them on the panel
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            nutritionistTableView.getSelectionModel().setCellSelectionEnabled(true);
            nutritionistTableView.setOnMouseClicked((MouseEvent event) -> {
                TablePosition tablePosition = nutritionistTableView.getSelectionModel().getSelectedCells().getFirst();
                int row = tablePosition.getRow();

                Nutritionniste nutritionniste = nutritionistTableView.getItems().get(row);
                TableColumn tableColumn = tablePosition.getTableColumn();

                String data = tableColumn.getCellObservableValue(nutritionniste).getValue().toString();

                alert.setHeaderText(tableColumn.getText() + " : " + data);
                alert.showAndWait();
            });

            Nutritionnistes nutritionnistes = SelectNutritionist.getValue("SELECT_ALL_NUTRITIONNISTES");

            idNutritionistColumn.setCellValueFactory(new PropertyValueFactory<>("Id_nutritionniste"));
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("Nom_N"));
            prenomColumn.setCellValueFactory(new PropertyValueFactory<>("Prenom_N"));
            telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("Numero_de_telephone_N"));
            mailColumn.setCellValueFactory(new PropertyValueFactory<>("Mail_N"));

            nutritionistTableView.getItems().clear();

            // insert every value from the database into the tableview object
            for (Nutritionniste nutritionniste : nutritionnistes.getNutritionnistes()) {
                nutritionistTableView.getItems().add(nutritionniste);
            }

            // this part is used to filter the table with the search bar
            FilteredList<Nutritionniste> filteredList = new FilteredList<>(nutritionistTableView.getItems(), p->true);

            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(nutritionniste -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (String.valueOf(nutritionniste.getId_nutritionniste()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(nutritionniste.getNom_N()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(nutritionniste.getPrenom_N()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(nutritionniste.getMail_N()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(nutritionniste.getNumero_de_telephone_N()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else return false;
                });
            });

            SortedList<Nutritionniste> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(nutritionistTableView.comparatorProperty());
            nutritionistTableView.setItems(sortedList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
