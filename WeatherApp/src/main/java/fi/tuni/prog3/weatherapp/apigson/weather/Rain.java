package fi.tuni.prog3.weatherapp.apigson.weather;

/**
 * Class contains rain data for one hour
 * @author vasav
 */
public class Rain {
    private Double _1h;

    /**
     * Gets the 1 hour rain data
     * @return Double
     */
    public Double get1h() {
        return _1h;
    }

    /**
     * Sets the 1 hour rain data
     * @param _1h value to be set
     */
    public void set1h(Double _1h) {
        this._1h = _1h;
    } 
}
