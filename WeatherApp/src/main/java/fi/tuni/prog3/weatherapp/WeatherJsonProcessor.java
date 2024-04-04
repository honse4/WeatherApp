package fi.tuni.prog3.weatherapp;

import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Ville Kivioja
 */

public class WeatherJsonProcessor implements iReadAndWriteToFile {
    private Preferences preferences;

    @Override
    public String readFromFile(String fileName) throws Exception {
        Gson gson = new Gson();
        try {
            preferences = gson.fromJson(new FileReader(fileName), Preferences.class);
            return preferences.toString();
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    @Override
    public boolean writeToFile(String fileName) throws Exception {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter(fileName);
            gson.toJson(preferences, writer);
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    public Preferences getPreferences() {
        return preferences;
    }
}
