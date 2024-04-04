/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp.apigson.weather;

import java.util.List;

/**
 *
 * @author vasav
 */
public class WeatherData {
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Rain rain;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
    
    
    public Main getMain() {
        return this.main;
    }
    
    public void setMain(Main main) {
        this.main = main;
    }
    
    public Wind getWind() {
        return this.wind;
    }
    
    public void setWind(Wind wind) {
        this.wind = wind;
    }
    
    public Rain getRain() {
        return this.rain;
    }
    
    public void setRain(Rain rain) {
        this.rain = rain;
    }
    
}
