package fi.tuni.prog3.weatherapp.preferencesgson;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Ville Kivioja
 */
public class Preferences {
    
    private LocationData currentLocation;
    private List<LocationData> locationSearchHistory;
    private List<LocationData> favouriteLocations;

    public void setCurrentLocation(LocationData currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void addLocationIntoHistory(locationData newLocation) {
        this.locationSearchHistory.add(newLocation);
    }

    public void addFavouriteLocations(LocationData newFavourite) {
        this.favouriteLocations.add(newFavourite);
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
    
    @Override
    public String toString() {
        String currentLoc = this.currentLocation.getLat() + "," + this.currentLocation.getLon();
        String history = locationSearchHistory.toString();
        String favourites =  favouriteLocations.toString();
        return String.format("current location:%s;location history:%s;favourite locations:%s",this.currentLocation,history,favourites);
    }
}
