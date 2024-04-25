package edu.ezip.ing1.pds.demo194;

import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.business.dto.Clients;
import edu.ezip.ing1.pds.business.dto.Nutritionniste;
import edu.ezip.ing1.pds.business.dto.Nutritionnistes;
import edu.ezip.ing1.pds.client.SelectClient;
import edu.ezip.ing1.pds.client.SelectNutritionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientNutritionistController extends ClientHeadController {
    @FXML
    private Button selectButton;
    @FXML
    private TableView<Nutritionniste> nutritionistTableView;
    @FXML
    private TableColumn<Nutritionniste, Integer> idNutritionistColumn;
    @FXML
    private TableColumn<Nutritionniste, String> prenomColumn, nomColumn, telephoneColumn, mailColumn;

    @FXML
    private void selectNutritionistData(ActionEvent actionEvent) {
        try {
            Nutritionnistes nutritionnistes = SelectNutritionist.getValue("SELECT_ALL_NUTRITIONNISTES");

            idNutritionistColumn.setCellValueFactory(new PropertyValueFactory<>("Id_nutritionniste"));
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("Nom_N"));
            prenomColumn.setCellValueFactory(new PropertyValueFactory<>("Prenom_N"));
            telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("Numero_de_telephone_N"));
            mailColumn.setCellValueFactory(new PropertyValueFactory<>("Mail_N"));

            nutritionistTableView.getItems().clear();

            for (Nutritionniste nutritionniste : nutritionnistes.getNutritionnistes()) {
                nutritionistTableView.getItems().add(nutritionniste);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
