/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.weatherapp;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.google.gson.reflect.TypeToken;
import fi.tuni.prog3.weatherapp.apigson.forecast.ForecastData;
import fi.tuni.prog3.weatherapp.apigson.location.LocationData;
import fi.tuni.prog3.weatherapp.apigson.weather.WeatherData;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author vasav
 */
public class WeatherApiTest {
    private static Gson gson;
    
    public WeatherApiTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        gson = new Gson();
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of lookUpLocation method, of class WeatherApi.
     */
    @Test
    public void testLookUpLocation() {
        System.out.println("lookUpLocation");
        String loc = "London";
        Double lat = 51.5073219;
        Double lon = -0.1276474;
        WeatherApi api = new WeatherApi();
        String result = api.lookUpLocation(loc);
        assertTrue(result.contains(loc));
        
        try {
            Type listType = new TypeToken<List<LocationData>>(){}.getType();
            List<LocationData> ldata = gson.fromJson(result, listType);
            assertTrue(Objects.equals(lat, ldata.get(0).getLat()));
            assertTrue(Objects.equals(lon, ldata.get(0).getLon()));
        } catch (JsonSyntaxException e) {
            fail("Did not fit the gson schema");
        }
        
    }

    /**
     * Test of getCurrentWeather method, of class WeatherApi.
     */
    @Test
    public void testGetCurrentWeather() {
        System.out.println("getCurrentWeather");
        double lat = 51.5073219;
        double lon = -0.1276474;
        WeatherApi instance = new WeatherApi();
        String result = instance.getCurrentWeather(lat, lon);
        assertTrue(result.contains("London"));
        try {
           WeatherData wdata = gson.fromJson(result, WeatherData.class);
            assertTrue(!wdata.getWeather().isEmpty());
        } catch (JsonSyntaxException e) {
            fail("Did not fit the gson schema");
        }
    }

    /**
     * Test of getForecast method, of class WeatherApi.
     */
    @Test
    public void testGetForecast() {
        System.out.println("getForecast");
        double lat = 51.5073219;
        double lon = -0.1276474;
        WeatherApi instance = new WeatherApi();
        String result = instance.getForecast(lat, lon);
        assertTrue(result.contains("London"));
        
        try {
           ForecastData fdata = gson.fromJson(result, ForecastData.class);
            assertTrue(fdata.getList().size() == 5);
        } catch (JsonSyntaxException e) {
            fail("Did not fit the gson schema");
        }
    }
    
}
