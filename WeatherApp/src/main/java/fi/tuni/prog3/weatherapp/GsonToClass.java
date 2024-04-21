package fi.tuni.prog3.weatherapp;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import fi.tuni.prog3.weatherapp.apigson.forecast.ForecastData;
import fi.tuni.prog3.weatherapp.apigson.forecast.HourlyForecastData;
import fi.tuni.prog3.weatherapp.apigson.location.LocationData;
import fi.tuni.prog3.weatherapp.apigson.weather.AirQualityData;
import fi.tuni.prog3.weatherapp.apigson.weather.WeatherData;
import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
     * @param unit Unit of measurement
     * @return WeatherData
     */
    public WeatherData weatherSearch(LocationData ldata, String unit) {
        WeatherData wdata = gson.fromJson(api.getCurrentWeather(ldata.getLat(),
                ldata.getLon(), unit), WeatherData.class);
        
        return wdata;
    }
    
    /**
     * api call to get the forecast
     * @param ldata LocationData which has the latitude and longitude
     * @param unit Unit of measurement
     * @return ForecastData
     */
    public ForecastData forecastSearch(LocationData ldata, String unit) {
        ForecastData fdata = gson.fromJson(api.getForecast(ldata.getLat(),
                ldata.getLon(), unit), ForecastData.class);
        
        return fdata;
    }
    
    /**
     * api call to get air quality
     * @param ldata LocationData which has latitude and longitude
     * @return AirQualityData
     */
    public AirQualityData qualitySearch(LocationData ldata) {
        AirQualityData aqdata = gson.fromJson(api.getAirQuality(ldata.getLat(),
                ldata.getLon()), AirQualityData.class);
        
        return aqdata;
    }
    
    /**
     * api call to get daily forecast
     * @param ldata LocationData which has the latitude and longitude
     * @param unit Unit of measurement
     * @return HourlyForecastData
     */
    public HourlyForecastData hourlyForecastSearch(LocationData ldata, String unit) {
        HourlyForecastData hfdata = gson.fromJson(api.getHourlyForecast(ldata.getLat(),
                ldata.getLon(), unit), HourlyForecastData.class);
        
        return hfdata;
    }
    
    /**
    * Returns preferences based on given Json-data. Creates a new empty Preferences
    * object if the given data is null.
    * @param jsonData Jsondata as string used to create the preferences object.
    * @return Preferences
    */
    public Preferences makePreferencesObject(String jsonData) {

        try {
            if (jsonData == null || jsonData.isBlank()) {
               Preferences preferences = new Preferences();
               preferences.setCurrentLocation(locationSearch("Tampere"));
               preferences.setFavouriteLocations(new ArrayList());
               preferences.setLocationSearchHistory(new ArrayList());
               return preferences;
            }
            return this.gson.fromJson(jsonData, Preferences.class);
        } catch (JsonSyntaxException e) {
            Preferences preferences = new Preferences();
            preferences.setCurrentLocation(locationSearch("Tampere"));
            preferences.setFavouriteLocations(new ArrayList());
            preferences.setLocationSearchHistory(new ArrayList());
            return preferences;
        }
        
    }
}
