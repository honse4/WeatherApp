package fi.tuni.prog3.weatherapp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fi.tuni.prog3.weatherapp.apigson.forecast.ForecastData;
import fi.tuni.prog3.weatherapp.apigson.forecast.HourlyForecastData;
import fi.tuni.prog3.weatherapp.apigson.location.LocationData;
import fi.tuni.prog3.weatherapp.apigson.weather.WeatherData;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author vasav
 */
public class GsonToClass {
    private final Gson gson;
    private final  WeatherApi api;
    
    /**
     * Constructor
     */
    public GsonToClass() {
        this.gson = new Gson();
        this.api = new WeatherApi();
    }
    
    /**
     * api call to get the latitude and longitude for a location
     * @param location String which has the name of the location
     * @return LocationData
     */
    public LocationData locationSearch(String location) {
        Type listType = new TypeToken<List<LocationData>>(){}.getType();
        List<LocationData> ldata = gson.fromJson(api.lookUpLocation(location), listType);
       
        return ldata.get(0);
    }
    
    /**
     * api call to get the current weather
     * @param ldata LocationData which has the latitude and longitude
     * @return WeatherData
     */
    public WeatherData weatherSearch(LocationData ldata) {
        WeatherData wdata = gson.fromJson(api.getCurrentWeather(ldata.getLat(),
                ldata.getLon()), WeatherData.class);
        
        return wdata;
    }
    
    /**
     * api call to get the forecast
     * @param ldata LocationData which has the latitude and longitude
     * @return ForecastData
     */
    public ForecastData forecastSearch(LocationData ldata) {
        ForecastData fdata = gson.fromJson(api.getForecast(ldata.getLat(),
                ldata.getLon()), ForecastData.class);
        
        return fdata;
    }
    
    /**
     * api call to get daily forecast
     * @param ldata LocationData which has the latitude and longitude
     * @return HourlyForecastData
     */
    public HourlyForecastData hourlyForecastSearch(LocationData ldata) {
        HourlyForecastData hfdata = gson.fromJson(api.getHourlyForecast(ldata.getLat(),
                ldata.getLon()), HourlyForecastData.class);
        
        return hfdata;
    }
}
