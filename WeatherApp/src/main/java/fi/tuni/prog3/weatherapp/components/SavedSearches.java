package fi.tuni.prog3.weatherapp.components;

import fi.tuni.prog3.weatherapp.WeatherApp;
import fi.tuni.prog3.weatherapp.apigson.location.LocationData;
import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 *
 * @author Ville Kivioja
 */
public class SavedSearches extends ComboBox{
    private final Stage stage;
    private final Scene scene;
    private final WeatherApp main;
    private Preferences preferences;
    
    
    public SavedSearches(Stage stage, Scene scene, WeatherApp main, Preferences preferences) {
        this.stage = stage;
        this.scene = scene;
        this.main = main;
        this.preferences = preferences;
        
        ObservableList<String> list = getItems();
        if (!preferences.getFavouriteLocations().isEmpty()) {
            for (LocationData preference : preferences.getFavouriteLocations()) {
                String locName = preference.getName();
                list.add(locName);
            }
        } 

        if (!preferences.getLocationSearchHistory().isEmpty()) {
            for (LocationData preference : preferences.getLocationSearchHistory()) {
                String locName = preference.getName();
                list.add(locName);
            }
        } else {
            list.add("No saved searches found.");
        }
    }
    
}
