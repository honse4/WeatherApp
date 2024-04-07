/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp.apigson.location;

/**
 *
 * @author vasav
 */
public class LocationData {
<<<<<<< HEAD
    private Double lat;
    private Double lon;
    
=======
    
    private Double lat;
    
    private Double lon;
    
    public LocationData() {}
    
>>>>>>> feature-json-write-read
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
<<<<<<< HEAD
}
=======
    
    @Override
    public String toString() {
        return "(" + this.lat + "," + this.lon + ")";
    }
}

>>>>>>> feature-json-write-read
