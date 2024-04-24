package fi.tuni.prog3.weatherapp.apigson.forecast;

import java.util.List;

/**
 * Class stores the forecast values for 24 hours
 * @author vasav
 */
public class HourlyForecastData {
    
    private List<HourlyForecast> list;

    /**
     * Gets the list of HourlyForecast object
     * @return List<>
     */
    public List<HourlyForecast> getList() {
        return list;
    }

    /**
     * Sets the list of HourlyForecast objects
     * @param list List to be set
     */
    public void setList(List<HourlyForecast> list) {
        this.list = list;
    }
    
}
