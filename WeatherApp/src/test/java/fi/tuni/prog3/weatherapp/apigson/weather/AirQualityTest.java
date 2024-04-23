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
public class AirQualityTest {
    
    public AirQualityTest() {
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
     * Test of getMain method, of class AirQuality.
     */
    @Test
public void testGetMain() {
    System.out.println("getMain");
    AirQuality instance = new AirQuality();
    AirMain expResult = null;
    AirMain result = instance.getMain();
    assertEquals(expResult, result);
}

@Test
public void testSetMain() {
    System.out.println("setMain");
    AirMain main = new AirMain();
    AirQuality instance = new AirQuality();
    instance.setMain(main);
    assertEquals(main, instance.getMain());
}

    
}
