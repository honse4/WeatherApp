
package fi.tuni.prog3.weatherapp.components;

import fi.tuni.prog3.weatherapp.WeatherApp;
import fi.tuni.prog3.weatherapp.apigson.forecast.HourlyForecastData;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * @author Ville
 */
public class ForecastChart {
    private final HourlyForecastData data;
    private final Stage stage;
    private final WeatherApp main;
    private final CategoryAxis xAxis;  // x-axis for time
    private final NumberAxis yAxis; // y-axis for temperature
    private final Scene chartScene;
    
    private LineChart<String,Number> lineChart;
    
    public ForecastChart(HourlyForecastData data, Stage stage, WeatherApp main) {
        this.data = data;
        this.stage = stage;
        this.main = main;
        
        xAxis = new CategoryAxis();
        xAxis.setLabel("Time");
        yAxis = new NumberAxis();
        
        lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.setTitle("Temperatures for 3 days");
        
        XYChart.Series series = new XYChart.Series();
        series.setName("Temperatures");
        
        for (var forecastdata : data.getList()) {
            series.getData().add(new XYChart.Data(forecastdata.getDt_txt(), 
                                                  forecastdata.getMain().getTemp() - 273.15));
        }
        
        lineChart.getData().add(series);
        chartScene = new Scene(lineChart,800,600);
        stage.setScene(chartScene);
    }
    
}
