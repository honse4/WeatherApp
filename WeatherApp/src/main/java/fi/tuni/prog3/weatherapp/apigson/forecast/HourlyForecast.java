package fi.tuni.prog3.weatherapp.apigson.forecast;

import fi.tuni.prog3.weatherapp.apigson.weather.Main;
import fi.tuni.prog3.weatherapp.apigson.weather.Rain;
import fi.tuni.prog3.weatherapp.apigson.weather.Weather;
import fi.tuni.prog3.weatherapp.apigson.weather.Wind;
import java.util.List;

/**
 * A class that represents the forecast object for hourly forecast
 * @author vasav
 */
public class HourlyForecast {
    
    private Double dt;
    private Main main;
    private List<Weather> weather;
    private Wind wind;
    private Double pop;
    private Rain rain;
    private String dt_txt;

    /**
     * Gets the timestamp of the forecast
     * @return Double
     */
    public Double getDt() {
        return dt;
    }

    /**
     * Sets the timestamp for the forecast
     * @param dt Timestamp to be set
     */
    public void setDt(Double dt) {
        this.dt = dt;
    }

    /**
     * Gets main object 
     * @return Main
     */
    public Main getMain() {
        return main;
    }

    /**
     * Sets main object
     * @param main Main object to be set
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * Gets list of weather objects
     * @return List<>
     */
    public List<Weather> getWeather() {
        return weather;
    }

    /**
     * Sets the list of weather object
     * @param weather List of weather objects to be set
     */
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    /**
     * Gets the wind object
     * @return Wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * Sets the wind object
     * @param wind Wind to be set
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }
    
    /**
     * Gets the probability of precipitation
     * @return Double
     */
    public Double getPop() {
        return pop;
    }

    /**
     * Sets the probability of precipitation
     * @param pop probability of precipitation
     */
    public void setPop(Double pop) {
        this.pop = pop;
    }

    /**
     * Gets the Rain object
     * @return Rain
     */
    public Rain getRain() {
        return rain;
    }

    /**
     * Sets the rain object
     * @param rain Rain object to be set
     */
    public void setRain(Rain rain) {
        this.rain = rain;
    }

    /**
     * Gets the date string
     * @return String
     */
    public String getDt_txt() {
        return dt_txt;
    }

    /**
     * Sets the date string
     * @param dt_txt String to be set
     */
    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
