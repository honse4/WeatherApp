
package fi.tuni.prog3.weatherapp.components;

import fi.tuni.prog3.weatherapp.WeatherApp;
import fi.tuni.prog3.weatherapp.apigson.forecast.HourlyForecastData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
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

        getChildren().addAll(getBackButton(),lineChart);
    }
    
    /**
     * Creates a HBox containing a button used to go back to the main scene
     * @return HBox
     */
    private HBox getBackButton() {
        SVGPath leftArrow = new SVGPath();
        leftArrow.setContent("M 0 0 L 6 3 L 0 6 L 1.5 3 Z");
        leftArrow.setScaleX(1.75);  
        leftArrow.setScaleY(1.75);
        
        Button back = new Button();
        back.setAlignment(Pos.CENTER);
        back.setGraphic(leftArrow);
        back.setMaxSize(50,30);
        back.setFocusTraversable(false);
        back.setMinSize(50,30);
        
        back.setOnAction(e -> {
            stage.setScene(scene);
            scene.getRoot().requestFocus();    
        });
        VBox.setMargin(back, new Insets(0, -50,0, 0));
        
        HBox alignment = new HBox(back);
        alignment.setAlignment(Pos.TOP_RIGHT);
        return alignment;
    }
    
}
