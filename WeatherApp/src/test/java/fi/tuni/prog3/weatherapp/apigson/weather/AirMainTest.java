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
public class AirMainTest {
    
    public AirMainTest() {
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
     * Test of getAqi method, of class AirMain.
     */
    @Test
public void testGetAqi() {
    System.out.println("getAqi");
    AirMain instance = new AirMain();
    Double expResult = null;
    Double result = instance.getAqi();
    assertEquals(expResult, result);
}

@Test
public void testSetAqi() {
    System.out.println("setAqi");
    Double aqi = 33.33;
    AirMain instance = new AirMain();
    instance.setAqi(aqi);
    assertEquals(aqi, instance.getAqi());
}

    
}
