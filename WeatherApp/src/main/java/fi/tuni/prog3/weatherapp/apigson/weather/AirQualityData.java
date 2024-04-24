package fi.tuni.prog3.weatherapp.apigson.weather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class stores air quality data
 * @author vasav
 */
public class AirQualityData {
    public static Map<Double, String> DESCRIPTOR = new HashMap<>(){{
        put(1.0, "Good");
        put(2.0, "Fair");
        put(3.0, "Moderate");
        put(4.0, "Poor");
        put(5.0, "Very Poor");
    }};
    
    private List<AirQuality> list;

    /**
     * Gets list of AirQuality objects
     * @return List<>
     */
    public List<AirQuality> getList() {
        return list;
    }

    /**
     * Sets list of AirQuality objects
     * @param list List to be set
     */
    public void setList(List<AirQuality> list) {
        this.list = list;
    }
    
    /**
     * Matches a string description of the air quality value
     * @param quality air quality as double
     * @return String
     */
    public static String descriptionOfQuality(Double quality) {
        return DESCRIPTOR.get(quality);
    }
}
