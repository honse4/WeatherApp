package fi.tuni.prog3.weatherapp.apigson.forecast;

import fi.tuni.prog3.weatherapp.apigson.weather.Weather;
import java.util.List;

/**
 *
 * @author vasav
 */
public class Forecast {
    private Double dt;
    private Temp temp;
    private List<Weather> weather; 
    private Double rain;

    public Double getDt() {
        return dt;
    }

    public void setDt(Double dt) {
        this.dt = dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
    
    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }
}
