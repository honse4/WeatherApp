package fi.tuni.prog3.weatherapp.apigson.weather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
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

    public List<AirQuality> getList() {
        return list;
    }

    public void setList(List<AirQuality> list) {
        this.list = list;
    }
    
    public static String descriptionOfQuality(Double quality) {
        return DESCRIPTOR.get(quality);
    }
}
