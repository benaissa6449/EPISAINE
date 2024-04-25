package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.business.dto.Information;
import edu.ezip.ing1.pds.business.dto.Informations;
import edu.ezip.ing1.pds.client.SelectInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;

public class NutritionistInformationController extends NutritionistHeadController {
    @FXML
    private Button selectButton;

    @FXML
    private TableView<Information> informationTableView;

    @FXML
    private TableColumn<Information, Integer> idInformationColumn, idClientColumn, repasColumn ;

    @FXML
    private  TableColumn<Information, String> butColumn, allergieColumn;

    public void selectInformationData(ActionEvent actionEvent) {
        try {
            Informations informations = SelectInformation.getValue("SELECT_ALL_INFORMATIONS");
            idInformationColumn.setCellValueFactory(new PropertyValueFactory<>("Id_information"));
            idClientColumn.setCellValueFactory(new PropertyValueFactory<>("Id_client"));
            repasColumn.setCellValueFactory(new PropertyValueFactory<>("NbDeRepas"));
            butColumn.setCellValueFactory(new PropertyValueFactory<>("But"));
            allergieColumn.setCellValueFactory(new PropertyValueFactory<>("Allergie"));

            informationTableView.getItems().clear();

            for (Information information : informations.getInformations()) {
                informationTableView.getItems().add(information);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
