package fi.tuni.prog3.weatherapp.components.supplement;

import fi.tuni.prog3.weatherapp.WeatherApp;
import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * Class to change units
 * @author vasav
 */
public class Units extends HBox{
    private final WeatherApp main;
    private boolean isMetric;
    private final Button changeMetric;
    private final Label message;
    private final LoadingCircle loader;
    private boolean isLoading;
    
    /**
     * Constructor
     * @param main The main class
     * @param preferences Preferences object used to store data
     */
    public Units(WeatherApp main, Preferences preferences) {
        this.main = main;
        this.isMetric = true;
        this.loader = new LoadingCircle(8, 8);
        this.isLoading = false;
        
        this.changeMetric = new Button("Imperial");
        changeMetric.setOnAction(e -> {
            if(!isLoading) {
                main.setUnit(isMetric ? "imperial" : "metric");
                onClick(preferences.getCurrentLocation().getName());
            } 
        });
        changeMetric.setFocusTraversable(false);
        
        this.message = new Label();
        message.setFont(new Font("Calibri", 10));

        getChildren().add(changeMetric);
        setSpacing(2);
    }
    
    /**
     * Changes the metric and gets the data in the new metric
     * @param search String of the location name
     */
    private void onClick(String search) {
        isLoading = true;
        getChildren().add(loader);
        loader.play();
        
        // Uses tasks and threads for asynchronous functioning
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return main.searchResult(search); 
            }

            @Override
            protected void succeeded() {
                Boolean result = getValue();
                if (result) {
                    changeMetric.setText(isMetric ? "Metric" : "Imperial");
                    isMetric = !isMetric;
                } else {
                    getChildren().add(message);
                    message.setText("An error occured");
                }
                loader.pause();
                getChildren().remove(loader);
                isLoading = false;
            }
        };
        new Thread(task).start();   
    }
}
