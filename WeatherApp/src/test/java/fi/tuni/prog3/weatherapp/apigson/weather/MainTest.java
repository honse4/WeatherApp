package fi.tuni.prog3.weatherapp.apigson.weather;

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
public class MainTest {
    
    public MainTest() {
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
     * Test of getTemp method, of class Main.
     */
    @Test
    public void testGetTemp() {
        System.out.println("getTemp");
        Main instance = new Main();
        Double expResult = null; 
        Double result = instance.getTemp();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTemp method, of class Main.
     */
    @Test
    public void testSetTemp() {
        System.out.println("setTemp");
        Double temp = 25.0; 
        Main instance = new Main();
        instance.setTemp(temp);
        assertEquals(temp, instance.getTemp());
    }

    /**
     * Test of getFeels_like method, of class Main.
     */
    @Test
    public void testGetFeels_like() {
        System.out.println("getFeels_like");
        Main instance = new Main();
        Double expResult = null;
        Double result = instance.getFeels_like();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFeels_like method, of class Main.
     */
    @Test
    public void testSetFeels_like() {
        System.out.println("setFeels_like");
        Double feels_like = 27.5; 
        Main instance = new Main();
        instance.setFeels_like(feels_like);
        assertEquals(feels_like, instance.getFeels_like());
    }

    /**
     * Test of getTemp_min method, of class Main.
     */
    @Test
    public void testGetTemp_min() {
        System.out.println("getTemp_min");
        Main instance = new Main();
        Double expResult = null; 
        Double result = instance.getTemp_min();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTemp_min method, of class Main.
     */
    @Test
    public void testSetTemp_min() {
        System.out.println("setTemp_min");
        Double temp_min = 20.0; 
        Main instance = new Main();
        instance.setTemp_min(temp_min);
        assertEquals(temp_min, instance.getTemp_min());
    }

    /**
     * Test of getTemp_max method, of class Main.
     */
    @Test
    public void testGetTemp_max() {
        System.out.println("getTemp_max");
        Main instance = new Main();
        Double expResult = null; 
        Double result = instance.getTemp_max();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTemp_max method, of class Main.
     */
    @Test
    public void testSetTemp_max() {
        System.out.println("setTemp_max");
        Double temp_max = 30.0; 
        Main instance = new Main();
        instance.setTemp_max(temp_max);
        assertEquals(temp_max, instance.getTemp_max());
    }

    /**
     * Test of getPressure method, of class Main.
     */
    @Test
    public void testGetPressure() {
        System.out.println("getPressure");
        Main instance = new Main();
        Double expResult = null; 
        Double result = instance.getPressure();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPressure method, of class Main.
     */
    @Test
    public void testSetPressure() {
        System.out.println("setPressure");
        Double pressure = 1013.25; 
        Main instance = new Main();
        instance.setPressure(pressure);
        assertEquals(pressure, instance.getPressure());
    }

    /**
     * Test of getHumidity method, of class Main.
     */
    @Test
    public void testGetHumidity() {
        System.out.println("getHumidity");
        Main instance = new Main();
        Double expResult = null; 
        Double result = instance.getHumidity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHumidity method, of class Main.
     */
    @Test
    public void testSetHumidity() {
        System.out.println("setHumidity");
        Double humidity = 70.0; 
        Main instance = new Main();
        instance.setHumidity(humidity);
        assertEquals(humidity, instance.getHumidity());
    }
    
}
