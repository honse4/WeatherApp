
package fi.tuni.prog3.weatherapp.components.sideview;

import fi.tuni.prog3.weatherapp.WeatherApp;
import fi.tuni.prog3.weatherapp.apigson.forecast.HourlyForecastData;
import fi.tuni.prog3.weatherapp.components.supplement.BackButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ville
 */
public class ForecastChart extends VBox{
    private final HourlyForecastData data;
    private final Stage stage;
    private final Scene scene;
    private final WeatherApp main;
    private final CategoryAxis xAxis;  // x-axis for time
    private final NumberAxis yAxis; // y-axis for temperatures
    private LineChart<String,Number> lineChart;
    
    public ForecastChart(Stage stage, Scene scene, WeatherApp main, HourlyForecastData data, String unit) {
        this.stage = stage;
        this.scene = scene;
        this.main = main;
        this.data = data;
        
        xAxis = new CategoryAxis();
        xAxis.setLabel("Time");
        yAxis = new NumberAxis();
        String tempUnit = ("metric".equals(unit)) ? "celsius" : "fahrenheit";
        yAxis.setLabel(tempUnit);
        
        lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.setTitle("Temperatures for following 24 hours");
        
        XYChart.Series series = new XYChart.Series();
        series.setName("Temperatures");
        
        if (data != null) {
            for (var forecastdata : this.data.getList()) {
                series.getData().add(new XYChart.Data(forecastdata.getDt_txt(), 
                                                  forecastdata.getMain().getTemp()));
            }
        }

        lineChart.getData().add(series);
        
        BackButton back = new BackButton();
        back.setOnAction(e -> {
            stage.setScene(scene);
            scene.getRoot().requestFocus();    
        });

        getChildren().addAll(back,lineChart);
    }  
}
