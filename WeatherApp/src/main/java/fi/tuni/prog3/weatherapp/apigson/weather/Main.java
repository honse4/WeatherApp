package fi.tuni.prog3.weatherapp.apigson.weather;

/**
 * Class that contains weather details for a few quantities
 * @author vasav
 */
public class Main {
    private Double temp;
    private Double feels_like;
    private Double temp_min;
    private Double temp_max;
    private Double pressure;
    private Double humidity;

    /**
     * Gets the temperature
     * @return Double
     */
    public Double getTemp() {
        return temp;
    }

    /**
     * Sets the temperature
     * @param temp temperature to be set
     */
    public void setTemp(Double temp) {
        this.temp = temp;
    }

    /**
     * Gets the temperature it feels like
     * @return Double
     */
    public Double getFeels_like() {
        return feels_like;
    }

    /**
     * Sets the temperature it feels like
     * @param feels_like temperature to be set
     */
    public void setFeels_like(Double feels_like) {
        this.feels_like = feels_like;
    }

    /**
     * Gets the minimum temperature
     * @return Double
     */
    public Double getTemp_min() {
        return temp_min;
    }

    /**
     * Sets the minimum temperature
     * @param temp_min temperature to be set
     */
    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    /**
     * Gets the maximum temperature
     * @return Double
     */
    public Double getTemp_max() {
        return temp_max;
    }

    /**
     * Sets the max temperature
     * @param temp_max temperature to be set
     */
    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }

    /**
     * Gets the pressure
     * @return Double
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     * Sets the pressure
     * @param pressure pressure to be set
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     * Gets the humidity
     * @return Double
     */
    public Double getHumidity() {
        return humidity;
    }

    /**
     * Sets the humidity
     * @param humidity humidity to be set
     */
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
