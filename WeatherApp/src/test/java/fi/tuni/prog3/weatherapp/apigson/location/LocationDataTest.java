package fi.tuni.prog3.weatherapp.apigson.location;

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
public class LocationDataTest {
    
    public LocationDataTest() {
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
     * Test of getName method, of class LocationData.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        LocationData instance = new LocationData();
        String expResult = "Tampere";
        String result = instance.getName();
        assertEquals(expResult, result);
        
        instance.setName("London");
        String expResult2 = "London";
        
        assertEquals(instance.getName(), expResult2);
    }

    /**
     * Test of setName method, of class LocationData.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "London";
        LocationData instance = new LocationData();
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getLat method, of class LocationData.
     */
    @Test
    public void testGetLat() {
        System.out.println("getLat");
        LocationData instance = new LocationData();
        instance.setLat(33.33);
        Double expResult = 33.33;
        Double result = instance.getLat();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLat method, of class LocationData.
     */
    @Test
    public void testSetLat() {
        System.out.println("setLat");
        Double lat = 33.33;
        LocationData instance = new LocationData();
        instance.setLat(lat);
        Double expResult = 33.33;
        assertEquals(instance.getLat(), expResult);
    }

    /**
     * Test of getLon method, of class LocationData.
     */
    @Test
    public void testGetLon() {
        System.out.println("getLon");
        LocationData instance = new LocationData();
        instance.setLon(33.33);
        Double expResult = 33.33;
        Double result = instance.getLon();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLon method, of class LocationData.
     */
    @Test
    public void testSetLon() {
        System.out.println("setLon");
        Double lat = 33.33;
        LocationData instance = new LocationData();
        instance.setLon(lat);
        Double expResult = 33.33;
        assertEquals(instance.getLon(), expResult);
    }

    /**
     * Test of getState method, of class LocationData.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        String state = "England";
        LocationData instance = new LocationData();
        instance.setState(state);
        assertEquals(instance.getState(), state);
    }

    /**
     * Test of setState method, of class LocationData.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        LocationData instance = new LocationData();
        instance.setState("England");
        String expResult = "England";
        String result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class LocationData.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        LocationData instance = new LocationData();
        instance.setLat(33.33);
        instance.setLon(33.33);
        String expResult = "(" + 33.33 + "," + 33.33 + ")";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class LocationData.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        LocationData instance = new LocationData();
        instance.setLat(33.33);
        instance.setLon(33.33);
        LocationData other = new LocationData();
        other.setLat(33.33);
        other.setLon(33.34);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
        other.setLon(33.33);
        assertTrue(instance.equals(other));
    }

}
