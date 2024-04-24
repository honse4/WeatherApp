package fi.tuni.prog3.weatherapp.apigson.location;

import java.util.Objects;

/**
 * A class to represent a location
 * @author vasav
 */
public class LocationData {
    
    private String name;
    
    private Double lat;
    
    private Double lon;
    
    private String state;
    
    public LocationData() {}

    /**
     * Gets name of the location
     * @return String
     */
    public String getName() {
        if (name == null) {
            return "Tampere";
        }
        return name;
    }

    /**
     * Sets the name of the location
     * @param name name to be set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the latitude of the location
     * @return Double
     */
    public Double getLat() {
        return this.lat;
    }
    
    /**
     * Sets latitude of the location
     * @param lat latitude to be set
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }
    
    /**
     * Gets the longitude of the location
     * @return Double
     */
    public Double getLon() {
        return this.lon;
    }
    
    /**
     * Sets longitude of the location
     * @param lon longitude to be set
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * Gets the state of the location
     * @return String
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state of the location
     * @param state state to be set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Makes a string representation of the class
     * @return String
     */
    @Override
    public String toString() {
        return "(" + this.lat + "," + this.lon + ")";
    }
    
    /**
     * Defines equality for the class
     * @param obj object of the same class
     * @return boolean
     */
     @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LocationData other = (LocationData) obj;
        return Objects.equals(lat, other.lat) && Objects.equals(lon, other.lon);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.lat);
        hash = 97 * hash + Objects.hashCode(this.lon);
        return hash;
    }
}
