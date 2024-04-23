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
public class HourlyForecastDataTest {
    
    public HourlyForecastDataTest() {
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
     * Test of getList method, of class HourlyForecastData.
     */
    @Test
public void testGetList() {
    System.out.println("getList");
    HourlyForecastData instance = new HourlyForecastData();
    List<HourlyForecast> expResult = null;
    List<HourlyForecast> result = instance.getList();
    assertEquals(expResult, result);
}

@Test
public void testSetList() {
    System.out.println("setList");
    List<HourlyForecast> list = new ArrayList();
    HourlyForecastData instance = new HourlyForecastData();
    instance.setList(list);
    assertEquals(list, instance.getList());
}
    
}
