package fi.tuni.prog3.weatherapp.apigson.location;

/**
 *
 * @author vasav
 */
public class LocationData {
    
    private Double lat;
    
    private Double lon;
    
    private String name;

    
    public LocationData() {}
    
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
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "(" + this.lat + "," + this.lon + ")";
    }
}
