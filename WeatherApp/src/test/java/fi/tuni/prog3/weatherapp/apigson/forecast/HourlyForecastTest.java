package fi.tuni.prog3.weatherapp.apigson.forecast;

import fi.tuni.prog3.weatherapp.apigson.weather.Main;
import fi.tuni.prog3.weatherapp.apigson.weather.Rain;
import fi.tuni.prog3.weatherapp.apigson.weather.Weather;
import fi.tuni.prog3.weatherapp.apigson.weather.Wind;
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
public class HourlyForecastTest {
    
    public HourlyForecastTest() {
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
     * Test of getDt method, of class HourlyForecast.
     */
    @Test
public void testGetDt() {
    System.out.println("getDt");
    HourlyForecast instance = new HourlyForecast();
    Double expResult = null;
    Double result = instance.getDt();
    assertEquals(expResult, result);
}

@Test
public void testSetDt() {
    System.out.println("setDt");
    Double dt = 1232.1212;
    HourlyForecast instance = new HourlyForecast();
    instance.setDt(dt);
    assertEquals(dt, instance.getDt());
}

@Test
public void testGetMain() {
    System.out.println("getMain");
    HourlyForecast instance = new HourlyForecast();
    Main expResult = null;
    Main result = instance.getMain();
    assertEquals(expResult, result);
}

@Test
public void testSetMain() {
    System.out.println("setMain");
    Main main = new Main();
    HourlyForecast instance = new HourlyForecast();
    instance.setMain(main);
    assertEquals(main, instance.getMain());
}

@Test
public void testGetWeather() {
    System.out.println("getWeather");
    HourlyForecast instance = new HourlyForecast();
    List<Weather> expResult = null;
    List<Weather> result = instance.getWeather();
    assertEquals(expResult, result);
}

@Test
public void testSetWeather() {
    System.out.println("setWeather");
    List<Weather> weather = new ArrayList();
    HourlyForecast instance = new HourlyForecast();
    instance.setWeather(weather);
    assertEquals(weather, instance.getWeather());
}

@Test
public void testGetWind() {
    System.out.println("getWind");
    HourlyForecast instance = new HourlyForecast();
    Wind expResult = null;
    Wind result = instance.getWind();
    assertEquals(expResult, result);
}

@Test
public void testSetWind() {
    System.out.println("setWind");
    Wind wind = new Wind();
    HourlyForecast instance = new HourlyForecast();
    instance.setWind(wind);
    assertEquals(wind, instance.getWind());
}

@Test
public void testGetPop() {
    System.out.println("getPop");
    HourlyForecast instance = new HourlyForecast();
    Double expResult = null;
    Double result = instance.getPop();
    assertEquals(expResult, result);
}

@Test
public void testSetPop() {
    System.out.println("setPop");
    Double pop = 33.33;
    HourlyForecast instance = new HourlyForecast();
    instance.setPop(pop);
    assertEquals(pop, instance.getPop());
}

@Test
public void testGetRain() {
    System.out.println("getRain");
    HourlyForecast instance = new HourlyForecast();
    Rain expResult = null;
    Rain result = instance.getRain();
    assertEquals(expResult, result);
}

@Test
public void testSetRain() {
    System.out.println("setRain");
    Rain rain = new Rain();
    HourlyForecast instance = new HourlyForecast();
    instance.setRain(rain);
    assertEquals(rain, instance.getRain());
}

@Test
public void testGetDt_txt() {
    System.out.println("getDt_txt");
    HourlyForecast instance = new HourlyForecast();
    String expResult = null;
    String result = instance.getDt_txt();
    assertEquals(expResult, result);
}

@Test
public void testSetDt_txt() {
    System.out.println("setDt_txt");
    String dt_txt = "23.32.12";
    HourlyForecast instance = new HourlyForecast();
    instance.setDt_txt(dt_txt);
    assertEquals(dt_txt, instance.getDt_txt());
}
    
}
