package fi.tuni.prog3.weatherapp.apigson.weather;

/**
 * Class contains the data for air quality
 * @author vasav
 */
public class AirQuality {
    private AirMain main;

    /**
     * Gets the AirMain object
     * @return AirMain
     */
    public AirMain getMain() {
        return main;
    }

    /**
     * Sets the AirMain object
     * @param main AirMain object to be set
     */
    public void setMain(AirMain main) {
        this.main = main;
    } 
}
