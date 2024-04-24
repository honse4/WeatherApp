package fi.tuni.prog3.weatherapp;

import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * @author Ville Kivioja
 */

public class WeatherJsonProcessor implements iReadAndWriteToFile {
    private Preferences preferences;

    
     /**
     * Reads and returns the contents of read Json-file as String
     * @param fileName the file which is to be read
     * @return String.
     */
    @Override
    public String readFromFile(String fileName) throws Exception {
        try {
            FileInputStream file = new FileInputStream(fileName);
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(file));
            StringBuilder content = new StringBuilder();
            responseReader.lines().forEach(line -> content.append(line));
            file.close();
            return content.toString();
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * Writes the contents of the preferences into the Json-file of the
     * given name
     * @param fileName the file into which the contents are written
     * @return boolean-value based on whether the writing was succesful or not
     */
    @Override
    public boolean writeToFile(String fileName) throws Exception {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter(fileName);
            gson.toJson(this.preferences, writer);
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    /**
     * Gets the preferences attribute
     * @return Preferences
     */
    public Preferences getPreferences() {
        return preferences;
    }

    /**
     * Sets the preferences attribute
     * @param preferences Preferences object
     */
    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }
}
