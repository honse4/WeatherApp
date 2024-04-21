package fi.tuni.prog3.weatherapp.preferencesgson;

import java.util.List;
import fi.tuni.prog3.weatherapp.apigson.location.LocationData;
import java.util.ArrayList;

/**
 *
 * @author Ville Kivioja
 */

public class Preferences {
    
    private LocationData currentLocation;
    
    private List<LocationData> locationSearchHistory;
    
    private List<LocationData> favouriteLocations;

    public Preferences() {}
    
    public void setCurrentLocation(LocationData currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setLocationSearchHistory(List<LocationData> locationSearchHistory) {
        this.locationSearchHistory = locationSearchHistory;
    }

    public void setFavouriteLocations(List<LocationData> favouriteLocations) {
        this.favouriteLocations = favouriteLocations;
    }

    public LocationData getCurrentLocation() {
        return currentLocation;
    }

    public List<LocationData> getLocationSearchHistory() {
        if (locationSearchHistory == null) {
            return new ArrayList<>();
        }
        return locationSearchHistory;
    }

    public List<LocationData> getFavouriteLocations() {
        if (favouriteLocations == null) {
            return new ArrayList<>();
        }
        return favouriteLocations;
    }
    
    public void addLocationIntoHistory(LocationData newLocation) {
        if (this.locationSearchHistory == null) {
            this.locationSearchHistory = new ArrayList<>();
        }
        this.locationSearchHistory.add(newLocation);
    }
    
    public void deleteLocationFromHistory(LocationData location) {
        this.locationSearchHistory.remove(location);
    }

    public void addFavouriteLocations(LocationData newFavourite) {
        if (this.favouriteLocations == null) {
            this.locationSearchHistory = new ArrayList<>();
        }
        this.favouriteLocations.add(newFavourite);
    }
    
    public void deleteFavouriteLocations(LocationData data){
        this.favouriteLocations.remove(data);
    }
    
    @Override
    public String toString() {
        String currentLoc = this.currentLocation.toString();
        String history = locationSearchHistory.toString();
        String favourites =  favouriteLocations.toString();
        return String.format("current location:%s;location history:%s;favourite locations:%s",
                            currentLoc,history,favourites);
    }
}
