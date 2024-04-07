package fi.tuni.prog3.weatherapp.apigson.forecast;

import java.util.List;

/**
 *
 * @author vasav
 */
public class ForecastData {
    private List<Forecast> list;

    public List<Forecast> getList() {
        return list;
    }

    public void setList(List<Forecast> list) {
        this.list = list;
    }
    
}
