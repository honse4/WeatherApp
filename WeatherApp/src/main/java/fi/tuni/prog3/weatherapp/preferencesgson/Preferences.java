package fi.tuni.prog3.weatherapp.preferencesgson;

import java.util.List;
import fi.tuni.prog3.weatherapp.apigson.location.LocationData;

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
        return locationSearchHistory;
    }

    public List<LocationData> getFavouriteLocations() {
        return favouriteLocations;
    }
    
    public void addLocationIntoHistory(LocationData newLocation) {
        this.locationSearchHistory.add(newLocation);
    }

    public void addFavouriteLocations(LocationData newFavourite) {
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
