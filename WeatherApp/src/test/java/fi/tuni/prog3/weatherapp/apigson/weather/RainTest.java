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
public class RainTest {
    
    public RainTest() {
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
     * Test of get1h method, of class Rain.
     */
    @Test
    public void testGet1h() {
        System.out.println("get1h");
        Rain instance = new Rain();
        Double expResult = null; 
        Double result = instance.get1h();
        assertEquals(expResult, result);
    }

    /**
     * Test of set1h method, of class Rain.
     */
    @Test
    public void testSet1h() {
        System.out.println("set1h");
        Double _1h = 0.5; 
        Rain instance = new Rain();
        instance.set1h(_1h);
        assertEquals(_1h, instance.get1h());
    }
    
}
