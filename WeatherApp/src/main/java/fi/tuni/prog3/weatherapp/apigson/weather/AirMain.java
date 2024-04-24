package fi.tuni.prog3.weatherapp.apigson.weather;

/**
 * Class to contain information about the air
 * @author vasav
 */
public class AirMain {
    private Double aqi;

    /**
     * Gets the air quality in Double
     * @return Double
     */
    public Double getAqi() {
        return aqi;
    }

    /**
     * Sets the air quality
     * @param aqi quality to be set
     */
    public void setAqi(Double aqi) {
        this.aqi = aqi;
    } 
}
