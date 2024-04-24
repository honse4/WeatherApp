package fi.tuni.prog3.weatherapp.apigson.weather;


/**
 * Class to store weather description
 * @author vasav
 */
public class Weather {
    private String main;
    private String description;
    
    /**
     * Gets the main weather description
     * @return String
     */
    public String getMain() {
        return this.main;
    }
    
    /**
     * Sets the main weather description
     * @param main Description to be set
     */
    public void setMain(String main) {
        this.main = main;
    }
    
    /**
     * Gets the elaborate weather description
     * @return String
     */
    public String getDescription(){
        return this.description;
    }
    
    /**
     * Sets the elaborate weather description
     * @param description description to be set
     */
    public void setDescription(String description) {
        this.description = description;
    } 
    
}
