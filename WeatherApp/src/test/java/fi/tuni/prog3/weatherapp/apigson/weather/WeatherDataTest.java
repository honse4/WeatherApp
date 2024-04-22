package fi.tuni.prog3.weatherapp.apigson.weather;

import java.util.ArrayList;
import java.util.List;
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
public class WeatherDataTest {
    
    public WeatherDataTest() {
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
     * Test of getWeather method, of class WeatherData.
     */
    @Test
    public void testGetWeather() {
        System.out.println("getWeather");
        WeatherData instance = new WeatherData();
        List<Weather> expResult = null; 
        List<Weather> result = instance.getWeather();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWeather method, of class WeatherData.
     */
    @Test
    public void testSetWeather() {
        System.out.println("setWeather");
        List<Weather> weather = new ArrayList();
        WeatherData instance = new WeatherData();
        instance.setWeather(weather);
        assertEquals(weather, instance.getWeather());
    }

    /**
     * Test of getMain method, of class WeatherData.
     */
    @Test
    public void testGetMain() {
        System.out.println("getMain");
        WeatherData instance = new WeatherData();
        Main expResult = null; 
        Main result = instance.getMain();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMain method, of class WeatherData.
     */
    @Test
    public void testSetMain() {
        System.out.println("setMain");
        Main main = new Main(); 
        WeatherData instance = new WeatherData();
        instance.setMain(main);
        assertEquals(main, instance.getMain());
    }

    /**
     * Test of getWind method, of class WeatherData.
     */
    @Test
    public void testGetWind() {
        System.out.println("getWind");
        WeatherData instance = new WeatherData();
        Wind expResult = null; 
        Wind result = instance.getWind();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWind method, of class WeatherData.
     */
    @Test
    public void testSetWind() {
        System.out.println("setWind");
        Wind wind = new Wind();
        WeatherData instance = new WeatherData();
        instance.setWind(wind);
        assertEquals(wind, instance.getWind());
    }

    /**
     * Test of getRain method, of class WeatherData.
     */
    @Test
    public void testGetRain() {
        System.out.println("getRain");
        WeatherData instance = new WeatherData();
        Rain expResult = null; 
        Rain result = instance.getRain();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRain method, of class WeatherData.
     */
    @Test
    public void testSetRain() {
        System.out.println("setRain");
        Rain rain = new Rain(); 
        WeatherData instance = new WeatherData();
        instance.setRain(rain);
        assertEquals(rain, instance.getRain());
    }
    
}
