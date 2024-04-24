package fi.tuni.prog3.weatherapp.apigson.forecast;

import fi.tuni.prog3.weatherapp.apigson.weather.Weather;
import java.util.List;

/**
 * Class represents a Forecast object for the daily forecast
 * @author vasav
 */
public class Forecast {
    private Double dt;
    private Temp temp;
    private List<Weather> weather; 
    private Double rain;

    /**
     * Gets timestamp of forecast data
     * @return Double
     */
    public Double getDt() {
        return dt;
    }

    /**
     * Set the timestamp of the forecast data
     * @param dt Timestamp of the forecast data to set
     */
    public void setDt(Double dt) {
        this.dt = dt;
    }

    /**
     * Gets the temperature object of the forecast
     * @return Temp
     */
    public Temp getTemp() {
        return temp;
    }
    
    /**
     * Sets the temperature object of the forecast
     * @param temp Temperature object to set
     */
    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    /**
     * Gets list of Weather objects
     * @return List<>
     */
    public List<Weather> getWeather() {
        return weather;
    }

    /**
     * Sets the list of Weather objects
     * @param weather List of weather objects
     */
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
    
    /**
     * Gets volume of rain from the forecast
     * @return Double
     */
    public Double getRain() {
        return rain;
    }

    /**
     * Sets the volume of rain
     * @param rain Volume of rain
     */
    public void setRain(Double rain) {
        this.rain = rain;
    }
}
