package fi.tuni.prog3.weatherapp.apigson.location;

/**
 *
 * @author vasav
 */
public class LocationData {
    
    private String name;
    
    private Double lat;
    
    private Double lon;
    
    private String state;
    
    public LocationData() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Double getLat() {
        return this.lat;
    }
    
    public void setLat(Double lat) {
        this.lat = lat;
    }
    
    public Double getLon() {
        return this.lon;
    }
    
    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "(" + this.lat + "," + this.lon + ")";
    }
}
