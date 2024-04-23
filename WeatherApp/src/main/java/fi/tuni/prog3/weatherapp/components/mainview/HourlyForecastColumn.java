package fi.tuni.prog3.weatherapp.components.mainview;

import fi.tuni.prog3.weatherapp.apigson.weather.Main;
import fi.tuni.prog3.weatherapp.apigson.weather.Weather;
import fi.tuni.prog3.weatherapp.apigson.weather.Wind;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDateTime;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author vasav
 */
public class HourlyForecastColumn extends VBox{
    private final Label timeLabel;
    private final Label tempLabel;
    private final Label windLabel;
    private final Label rainLabel;
    private final Label humidityLabel;
    private InputStream stream;
    private final ImageView icon;
    private final static String DEGREE_SYMBOL = "\u00B0";
    
    /**
     * Constructor
     */
    public HourlyForecastColumn() {
        timeLabel = new Label();
        timeLabel.setFont(new Font("Helvetica", 14));
        
        Font font = new Font("Tahoma", 13);
        tempLabel = new Label();
        tempLabel.setFont(font);
        
        windLabel = new Label();
        windLabel.setFont(font);
        
        rainLabel = new Label();
        rainLabel.setFont(font);
        
        humidityLabel = new Label();
        humidityLabel.setFont(font);
        
        icon = new ImageView();
        
        getChildren().addAll(timeLabel, icon, tempLabel,
                windLabel, rainLabel, humidityLabel);
        setSpacing(18);
        setStyle("-fx-background-color: #e9e9e9;");
        setMinSize(50, 175);
        setAlignment(Pos.TOP_CENTER);
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
    public void setIcon(Weather weather)  {
        try {
            String description = weather.getDescription().replace(' ', '-').replace('/', '-');
            if (description.contentEquals("sky-is-clear")) {
                description = "clear-sky";
            }
            stream = new FileInputStream(String.format("weatherSet\\%s.png",description));
            Image image = new Image(stream);
            icon.setImage(image);
            icon.setFitHeight(40);
            icon.setFitWidth(40);   
               
        } catch (FileNotFoundException e) {
                setError();          
        } 
    }
    
    /**
     * Sets icon as error in case of unforeseen description 
     */
    public void setError() {
        try {
            stream = new FileInputStream("weatherSet\\error.png");
            Image image = new Image(stream);
            icon.setImage(image);
            icon.setFitHeight(30);
            icon.setFitWidth(30);
        } catch (FileNotFoundException e) {
            
        }
    }
    
    /**
     * Sets the temperatures along with the right unit
     * @param main Main object holding temperatures
     */
    public void setTemp(Main main) { 
        tempLabel.setText(String.valueOf(main.getTemp()) + DEGREE_SYMBOL);

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
