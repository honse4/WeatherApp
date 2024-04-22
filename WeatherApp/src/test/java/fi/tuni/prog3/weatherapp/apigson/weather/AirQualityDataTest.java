package fi.tuni.prog3.weatherapp.apigson.weather;

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
public class AirQualityDataTest {
    
    public AirQualityDataTest() {
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
     * Test of getList method, of class AirQualityData.
     */
    @Test
public void testGetList() {
    System.out.println("getList");
    AirQualityData instance = new AirQualityData();
    List<AirQuality> expResult = null;
    List<AirQuality> result = instance.getList();
    assertEquals(expResult, result);
}

@Test
public void testSetList() {
    System.out.println("setList");
    List<AirQuality> list = new ArrayList<>();
    AirQualityData instance = new AirQualityData();
    instance.setList(list);
    assertEquals(list, instance.getList());
}

@Test
public void testDescriptionOfQuality() {
    System.out.println("descriptionOfQuality");
    Double quality = 3.0;
    String expResult = "Moderate";
    String result = AirQualityData.descriptionOfQuality(quality);
    assertEquals(expResult, result);
}

    
}
