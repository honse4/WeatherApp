package fi.tuni.prog3.weatherapp.components;

import fi.tuni.prog3.weatherapp.apigson.forecast.Temp;
import fi.tuni.prog3.weatherapp.apigson.weather.Weather;
import java.time.LocalDate;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author vasav
 */
public class DailyForecastColumn extends VBox{
    private Label dayLabel;
    private Label dateLabel;
    private Label minTemperature;
    private Label maxTemperature;
    private final static String DEGREE_SYMBOL = "\u00B0";
    
    /**
     * Constructor
     */
    public DailyForecastColumn() {
        getChildren().addAll(createDateLabels(), createIcon(), createTemperatureLabels());
        setSpacing(5);
        
        setStyle("-fx-background-color: #f0f0f0;");
    }
    
    /**
     * Creates the labels having the day and date
     * @return HBox
     */
    private HBox createDateLabels() {
        dayLabel = new Label();
        dayLabel.setFont(new Font("Helvetica", 14));
        
        dateLabel = new Label();
        dateLabel.setFont(new Font("Helvetica", 14));
            
        HBox dateBox = new HBox(dayLabel, dateLabel);
        dateBox.setSpacing(2);
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
     * Creates the labels displaying the min and max temperatures for the day
     * @return VBox
     */
    private VBox createTemperatureLabels() {
        Label minLabel = new Label("Min: ");
        minLabel.setFont(new Font("Tahoma", 12));      
        minTemperature = new Label();
        minTemperature.setFont(new Font("Helvetica", 12));
        
        HBox minBox = new HBox(minLabel, minTemperature);
        minBox.setAlignment(Pos.CENTER);
        
        Label maxLabel = new Label("Max: ");
        maxLabel.setFont(new Font("Tahoma", 12));
        maxTemperature = new Label();
        maxTemperature.setFont(new Font("Helvetica", 12));
        
        HBox maxBox = new HBox(maxLabel, maxTemperature);
        maxBox.setAlignment(Pos.CENTER);
        
        VBox temperatureBox = new VBox(minBox, maxBox);
        temperatureBox.setSpacing(1);
        
        return temperatureBox;   
    }
    
    /**
     * Sets the day and date
     * @param date LocalDate object
     */
    public void setTime(LocalDate date) {
        dayLabel.setText(date.getDayOfWeek().toString().substring(0, 3));
        dateLabel.setText(String.valueOf(date.getDayOfMonth()) + 
                "." + String.valueOf(date.getMonthValue()));
    }
    
    /**
     * Sets the min and max temperatures along with the right unit
     * @param temp Temp object holding min and max temperatures
     * @param unit String containing name of the unit
     */
    public void setTemp(Temp temp, String unit) { 
        String suffix = " " + DEGREE_SYMBOL + (unit.contentEquals("metric") ? "C" : "F");
        minTemperature.setText(String.valueOf(temp.getMin()) + suffix);
        maxTemperature.setText(String.valueOf(temp.getMax()) + suffix);
    }
    
    /**
     * Sets the icon based on the weather
     * @param weather Weather object containing weather data
     */
    public void setIcon(Weather weather) {
        
    }
}
