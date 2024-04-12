package fi.tuni.prog3.weatherapp.components;

import fi.tuni.prog3.weatherapp.WeatherApp;
import fi.tuni.prog3.weatherapp.WeatherJsonProcessor;
import fi.tuni.prog3.weatherapp.apigson.location.LocationData;
import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import java.io.FileNotFoundException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

/**
 *
 * @author vasav
 */
public class SearchBar extends VBox {
    private final Stage stage;
    private final Scene scene;
    private final WeatherApp main;
    private final TextField searchbar;
    private final Label error;
    private final HBox loading;
    private SavedSearches savedSearches;
    
    /**
     * Constructor for the search bar.
     * @param stage Stage used by javaFx
     * @param scene Main scene presented to user
     * @param main WeatherApp class used for a method call
     */
    public SearchBar(Stage stage, Scene scene, WeatherApp main) throws Exception {
        this.stage = stage;
        this.scene = scene;
        this.main = main;
        this.searchbar = new TextField();
        this.error = new Label("");
        this.loading = new HBox(new Label("Loading..."));

        loading.setAlignment(Pos.CENTER);
        
        searchbar.setMinSize(320,30);
        searchbar.setStyle("-fx-border-color: transparent; -fx-border-width: 0; -fx-background-color:transparent;"
                + "-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-font-size: 14px;");
        
        error.setAlignment(Pos.CENTER);
        error.setMinWidth(200);
        error.setStyle("-fx-text-fill: #ff0000;");
        
        getChildren().addAll(getBackButton(), getVBox(),getFavourites(), getSavedSearches());
        setSpacing(50);
        
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                onClick();
            }
        });
    }
    
    /**
     * Creates the button used to go back to the main scene
     * @return Button 
     */
    private Button getBackButton() {
        SVGPath leftArrow = new SVGPath();
        leftArrow.setContent("M 20,0 L 8,12 L 20,24 L 20,16 L 32,16 L 32,8 L 20,8 L 20,0 Z");
        leftArrow.setScaleX(0.75);  
        leftArrow.setScaleY(0.75);
        
        Button back = new Button();
        back.setAlignment(Pos.CENTER);
        back.setGraphic(leftArrow);
        back.setMaxSize(50,30);
        back.setFocusTraversable(false);
        back.setMinSize(50,30);
        
        back.setOnAction(e -> {
            searchbar.setText("");
            error.setText("");
            stage.setScene(scene);
            scene.getRoot().requestFocus();    
        });
        VBox.setMargin(back, new Insets(0, -50,0, 0));
        return back;
    }
    
    /**
     * Adds the top row to a VBox
     * @return VBox
     */
    private VBox getVBox() {
        //
        SVGPath magnifyingGlass = new SVGPath();
        magnifyingGlass.setContent("M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z");
        magnifyingGlass.setScaleX(0.035);  
        magnifyingGlass.setScaleY(0.035);
        
        Button searchButton = new Button();
        searchButton.setGraphic(magnifyingGlass);
        searchButton.setMinSize(50,40);
        searchButton.setFocusTraversable(false);
        searchButton.setStyle("-fx-background-radius: 0 20 20 0;");
        
        searchButton.setOnAction(e -> {
            onClick();
        });
        
        VBox topRow = getTopRow(searchbar, searchButton, error);
        
        VBox container = new VBox(topRow);
        VBox.setMargin(topRow, new Insets(20, 0, 0, 0));
        container.setSpacing(10);
        
        return container;
    }
    
    /**
     * Creates a HBox within a VBox that contains the search bar and button as
     * well as the error label
     * @param searchbar TextField where the user searches location
     * @param searchButton Button which submits the search
     * @param error Label which can show any errors
     * @return VBox 
     */
    private VBox getTopRow(TextField searchbar, Button searchButton, Label error) {
        HBox searchRow = new HBox(searchbar, searchButton);
        searchRow.setSpacing(0);
        searchRow.setAlignment(Pos.CENTER);
        searchRow.setStyle("-fx-border-color: #a0a0a0; -fx-padding: 0 0 0 5;"
                + "-fx-border-radius: 20; -fx-border-width: 1;");
        searchRow.setMaxSize(375, 40);
        
        VBox topRow = new VBox(searchRow, error);
        topRow.setSpacing(5);
        topRow.setAlignment(Pos.CENTER);
        
        return topRow;
    }
    
    /**
     * Function called by the search button. Uses a task to create a separate thread
     * for the api call and the main thread continues the other code. 
     */
    private void onClick() {
        String search = searchbar.getText();
        searchbar.setText("");
        error.setText("");
        
        getChildren().add(2, loading);
        
        //
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                LocationData result = main.searchResult(search);
                if (result != null) {
                    savedSearches.addLocationIntoHistory(result);
                    return true;
                }
                return false;            
            }

            @Override
            protected void succeeded() {
                Boolean result = getValue();
                if (result) {
                    stage.setScene(scene);
                    scene.getRoot().requestFocus();
                } else {
                    error.setText("Place Does Not Exist");
                }
                getChildren().remove(loading);
            }
        };
        new Thread(task).start();   
    }
    
    /**
     * 
     * @return 
     */
    private VBox getFavourites() {
        Label title = new Label("Favourites");
        title.setStyle("-fx-font-size: 14px;-fx-font-family: Helvetica;");
        VBox favourites = new VBox(title);
        favourites.setAlignment(Pos.CENTER);
        return favourites;
    }
    
    private VBox getSavedSearches() throws Exception {
        WeatherJsonProcessor processor = new WeatherJsonProcessor();
        String filename = "preferencesTestIn.json";
        try {
            Preferences preferences = processor.readFromFile(filename);
            savedSearches = new SavedSearches(stage, scene, main, searchbar,preferences);
            
            VBox favourites = new VBox(savedSearches);
            favourites.setAlignment(Pos.CENTER);
            return favourites;
        }catch (FileNotFoundException e) {
            return null;
        } 
    }
    
}
