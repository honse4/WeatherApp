package fi.tuni.prog3.weatherapp.components;

import fi.tuni.prog3.weatherapp.apigson.location.LocationData;
import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author vasav
 */
public class Favourite extends Polygon {
    private final Preferences preferences;
    private final SearchBar search;
    
    public Favourite(Preferences preferences, SearchBar search) {
        this. preferences = preferences;
        this.search = search;
        
        double angle = Math.PI / 5;
        double innerRadius = 12 * Math.sin(angle) / Math.cos(angle);
        
        for (int i = 0; i < 10; i++) {
            double r = (i % 2 == 0) ? 12 : innerRadius;
            getPoints().add(5 + r * Math.sin(i * 2 * Math.PI / 10));
            getPoints().add(5 - r * Math.cos(i * 2 * Math.PI / 10));
        }
        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
        setStrokeWidth(1);
        
        setOnMouseEntered(e -> {
            setScaleX(1.1);
            setScaleY(1.1);
            setScaleZ(1.1);
        });
        
        setOnMouseExited(e -> {
            setScaleX(1);
            setScaleY(1);
            setScaleZ(1);
        });
        
        
    } 
        
    public void pressStar(LocationData data) {
        if (!preferences.getFavouriteLocations().isEmpty()) {
            if (preferences.getFavouriteLocations().contains(data)) {
                search.deleteFavourite(data);
                setFill(Color.TRANSPARENT);
            } else {
                preferences.addFavouriteLocations(data);
                search.addFavourite(data);
                setFill(Color.YELLOW);
            }       
        } else {
            ArrayList<LocationData>  locationList = new ArrayList<>();
            locationList.add(data);
            preferences.setFavouriteLocations(locationList);
            search.addFavourite(data);
            setFill(Color.YELLOW);
        }
            
    }
    
    public void checkFavourite(LocationData data) {
        
        if (preferences.getFavouriteLocations() != null) {
            if(preferences.getFavouriteLocations().contains(data)) {
                 setFill(Color.YELLOW);
            } else {
                setFill(Color.TRANSPARENT);
            }
        }
    }
}
