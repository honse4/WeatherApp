package fi.tuni.prog3.weatherapp.apigson.forecast;

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
public class TempTest {
    
    public TempTest() {
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
     * Test of getMax method, of class Temp.
     */
    @Test
public void testGetMax() {
    System.out.println("getMax");
    Temp instance = new Temp();
    Double expResult = null;
    Double result = instance.getMax();
    assertEquals(expResult, result);
}

@Test
public void testSetMax() {
    System.out.println("setMax");
    Double max = 33.33;
    Temp instance = new Temp();
    instance.setMax(max);
    assertEquals(max, instance.getMax());
}

@Test
public void testGetMin() {
    System.out.println("getMin");
    Temp instance = new Temp();
    Double expResult = null;
    Double result = instance.getMin();
    assertEquals(expResult, result);
}

@Test
public void testSetMin() {
    System.out.println("setMin");
    Double min = 33.33;
    Temp instance = new Temp();
    instance.setMin(min);
    assertEquals(min, instance.getMin());
}

    
}
