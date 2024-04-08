package fi.tuni.prog3.weatherapp.apigson.forecast;

import fi.tuni.prog3.weatherapp.apigson.weather.Main;
import fi.tuni.prog3.weatherapp.apigson.weather.Rain;
import fi.tuni.prog3.weatherapp.apigson.weather.Weather;
import fi.tuni.prog3.weatherapp.apigson.weather.Wind;
import java.util.List;

/**
 *
 * @author vasav
 */
public class HourlyForecast {
    
    private Double dt;
    private Main main;
    private List<Weather> weather;
    private Wind wind;
    private Rain rain;
    private String dt_txt;

    public Double getDt() {
        return dt;
    }

    public void setDt(Double dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
