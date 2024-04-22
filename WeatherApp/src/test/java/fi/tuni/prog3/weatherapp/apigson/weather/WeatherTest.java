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
public class WeatherTest {
    
    public WeatherTest() {
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
     * Test of getMain method, of class Weather.
     */
    @Test
    public void testGetMain() {
        System.out.println("getMain");
        Weather instance = new Weather();
        String expResult = null;
        String result = instance.getMain();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMain method, of class Weather.
     */
    @Test
    public void testSetMain() {
        System.out.println("setMain");
        String main = "Sunny";
        Weather instance = new Weather();
        instance.setMain(main);
        assertEquals("Sunny", instance.getMain());
    }

    /**
     * Test of getDescription method, of class Weather.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Weather instance = new Weather();
        String expResult = null;
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Weather.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "Rain";
        Weather instance = new Weather();
        instance.setDescription(description);
        assertEquals("Rain", instance.getDescription());
    }
    
}
