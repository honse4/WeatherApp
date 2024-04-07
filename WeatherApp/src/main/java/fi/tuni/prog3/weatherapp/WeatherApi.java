package fi.tuni.prog3.weatherapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author vasav
 */
public class WeatherApi implements iAPI{
    private final static String API_ID = "2bcb8cfeeba3f4072f586ab0d2b40b2b";
    private final static String URL_WEATHER = "https://api.openweathermap.org/data/2.5/weather?";
    private final static String URL_GEO = "http://api.openweathermap.org/geo/1.0/direct?";
    private final static String URL_FORECAST_DAILY = "http://api.openweathermap.org/data/2.5/forecast/daily?";
    
    public WeatherApi() {}
    
    /**
     * Returns coordinates for a location.
     * @param loc Name of the location for which coordinates should be fetched.
     * @return String.
     */
    @Override
    public String lookUpLocation(String loc) {
        String parameters = String.format("q=%s&limit=1&appid=%s",loc, API_ID);
        try {
            URL url = new URL(URL_GEO + parameters);
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            
            StringBuilder content = new StringBuilder();
            try (BufferedReader responseReader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                responseReader.lines().forEach(line -> content.append(line).append("\n"));
            }
            
            conn.disconnect();
            return content.toString();
        } catch (IOException e) {
            
        }
        return null;
    }

    /**
     * Returns the current weather for the given coordinates.
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return String.
     */
    @Override
    public String getCurrentWeather(double lat, double lon) {
       String parameters = String.format("lat=%f&lon=%f&appid=%s",lat,lon, API_ID);
        try {
            URL url = new URL(URL_WEATHER + parameters);
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            
            StringBuilder content = new StringBuilder();
            try (BufferedReader responseReader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                responseReader.lines().forEach(line -> content.append(line).append("\n"));
            }
            
            conn.disconnect();
            return content.toString();
        } catch (IOException e) {
            
        }
        return null;
    
    }

    /**
     * Returns a forecast for the given coordinates.
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return String.
     */
    @Override
    public String getForecast(double lat, double lon) {
        String parameters = String.format("lat=%f&lon=%f&cnt=5&appid=%s",lat,lon, API_ID);
        try {
            URL url = new URL(URL_FORECAST_DAILY + parameters);
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            
            StringBuilder content = new StringBuilder();
            try (BufferedReader responseReader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                responseReader.lines().forEach(line -> content.append(line).append("\n"));
            }

            conn.disconnect();
            return content.toString();
        } catch (IOException e) {
            
        }
        return null;
    
    }
    
}
