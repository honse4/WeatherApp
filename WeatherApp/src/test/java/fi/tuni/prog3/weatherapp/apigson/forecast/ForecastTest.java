package fi.tuni.prog3.weatherapp.apigson.forecast;

import fi.tuni.prog3.weatherapp.apigson.weather.Weather;
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
public class ForecastTest {
    
    public ForecastTest() {
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

    @Test
    public void testGetDt() {
        System.out.println("getDt");
        Forecast instance = new Forecast();
        Double expResult = null;
        Double result = instance.getDt();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetDt() {
        System.out.println("setDt");
        Double dt = 123.45; 
        Forecast instance = new Forecast();
        instance.setDt(dt);
        assertEquals(dt, instance.getDt());
    }

    @Test
    public void testGetTemp() {
        System.out.println("getTemp");
        Forecast instance = new Forecast();
        Temp expResult = null;
        Temp result = instance.getTemp();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetTemp() {
        System.out.println("setTemp");
        Temp temp = new Temp(); 
        Forecast instance = new Forecast();
        instance.setTemp(temp);
        assertEquals(temp, instance.getTemp());
    }

    @Test
    public void testGetWeather() {
        System.out.println("getWeather");
        Forecast instance = new Forecast();
        List<Weather> expResult = null;
        List<Weather> result = instance.getWeather();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetWeather() {
        System.out.println("setWeather");
        List<Weather> weather = new ArrayList<>();
        Forecast instance = new Forecast();
        instance.setWeather(weather);
        assertEquals(weather, instance.getWeather());
    }
    
}
