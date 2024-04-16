package fi.tuni.prog3.weatherapp;

import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * @author Ville Kivioja
 */

public class WeatherJsonProcessor implements iReadAndWriteToFile {
    private Preferences preferences;
    String result;

    @Override
    public String readFromFile(String fileName) throws Exception {
        FileInputStream file = new FileInputStream(fileName);
        StringBuilder content = new StringBuilder();
        try (BufferedReader responseReader = new BufferedReader(new InputStreamReader(file))) {
            responseReader.lines().forEach(line -> content.append(line).append("\n"));
            file.close();
            result = content.toString();
            return content.toString();
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    @Override
    public boolean writeToFile(String fileName) throws Exception {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter(fileName);
            gson.toJson(this.preferences, writer);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }
}
