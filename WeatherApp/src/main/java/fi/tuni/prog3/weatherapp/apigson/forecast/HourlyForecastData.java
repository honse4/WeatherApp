package fi.tuni.prog3.weatherapp.apigson.forecast;

import java.util.List;

/**
 *
 * @author vasav
 */
public class HourlyForecastData {
    
    private List<HourlyForecast> list;

    public List<HourlyForecast> getList() {
        return list;
    }

    public void setList(List<HourlyForecast> list) {
        this.list = list;
    }
    
}
