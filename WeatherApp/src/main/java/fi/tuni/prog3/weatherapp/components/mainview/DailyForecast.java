package fi.tuni.prog3.weatherapp.components.mainview;

import fi.tuni.prog3.weatherapp.apigson.forecast.Forecast;
import fi.tuni.prog3.weatherapp.apigson.forecast.ForecastData;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * Class to display the daily forecast
 * @author vasav
 */
public class DailyForecast extends GridPane {
    private final ArrayList<DailyForecastColumn> columns;
    
    /**
     * Constructor
     */
    public DailyForecast() {
        this.columns = new ArrayList<>();
        setMinHeight(100);
        setGridLinesVisible(true);
        setPadding(new Insets(5,0,5,0)); 
        
        // Setting the column width and adding columns
        for (int i = 0; i<5; i++) {       
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPercentWidth(20);
            getColumnConstraints().add(colConstraints);
            
            DailyForecastColumn column = new DailyForecastColumn();
            add(column, i, 0);
            columns.add(column);
        }
        
    }
    
    /**
     * Changes the data in each column
     * @param data Daily forecast data
     * @param unit String containing the type of unit
     */
    public void showData(ForecastData data, String unit){
        int i = 0;
        for(Forecast fct : data.getList()) {
            double date = fct.getDt();
            Instant instant = Instant.ofEpochSecond((long) date);
            LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            DailyForecastColumn column = columns.get(i);
            column.setTime(localDate);
            column.setTemp(fct.getTemp(), unit);
            column.setIcon(fct.getWeather().get(0));
            
            i++;
        }
    }
}
