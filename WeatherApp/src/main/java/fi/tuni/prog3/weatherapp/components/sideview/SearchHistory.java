package fi.tuni.prog3.weatherapp.components.sideview;

import fi.tuni.prog3.weatherapp.WeatherApp;
import fi.tuni.prog3.weatherapp.apigson.location.LocationData;
import fi.tuni.prog3.weatherapp.components.supplement.BackButton;
import fi.tuni.prog3.weatherapp.components.supplement.LoadingCircle;
import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Class displays the search history
 * @author vasav
 */
public class SearchHistory extends VBox {
    private final Preferences preferences;
    private final VBox list;
    private final Stage stage;
    private final Scene scene;
    private final WeatherApp main;
    private final HBox message;
    private final LoadingCircle loader;
    private boolean isLoading;
    
    /**
     * Constructor 
     * @param preferences Preferences object containing data
     * @param stage Primary stage
     * @param scene Main scene displayed to user
     * @param main WeatherApp class
     */
    public SearchHistory(Preferences preferences, Stage stage, Scene scene, WeatherApp main) {
        this.preferences = preferences;
        this.list = new VBox();
        this.scene = scene;
        this.stage = stage;
        this.main = main;
        this.message = new HBox();
        this.isLoading = false;
        this.loader = new LoadingCircle(10, 10);
        
        message.setAlignment(Pos.CENTER);
        
        list.setAlignment(Pos.CENTER);
        list.setSpacing(4);
        
        Label title = new Label("Search History");
        title.setFont(new Font("Helvetica", 16));
        HBox titleCenter = new HBox(title);
        titleCenter.setAlignment(Pos.CENTER);
        
        BackButton back = new BackButton();
        back.setOnAction(e -> {
            getChildren().removeIf(node -> node == message);
            stage.setScene(scene);
            scene.getRoot().requestFocus();    
        });
        
        getChildren().addAll(back, titleCenter, getScrollPane());
        setSpacing(20);    
    }
    
    /**
     * Creates the ScrollPane which contains all the locations
     * @return ScrollPane
     */
    private ScrollPane getScrollPane(){
        ArrayList<LocationData> locations = new ArrayList<>(preferences.getLocationSearchHistory());
        
        if (!locations.isEmpty()) {
            for (LocationData data: locations) {
                 addLocation(data);
            }
        }
        else {
            Label empty = new Label("Seach history is empty");
            empty.setId("label");
            list.getChildren().add(empty);
        }
        
        ScrollPane favouritesContainer = new ScrollPane();
        favouritesContainer.setFitToHeight(true);
        favouritesContainer.setFitToWidth(true);
        favouritesContainer.setContent(list);
        favouritesContainer.setStyle("-fx-background-color: transparent; "
        + "-fx-border-color: transparent; -fx-padding: 10 7 12 7; "
        + "-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        VBox.setMargin(favouritesContainer, new Insets(0, 30,80,30));
        
        return favouritesContainer;
    }
    
    /**
     * Adds a location to search history
     * @param data LocationData to be added
     */
    public void addLocation(LocationData data) { 
        if (!preferences.getLocationSearchHistory().isEmpty() ) {
            deleteLocation(data);
            preferences.addLocationIntoHistory(data);
        } else {
            ArrayList<LocationData>  locationList = new ArrayList<>();
            locationList.add(data);
            preferences.setLocationSearchHistory(locationList);
        }
        
        Button delete = new Button("X");
        delete.setOnAction(e -> {
            if(!isLoading) {
                deleteLocation(data);
            }
        });
        
        LocationRow row = new LocationRow(data.getName(), data.getState(), delete);
        row.setOnMouseClicked(e -> {
            if (!isLoading) {
                onClick(data.getName());
            } 
        });
        
        Platform.runLater(() -> {
            list.getChildren().removeIf(node -> node instanceof Label);
            list.getChildren().add(0, row);
        });
        
    }
    
    /**
     * Deletes a location from search history
     * @param data LocationData to be removed
     */
    private void deleteLocation(LocationData data) {
        preferences.deleteLocationFromHistory(data);
        
        Platform.runLater(() -> {
            list.getChildren().removeIf(node -> node.getId().equals(data.getName()+data.getState()));
            if (list.getChildren().isEmpty()) {
                Label empty = new Label("Seach history is empty");
                empty.setId("label");
                list.getChildren().add(empty);
        }
        });
    }
    
    /**
     * Makes the search for the location and reverts back to the main scene
     * @param search String of location name
     */
    private void onClick(String search) {
        isLoading = true;
        getChildren().removeIf(node -> node == message);
        message.getChildren().removeIf(node -> node instanceof Label);
        message.getChildren().add(loader);
        loader.play();
        getChildren().add(2, message);
        
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
                    stage.setScene(scene);
                    scene.getRoot().requestFocus();
                    getChildren().remove(message);
                } else {
                    message.getChildren().add(new Label("Error processing your request"));
                }
                loader.pause();
                message.getChildren().removeIf(node -> node instanceof LoadingCircle);
                isLoading = false;
            }
        };
        new Thread(task).start();   
    }
}
