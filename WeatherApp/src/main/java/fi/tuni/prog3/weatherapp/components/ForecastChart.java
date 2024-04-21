
package fi.tuni.prog3.weatherapp.components;

import fi.tuni.prog3.weatherapp.WeatherApp;
import fi.tuni.prog3.weatherapp.apigson.forecast.HourlyForecastData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
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
    private final String unit;
    
    /**
     * Constructor
     * @param stage Primary stage
     * @param scene Main scene displayed to user
     * @param main WeatherApp class
     * @param data HourlyForecastData object containing forecast data for 24 hours
     */
    public ForecastChart(Stage stage, Scene scene, WeatherApp main, HourlyForecastData data, String unit) {
        this.stage = stage;
        this.scene = scene;
        this.main = main;
        this.data = data;
        this.unit = unit;
        

        getChildren().addAll(getBackButton(),getTemperatureChart(), getRainChart());
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
    
    /**
     * Creates LineChart object showing temperature in given unit
     * @return LineChart
     */
    private LineChart<String,Number> getTemperatureChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Time");
        NumberAxis yAxis = new NumberAxis();
        String tempUnit = ("metric".equals(this.unit)) ? "celsius" : "fahrenheit";
        yAxis.setLabel(tempUnit);
        
        LineChart<String,Number> lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.setTitle("Temperatures for following 24 hours");
        
        XYChart.Series series = new XYChart.Series();
        series.setName("Temperatures");
        
        if (data != null) {
            for (var forecastdata : this.data.getList()) {
                String time = forecastdata.getDt_txt();
                Double temperature = forecastdata.getMain().getTemp();
                series.getData().add(new XYChart.Data(time, temperature));
            }
        }
        lineChart.getData().add(series);
        return lineChart;
    }
    
    /**
     * Creates Barchart object showing rainfall in given unit
     * @return BarChart
     */
    private BarChart<String,Number> getRainChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Time");
        NumberAxis yAxis = new NumberAxis();
        String measureUnit = ("metric".equals(this.unit)) ? "mm" : "inches";
        yAxis.setLabel(measureUnit);
        
       BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis);
        barChart.setTitle("Rainfall for following 24 hours");
        
        XYChart.Series series = new XYChart.Series();
        series.setName("Rainfall");
        
        if (data != null) {
            for (var forecastdata : this.data.getList()) {
                String time = forecastdata.getDt_txt();
                        
                // TODO: Something seems to go wrong in creating the Rain class (is null), needs a fix
                Double rain = (forecastdata.getRain() == null) ? 2.0 : 
                          ((forecastdata.getRain().get1h() != null) ? forecastdata.getRain().get1h() : 1.0);
                
                series.getData().add(new XYChart.Data(time,rain));
            }
        }
        barChart.getData().addAll(series);
        return barChart;
    }   
}
