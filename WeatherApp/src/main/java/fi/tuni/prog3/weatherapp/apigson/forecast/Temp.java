package fi.tuni.prog3.weatherapp.apigson.forecast;

/**
 *
 * @author vasav
 */
public class Temp {
    private Double max;
    private Double min;

    /**
     * Gets max temperature
     * @return Double
     */
    public Double getMax() {
        return max;
    }

    /**
     * Sets max temperature
     * @param max temperature to be set
     */
    public void setMax(Double max) {
        this.max = max;
    }

    /**
     * Gets min temperature
     * @return Double
     */
    public Double getMin() {
        return min;
    }

    /**
     * Sets min temperature
     * @param min temperature to be set
     */
    public void setMin(Double min) {
        this.min = min;
    }
    
    
}
