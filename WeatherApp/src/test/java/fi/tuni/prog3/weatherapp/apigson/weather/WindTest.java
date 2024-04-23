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
public class WindTest {
    
    public WindTest() {
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
     * Test of getSpeed method, of class Wind.
     */
    @Test
    public void testGetSpeed() {
        System.out.println("getSpeed");
        Wind instance = new Wind();
        Double expResult = null;
        Double result = instance.getSpeed();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSpeed method, of class Wind.
     */
    @Test
    public void testSetSpeed() {
        System.out.println("setSpeed");
        Double speed = 33.33;
        Wind instance = new Wind();
        instance.setSpeed(speed);
        assertEquals(speed, instance.getSpeed());
    }

    /**
     * Test of getDeg method, of class Wind.
     */
    @Test
    public void testGetDeg() {
        System.out.println("getDeg");
        Wind instance = new Wind();
        Double expResult = null;
        Double result = instance.getDeg();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDeg method, of class Wind.
     */
    @Test
    public void testSetDeg() {
        System.out.println("setDeg");
        Double deg = 33.33;
        Wind instance = new Wind();
        instance.setDeg(deg);
        assertEquals(deg, instance.getDeg());
    }

    /**
     * Test of getGust method, of class Wind.
     */
    @Test
    public void testGetGust() {
        System.out.println("getGust");
        Wind instance = new Wind();
        Double expResult = null;
        Double result = instance.getGust();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGust method, of class Wind.
     */
    @Test
    public void testSetGust() {
        System.out.println("setGust");
        Double gust = 33.33;
        Wind instance = new Wind();
        instance.setGust(gust);
        assertEquals(gust, instance.getGust());
    }
    
}
