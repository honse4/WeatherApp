package fi.tuni.prog3.weatherapp.components;

import fi.tuni.prog3.weatherapp.WeatherApp;
import fi.tuni.prog3.weatherapp.apigson.location.LocationData;
import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Ville Kivioja
 */
public class SearchHistory extends ComboBox{
    private final Stage stage;
    private final Scene scene;
    private final WeatherApp main;
    private TextField searchbar;
    
    private ObservableList<String> list;
    private Preferences preferences;
    
    
    public SearchHistory(Stage stage, Scene scene, WeatherApp main, TextField searchbar, Preferences preferences) {
        this.stage = stage;
        this.scene = scene;
        this.main = main;
        this.preferences = preferences;
        this.searchbar = searchbar;
        
        list = getItems();

        if (!preferences.getLocationSearchHistory().isEmpty()) {
            for (LocationData preference : preferences.getLocationSearchHistory()) {
                String locName = preference.getName();
                list.add(locName);
            }
        } else {
            list.add("No saved searches found.");
        }
        
        EventHandler<ActionEvent> event =
                  (ActionEvent e) -> {
                      searchbar.setText(getValue().toString());
        };
        
        setOnAction(event);
    }
    
    void addFavouriteLocation(LocationData loc){
        this.preferences.addFavouriteLocations(loc);
        this.list.add(0,loc.getName());
    }
    
    void addLocationIntoHistory(LocationData loc){
        if (!this.list.contains(loc.getName())) {
            this.preferences.addLocationIntoHistory(loc);
            this.list.add(loc.getName());
        }
    }
}
