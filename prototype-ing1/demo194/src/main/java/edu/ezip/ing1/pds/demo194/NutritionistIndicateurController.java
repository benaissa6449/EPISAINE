package edu.ezip.ing1.pds.demo194;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class NutritionistIndicateurController extends NutritionistHeadController implements Initializable {

    @FXML
    private LineChart<?, ?> lineChart;

    @Override // Ajout de l'annotation @FXML
    public void initialize(URL location, ResourceBundle resources) {
        initLineChart();
    }

    private void initLineChart() {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("[Jeunes]", 10));
        series.getData().add(new XYChart.Data("[AZD]", 20));
        series.getData().add(new XYChart.Data("[JeuZARnes]", 30));
        series.getData().add(new XYChart.Data("[JeuZRnes]", 40));
        series.getData().add(new XYChart.Data("[JeuZRAnes]", 50));
        series.getData().add(new XYChart.Data("[JeunAZRes]", 60));
        lineChart.getData().addAll(series);
    }
}
