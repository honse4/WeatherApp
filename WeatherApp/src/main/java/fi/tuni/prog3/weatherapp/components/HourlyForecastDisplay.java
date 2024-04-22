package fi.tuni.prog3.weatherapp.components;

import fi.tuni.prog3.weatherapp.apigson.forecast.HourlyForecast;
import fi.tuni.prog3.weatherapp.apigson.forecast.HourlyForecastData;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author vasav
 */
public class HourlyForecastDisplay extends ScrollPane {
    private final ArrayList<HourlyForecastColumn> columns;
    
    public HourlyForecastDisplay() {
        setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        setVbarPolicy(ScrollBarPolicy.NEVER);
        HBox container = new HBox();
        this.columns = new ArrayList<>();
        
        for(int i = 0; i<24; i++){
            HourlyForecastColumn column = new HourlyForecastColumn();
            columns.add(column);
            container.getChildren().add(column);
        }
        setContent(container);
        setFitToHeight(true);
        setFitToWidth(true);
    }
    
    public void showData(HourlyForecastData data, String unit) {
        int i = 0;
        for(HourlyForecast fct: data.getList()) {
            long unixTimeMillis = fct.getDt().longValue() * 1000L;
            LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTimeMillis), ZoneOffset.UTC);
            HourlyForecastColumn column = columns.get(i);
            column.setTime(dateTime);
            column.setTemp(fct.getMain(), unit);
            column.setWind(fct.getWind());
            column.setRain(fct.getPop());
            column.setHumidity(fct.getMain());
            
            i++;
        }
    }
}
