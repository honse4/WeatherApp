package fi.tuni.prog3.weatherapp.apigson.weather;

/**
 * Class stores wind data
 * @author vasav
 */
public class Wind {
    private Double speed;
    private Double deg;
    private Double gust;

    /**
     * Gets the speed of the wind
     * @return Double
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * Sets the wind speed
     * @param speed speed to be set
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * Gets the degree of the wind
     * @return Double
     */
    public Double getDeg() {
        return deg;
    }

    /**
     * Sets the degree of the wind
     * @param deg degree to be set
     */
    public void setDeg(Double deg) {
        this.deg = deg;
    }

    /**
     * Gets the gust value
     * @return Double
     */
    public Double getGust() {
        return gust;
    }

    /**
     * Sets the gust value
     * @param gust gust value to be set
     */
    public void setGust(Double gust) {
        this.gust = gust;
    }
    
}
