package fi.tuni.prog3.weatherapp.apigson.forecast;

import java.util.List;

/**
 * Class stores the forecast values for a few days
 * @author vasav
 */
public class ForecastData {
    private List<Forecast> list;

    /**
     * Gets the list of forecast object
     * @return 
     */
    public List<Forecast> getList() {
        return list;
    }

    /**
     * Sets the list of forecast objects
     * @param list 
     */
    public void setList(List<Forecast> list) {
        this.list = list;
    }
    
}
