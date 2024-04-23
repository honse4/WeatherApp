package fi.tuni.prog3.weatherapp.components.mainview;

import fi.tuni.prog3.weatherapp.apigson.weather.Main;
import fi.tuni.prog3.weatherapp.apigson.weather.Weather;
import fi.tuni.prog3.weatherapp.apigson.weather.Wind;
import java.time.LocalDateTime;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author vasav
 */
public class HourlyForecastColumn extends VBox{
    private Label timeLabel;
    private Label tempLabel;
    private Label windLabel;
    private Label rainLabel;
    private Label humidityLabel;
    private final static String DEGREE_SYMBOL = "\u00B0";
    
    public HourlyForecastColumn() {
        getChildren().addAll(createTimeLabel(), createIcon(), createTempLabel(),
                createWindLabel(), createRainLabel(), createHumidityLabel());
        setSpacing(10);
        
        setStyle("-fx-background-color: #f0f0f0;");
        setMinSize(50, 175);
    }
    
    /**
     * Creates the label having the time
     * @return HBox
     */
    private HBox createTimeLabel() {
        timeLabel = new Label();
        timeLabel.setFont(new Font("Helvetica", 14));
                    
        HBox dateBox = new HBox( timeLabel);
        dateBox.setAlignment(Pos.CENTER);
        
        return dateBox;
    }
    
    /**
     * Creates the weather icon representing the weather
     * @return HBox
     */
    private HBox createIcon() {
        
        HBox iconBox = new HBox();
        iconBox.setAlignment(Pos.CENTER);
        
        return iconBox;
    }
    
    /**
     * Creates the label having the temperature
     * @return HBox
     */
    private HBox createTempLabel() {
        tempLabel = new Label();
        tempLabel.setFont(new Font("Tahoma", 12));
        
        HBox tempBox = new HBox(tempLabel);
        tempBox.setAlignment(Pos.CENTER);
        
        return tempBox;        
    }
    
    /**
     * Creates the label having the wind
     * @return HBox
     */
    private HBox createWindLabel() {
        windLabel = new Label();
        windLabel.setFont(new Font("Tahoma", 12));
        
        HBox windBox = new HBox(windLabel);
        windBox.setAlignment(Pos.CENTER);
        
        return windBox;        
    }
    
    /**
     * Creates the label having the rain
     * @return HBox
     */
    private HBox createRainLabel() {
        rainLabel = new Label();
        rainLabel.setFont(new Font("Tahoma", 12));
        
        HBox rainBox = new HBox(rainLabel);
        rainBox.setAlignment(Pos.CENTER);
        
        return rainBox;        
    }
    
    /**
     * Creates the label having the humidity
     * @return HBox
     */
    private HBox createHumidityLabel() {
        humidityLabel = new Label();
        humidityLabel.setFont(new Font("Tahoma", 12));
        
        HBox humidityBox = new HBox(humidityLabel);
        humidityBox.setAlignment(Pos.CENTER);
        
        return humidityBox;        
    }
    
    /**
     * Sets the day and date
     * @param time LocalTIme object
     */
    public void setTime(LocalDateTime time) {
        timeLabel.setText(String.valueOf(time.getHour()));
    }
    
    /**
     * Sets the icon based on the weather
     * @param weather Weather object containing weather data
     */
    public void setIcon(Weather weather) {
        
    }
    
    /**
     * Sets the temperatures along with the right unit
     * @param main Main object holding temperatures
     * @param unit String containing name of the unit
     */
    public void setTemp(Main main, String unit) { 
        String suffix = " " + DEGREE_SYMBOL + (unit.contentEquals("metric") ? "C" : "F");
        tempLabel.setText(String.valueOf(main.getTemp()) + suffix);

    }
    
    /**
     * sets the wind speed
     * @param wind Wind object
     */
    public void setWind(Wind wind) {
        windLabel.setText(String.valueOf(wind.getSpeed()));
    }
    
    /**
     * Sets the rain percentage
     * @param pop Double value
     */
    public void setRain(Double pop) {
        rainLabel.setText(String.valueOf(pop));
    }
    
    /**
     * Sets the humidity
     * @param main Main object
     */
    public void setHumidity(Main main) {
        humidityLabel.setText(String.valueOf(main.getHumidity()));
    }
    
}
