package fi.tuni.prog3.weatherapp.apigson.forecast;

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
public class ForecastDataTest {
    
    public ForecastDataTest() {
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
     * Test of getList method, of class ForecastData.
     */
    @Test
    public void testGetList() {
        System.out.println("getList");
        ForecastData instance = new ForecastData();
        List<Forecast> expResult = null; 
        List<Forecast> result = instance.getList();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetList() {
        System.out.println("setList");
        List<Forecast> list = new ArrayList<>();
        ForecastData instance = new ForecastData();
        instance.setList(list);
        assertEquals(list, instance.getList());
    }
    
}
