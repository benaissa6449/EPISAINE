package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.business.dto.Information;
import edu.ezip.ing1.pds.business.dto.Informations;
import edu.ezip.ing1.pds.business.dto.Nutritionniste;
import edu.ezip.ing1.pds.client.SelectInformation;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class NutritionistInformationController extends NutritionistHeadController implements Initializable {
    @FXML
    private Button selectButton;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Information> informationTableView;
    @FXML
    private TableColumn<Information, Integer> idInformationColumn, idClientColumn, repasColumn ;
    @FXML
    private  TableColumn<Information, String> butColumn, allergieColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        informationTableView.getSelectionModel().setCellSelectionEnabled(true);
        informationTableView.setOnMouseClicked((MouseEvent event) -> {
            TablePosition tablePosition = informationTableView.getSelectionModel().getSelectedCells().getFirst();
            int row = tablePosition.getRow();

            Information information = informationTableView.getItems().get(row);
            TableColumn tableColumn = tablePosition.getTableColumn();

            String data = tableColumn.getCellObservableValue(information).getValue().toString();

            alert.setHeaderText(data);
            alert.showAndWait();
        });
    }

    public void selectInformationData(ActionEvent actionEvent) {
        try {
            Informations informations = SelectInformation.getValue("SELECT_ALL_INFORMATIONS");
            idInformationColumn.setCellValueFactory(new PropertyValueFactory<>("Id_info"));
            idClientColumn.setCellValueFactory(new PropertyValueFactory<>("Id_Client"));
            repasColumn.setCellValueFactory(new PropertyValueFactory<>("NbDeRepas"));
            butColumn.setCellValueFactory(new PropertyValueFactory<>("But"));
            allergieColumn.setCellValueFactory(new PropertyValueFactory<>("Allergie"));

            informationTableView.getItems().clear();

            for (Information information : informations.getInformations()) {
                informationTableView.getItems().add(information);
            }

            FilteredList<Information> filteredList = new FilteredList<>(informationTableView.getItems(), p->true);

            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(information -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (String.valueOf(information.getAllergie()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(information.getBut()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(information.getId_Client()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(information.getId_info()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (String.valueOf(information.getNbDeRepas()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else return false;
                });
            });

            SortedList<Information> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(informationTableView.comparatorProperty());
            informationTableView.setItems(sortedList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
