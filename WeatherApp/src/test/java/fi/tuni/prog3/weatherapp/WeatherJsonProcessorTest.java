/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.weatherapp;

import com.google.gson.Gson;
import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import fi.tuni.prog3.weatherapp.apigson.location.LocationData;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ville
 */
public class WeatherJsonProcessorTest {
    private static Preferences preferences;
    
    public WeatherJsonProcessorTest() {
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
     * Test of readFromFile method, of class WeatherJsonProcessor.
     */
    @Test
    public void testReadFromFile() throws Exception {
        System.out.println("readFromFile");
        String fileName = "preferencesTestIn.json";
        WeatherJsonProcessor instance = new WeatherJsonProcessor();
        Gson gson = new Gson();
        String expectedJson = 
                "{currentLocation:{lat:61.498020,lon:23.760317},"
                + "locationSearchHistory:[{lat:60.1756,lon:24.9342},{lat:61.498020,lon:23.760317}],"
                + "favouriteLocations:[{lat:65.0121,lon:25.4651}]}";
        Preferences pref1 = gson.fromJson(expectedJson, Preferences.class);
        
        String result = instance.readFromFile(fileName);
        Preferences pref2 = gson.fromJson(result, Preferences.class);
        assertEquals(pref1.toString(), pref2.toString());
    }

    /**
     * Test of writeToFile method, of class WeatherJsonProcessor.
     */
    @Test
    public void testWriteToFile() throws Exception {
        ArrayList<LocationData> locs = new ArrayList<>();
        for (int i = 50; i < 61; ++i) {
            LocationData loc = new LocationData();
            loc.setLat(Double.valueOf(i));
            loc.setLon(Double.valueOf(i));
            loc.setName(String.valueOf(i));
            locs.add(loc);
        }
        preferences = new Preferences();
        preferences.setCurrentLocation(locs.get(0));
        preferences.setLocationSearchHistory(locs);
        preferences.setFavouriteLocations(locs);
        
        Gson gson = new Gson();
        
        System.out.println("writeToFile");
        String fileName = "preferencesTestOut.json";
        WeatherJsonProcessor instance = new WeatherJsonProcessor();
        instance.setPreferences(this.preferences);
        boolean expResult = true;
        boolean result = instance.writeToFile(fileName);
        assertEquals(expResult, result); 
    }

    /**
     * Test of getPreferences method, of class WeatherJsonProcessor.
     */
    @Test
    public void testGetPreferences() throws Exception {
        ArrayList<LocationData> locs = new ArrayList<>();
        for (int i = 50; i < 61; ++i) {
            LocationData loc = new LocationData();
            loc.setLat(Double.valueOf(i));
            loc.setLon(Double.valueOf(i));
            loc.setName(String.valueOf(i));
            locs.add(loc);
        }
        
        preferences = new Preferences();
        preferences.setCurrentLocation(locs.get(0));
        preferences.setLocationSearchHistory(locs);
        preferences.setFavouriteLocations(locs);

        System.out.println("getPreferences");
        WeatherJsonProcessor instance = new WeatherJsonProcessor();
        instance.setPreferences(this.preferences);
        LocationData expResult = new LocationData();
        expResult.setLat(50.0);
        expResult.setLon(50.0);
        String result = instance.getPreferences().getCurrentLocation().toString();
        assertEquals(expResult.toString(), result);
    }
}
