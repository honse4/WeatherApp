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
    
    public DailyForecastColumn() {
        getChildren().addAll(createDateLabels(), createIcon(), createTemperatureLabels());
        setSpacing(4);
    }
    
    private HBox createDateLabels() {
        dayLabel = new Label();
        dayLabel.setFont(new Font("Helvetica", 14));
        
        dateLabel = new Label();
        dateLabel.setFont(new Font("Calibri", 14));
            
        HBox dateBox = new HBox(dayLabel, dateLabel);
        dateBox.setSpacing(2);
        dateBox.setAlignment(Pos.CENTER);
        
        return dateBox;
    }
    
    private HBox createIcon() {
        
        HBox iconBox = new HBox();
        iconBox.setAlignment(Pos.CENTER);
        
        return iconBox;
    }
    
    private VBox createTemperatureLabels() {
        Label minLabel = new Label("Min: ");
        minLabel.setFont(new Font("Helvetica", 12));      
        minTemperature = new Label();
        minTemperature.setFont(new Font("Helvetica", 12));
        
        HBox minBox = new HBox(minLabel, minTemperature);
        minBox.setAlignment(Pos.CENTER);
        
        Label maxLabel = new Label("Max: ");
        maxLabel.setFont(new Font("Helvetica", 12));
        maxTemperature = new Label();
        maxTemperature.setFont(new Font("Helvetica", 12));
        
        HBox maxBox = new HBox(maxLabel, maxTemperature);
        maxBox.setAlignment(Pos.CENTER);
        
        VBox temperatureBox = new VBox(minBox, maxBox);
        temperatureBox.setSpacing(2);
        
        return temperatureBox;
        
    }
    
    public void setTime(LocalDate date) {
        dayLabel.setText(date.getDayOfWeek().toString().substring(0, 3));
        dateLabel.setText(String.valueOf(date.getDayOfMonth()) + 
                "." + String.valueOf(date.getMonthValue()));
    }
    
    public void setTemp(Temp temp) {
        minTemperature.setText(String.valueOf(temp.getMin()));
        maxTemperature.setText(String.valueOf(temp.getMax()));
    }
    
    public void setIcon(Weather weather) {
        
    }
}
