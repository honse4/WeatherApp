package fi.tuni.prog3.weatherapp.apigson.weather;

import java.util.List;

/**
 * Class stores all the description for the weather
 * @author vasav
 */
public class WeatherData {
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Rain rain;

    /**
     * Gets list of weather objects
     * @return List<>
     */
    public List<Weather> getWeather() {
        return weather;
    }

    /**
     * Sets the list of weather objects
     * @param weather List to be set
     */
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
    
    /**
     * Gets the Main object
     * @return Main
     */
    public Main getMain() {
        return this.main;
    }
    
    /**
     * Sets the Main object
     * @param main Main object to be set
     */
    public void setMain(Main main) {
        this.main = main;
    }
    
    /**
     * Gets the Wind object
     * @return Wind
     */
    public Wind getWind() {
        return this.wind;
    }
    
    /**
     * Sets the wind object
     * @param wind Wind object to be set
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }
    
    /**
     * Gets the Rain object
     * @return Rain
     */
    public Rain getRain() {
        return this.rain;
    }
    
    /**
     * Sets the rain object
     * @param rain Rain object to be set
     */
    public void setRain(Rain rain) {
        this.rain = rain;
    }
    
}
