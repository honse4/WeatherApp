package fi.tuni.prog3.weatherapp.components.mainview;

import fi.tuni.prog3.weatherapp.apigson.forecast.HourlyForecast;
import fi.tuni.prog3.weatherapp.apigson.forecast.HourlyForecastData;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

/**
 * Class that displays hourly forecast
 * @author vasav
 */
public class HourlyForecastDisplay extends ScrollPane {
    private final ArrayList<HourlyForecastColumn> columns;
    
    public HourlyForecastDisplay() {
        setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        setVbarPolicy(ScrollBarPolicy.NEVER);
        setPadding(new Insets(5,0,5,0)); 
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
        setStyle("-fx-background-color: #e9e9e9;");
    }
    
    public void showData(HourlyForecastData data) {
        int i = 0;
        for(HourlyForecast fct: data.getList()) {
            long unixTimeMillis = fct.getDt().longValue() * 1000L;
            LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTimeMillis), ZoneOffset.UTC);
            HourlyForecastColumn column = columns.get(i);
            column.setTime(dateTime);
            column.setTemp(fct.getMain());
            column.setWind(fct.getWind());
            column.setRain(fct.getPop());
            column.setHumidity(fct.getMain());
            column.setIcon(fct.getWeather().get(0));
            i++;
        }
    }
}
