package fi.tuni.prog3.weatherapp;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import fi.tuni.prog3.weatherapp.apigson.forecast.ForecastData;
import fi.tuni.prog3.weatherapp.apigson.forecast.HourlyForecastData;
import fi.tuni.prog3.weatherapp.apigson.location.LocationData;
import fi.tuni.prog3.weatherapp.apigson.weather.AirQualityData;
import fi.tuni.prog3.weatherapp.apigson.weather.WeatherData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author vasav
 */
public class GsonToClassTest {
    public GsonToClassTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
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
     * Test of locationSearch method, of class GsonToClass.
     */
    @Test
    public void testLocationSearch() {
        System.out.println("locationSearch");
        String location = "Not a country";
        GsonToClass instance = new GsonToClass();
        assertThrows(Exception.class, () -> {
            instance.locationSearch(location);
        });
        
    }

    /**
     * Test of weatherSearch method, of class GsonToClass.
     */
    @Test
    public void testWeatherSearch() {
        System.out.println("weatherSearch");
        LocationData ldata = new LocationData();
        ldata.setLat(Double.MAX_VALUE);
        ldata.setLon(Double.MAX_VALUE);
        GsonToClass instance = new GsonToClass();
        assertThrows(Exception.class, () -> {
            instance.weatherSearch(ldata).getMain();
        });
    }

    /**
     * Test of forecastSearch method, of class GsonToClass.
     */
    @Test
    public void testForecastSearch() {
        System.out.println("forecastSearch");
        LocationData ldata = new LocationData();
        ldata.setLat(Double.MAX_VALUE);
        ldata.setLon(Double.MAX_VALUE);
        GsonToClass instance = new GsonToClass(); 
        assertThrows(Exception.class, () -> {
            instance.forecastSearch(ldata).getList();
        });
    }

    /**
     * Test of qualitySearch method, of class GsonToClass.
     */
    @Test
    public void testQualitySearch() {
        System.out.println("qualitySearch");
        LocationData ldata = new LocationData();
        ldata.setLat(Double.MAX_VALUE);
        ldata.setLon(Double.MAX_VALUE);
        GsonToClass instance = new GsonToClass();
        assertThrows(Exception.class, () -> {
            instance.qualitySearch(ldata).getList();
        });
    }

    /**
     * Test of hourlyForecastSearch method, of class GsonToClass.
     */
    @Test
    public void testHourlyForecastSearch() {
        System.out.println("hourlyForecastSearch");
        LocationData ldata = new LocationData();
        ldata.setLat(Double.MAX_VALUE);
        ldata.setLon(Double.MAX_VALUE);
        GsonToClass instance = new GsonToClass();
        assertThrows(Exception.class, () -> {
            instance.hourlyForecastSearch(ldata).getList();
        });
    }
    
}
