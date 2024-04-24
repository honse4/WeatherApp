package fi.tuni.prog3.weatherapp.components.mainview;

import fi.tuni.prog3.weatherapp.apigson.forecast.Temp;
import fi.tuni.prog3.weatherapp.apigson.weather.Weather;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Class to represent a single column where one day's forecast is shown
 * @author vasav
 */
public class DailyForecastColumn extends VBox{
    private Label dayLabel;
    private Label dateLabel;
    private InputStream stream;
    private ImageView icon;
    private Label minTemperature;
    private Label maxTemperature;
    private final static String DEGREE_SYMBOL = "\u00B0";
    
    /**
     * Constructor
     */
    public DailyForecastColumn() {
        getChildren().addAll(createDateLabels(), createIcon(), createTemperatureLabels());
        setSpacing(5);
        setAlignment(Pos.TOP_CENTER);
        setStyle("-fx-background-color: #e9e9e9;");
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
    private ImageView createIcon() {
        
        icon = new ImageView();
        return icon;
    }
    
    /**
     * Creates the labels displaying the min and max temperatures for the day
     * @return VBox
     */
    private VBox createTemperatureLabels() {
        Label minLabel = new Label("Min: ");
        minLabel.setFont(new Font("Tahoma", 13));      
        minTemperature = new Label();
        minTemperature.setFont(new Font("Helvetica", 13));
        
        HBox minBox = new HBox(minLabel, minTemperature);
        minBox.setAlignment(Pos.CENTER);
        
        Label maxLabel = new Label("Max: ");
        maxLabel.setFont(new Font("Tahoma", 13));
        maxTemperature = new Label();
        maxTemperature.setFont(new Font("Helvetica", 13));
        
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
    public void setIcon(Weather weather)  {
        try {
            String description = weather.getDescription().replace(' ', '-').replace('/', '-');
            if (description.contentEquals("sky-is-clear")) {
                description = "clear-sky";
            }
            stream = new FileInputStream(String.format("weatherSet\\%s.png",description));
            Image image = new Image(stream);
            icon.setImage(image);
            icon.setFitHeight(60);
            icon.setFitWidth(60);           
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
            icon.setFitHeight(50);
            icon.setFitWidth(50);
        } catch (FileNotFoundException e) {
            
        }
    }
}
